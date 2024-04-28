package moe.lottuce.stampeditor.controllers;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import moe.lottuce.stampeditor.StampEditorApplication;
import moe.lottuce.stampeditor.drawables.CircularFrame;
import moe.lottuce.stampeditor.drawables.CircularText;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.HorizontalText;
import moe.lottuce.stampeditor.io.Exporter;
import moe.lottuce.stampeditor.io.Reader;
import moe.lottuce.stampeditor.io.Stamp;
import moe.lottuce.stampeditor.io.Writer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MainController {
    @FXML
    private Canvas canvas;

    @FXML
    private VBox drawablePanes;

    private GraphicsContext gc;

    private Map<Drawable, TitledPane> drawables = new HashMap<>();

    private File saveFile;

    private static final ResourceBundle localization = ResourceBundle.getBundle("moe/lottuce/stampeditor/bundles/StampEditor");

    @FXML
    private void initialize() throws IOException {
        gc = canvas.getGraphicsContext2D();

        Drawable[] drawables = {
                new HorizontalText("Example text", new Font("Times New Roman", 16), Color.NAVY, TextAlignment.CENTER, VPos.CENTER, canvas.getWidth() / 2, canvas.getHeight() / 2),
                new CircularFrame(5, Color.NAVY, 195),
                new CircularFrame(2.5, Color.NAVY, 185),
                new CircularFrame(2.5, Color.NAVY, 150),
                new CircularText("Example text", new Font("Times New Roman", 12), Color.NAVY, 165, 185, 535, 5)
        };

        for (Drawable drawable : drawables) {
            TitledPane titledPane = createTitledPane(drawable);
            drawablePanes.getChildren().add(drawablePanes.getChildren().size() - 1, titledPane);
            this.drawables.put(drawable, titledPane);
        }

        redrawCanvas();
    }

    @FXML
    protected void onExport(ActionEvent actionEvent) {
        try {
            Exporter.exportAs(canvas);
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("%1$s: %2$s", localization.getString("exportError"), e.getLocalizedMessage()));
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());
        }
    }

    @FXML
    protected void onSave() {
        try {
            if (saveFile == null) {
                onSaveAs();
            }
            else {
                Writer.save(saveFile, new Stamp(drawables.keySet().stream().toList()));
            }
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("%1$s: %2$s", localization.getString("saveError"), e.getLocalizedMessage()));
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());
        }
    }

    @FXML
    protected void onSaveAs() {
        try {
            saveFile = Writer.saveAs(canvas.getScene().getWindow(), new Stamp(drawables.keySet().stream().toList()));
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("%1$s: %2$s", localization.getString("saveError"), e.getLocalizedMessage()));
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());
        }
    }

    @FXML
    protected void onOpen(ActionEvent actionEvent) {
        if (!confirmProceedingWithoutSave()) {
            return;
        }

        try {
            List<Drawable> drawables = Reader.open(canvas.getScene().getWindow()).drawables();

            drawablePanes.getChildren().remove(0, this.drawables.size());
            this.drawables.clear();

            for (Drawable drawable : drawables) {
                TitledPane titledPane = createTitledPane(drawable);
                drawablePanes.getChildren().add(drawablePanes.getChildren().size() - 1, titledPane);
                this.drawables.put(drawable, titledPane);
            }

            redrawCanvas();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("%1$s: %2$s", localization.getString("openError"), e.getLocalizedMessage()));
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());
        }
    }

    protected void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Drawable drawable : drawables.keySet()) {
            drawable.draw(canvas);
        }
    }

    protected boolean confirmProceedingWithoutSave() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, localization.getString("saveWarning"));
        Optional<ButtonType> response = alert.showAndWait();

        return response.isPresent() && response.get() == ButtonType.OK;
    }

    @FXML
    protected void onDrawableAdded(ActionEvent actionEvent) throws IOException {
        Drawable drawable;
        String circularFrame = localization.getString("CircularFrame");
        String circularText = localization.getString("CircularText");
        String horizontalText = localization.getString("HorizontalText");

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>();
        choiceDialog.getItems().addAll(circularFrame, circularText, horizontalText);

        Optional<String> result = choiceDialog.showAndWait();
        if (result.isEmpty()) {
            return;
        }

        if (result.get().equals(circularFrame)) {
            drawable = new CircularFrame();
        }
        else if (result.get().equals(circularText)) {
            drawable = new CircularText();
        }
        else if (result.get().equals(horizontalText)) {
            drawable = new HorizontalText();
        }
        else {
            throw new RuntimeException();
        }

        TitledPane titledPane = createTitledPane(drawable);
        drawablePanes.getChildren().add(drawablePanes.getChildren().size() - 1, titledPane);
        drawables.put(drawable, titledPane);
        redrawCanvas();
    }

    protected void removeDrawable(Drawable drawable) {
        drawablePanes.getChildren().remove(drawables.get(drawable));
        drawables.remove(drawable);
        redrawCanvas();
    }

    protected TitledPane createTitledPane(Drawable drawable) throws IOException {
        String className = drawable.getClass().getSimpleName();

        FXMLLoader fxmlLoader = new FXMLLoader(StampEditorApplication.class.getResource(String.format("fxml/drawable/%1$s.fxml", className)));
        fxmlLoader.setResources(localization);
        TitledPane titledPane = new TitledPane(localization.getString(className), fxmlLoader.load());
        ((DrawableController) fxmlLoader.getController()).setMainController(this);
        ((DrawableController) fxmlLoader.getController()).setDrawable(drawable);
        ((DrawableController) fxmlLoader.getController()).initDrawable();

        return titledPane;
    }
}
