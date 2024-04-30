package moe.lottuce.stampeditor.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import moe.lottuce.stampeditor.StampEditorApplication;
import moe.lottuce.stampeditor.drawable.*;
import moe.lottuce.stampeditor.io.Exporter;
import moe.lottuce.stampeditor.io.Reader;
import moe.lottuce.stampeditor.io.Stamp;
import moe.lottuce.stampeditor.io.Writer;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainController {
    @FXML
    private Canvas canvas;

    @FXML
    private VBox drawablePanes;

    @FXML
    private Label canvasSize;

    @FXML
    private Label filePath;

    private static final ResourceBundle localization = ResourceBundle.getBundle("moe/lottuce/stampeditor/bundles/StampEditor");

    private GraphicsContext gc;

    private Map<Drawable, TitledPane> drawables = new HashMap<>();

    private ObjectProperty<File> saveFile = new SimpleObjectProperty<>();

    @FXML
    private void initialize() {
        gc = canvas.getGraphicsContext2D();

        canvasSize.setText(String.format("%1$s: %2$s x %3$s", localization.getString("canvasSize"), canvas.getWidth(), canvas.getHeight()));
        saveFile.addListener((o, oldValue, newValue) -> filePath.setText(saveFile.get().getAbsolutePath()));
    }

    @FXML
    protected void onOpen() {
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
            showIOAlert(e);
        }
    }

    @FXML
    protected void onSave() {
        try {
            if (saveFile == null) {
                onSaveAs();
            }
            else {
                Writer.save(saveFile.get(), new Stamp(drawables.keySet().stream().toList()));
            }
        }
        catch (IOException e) {
            showIOAlert(e);
        }
    }

    @FXML
    protected void onSaveAs() {
        try {
            saveFile.set(Writer.saveAs(canvas.getScene().getWindow(), new Stamp(drawables.keySet().stream().toList())));
        }
        catch (IOException e) {
            showIOAlert(e);
        }
    }

    @FXML
    protected void onExport() {
        try {
            Exporter.exportAs(canvas);
        }
        catch (IOException e) {
            showIOAlert(e);
        }
    }

    @FXML
    protected void onDrawableAdded() {
        Drawable drawable;
        String rectangularFrame = localization.getString("RectangularFrame");
        String circularFrame = localization.getString("CircularFrame");
        String circularText = localization.getString("CircularText");
        String horizontalText = localization.getString("HorizontalText");

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>();
        choiceDialog.getItems().addAll(rectangularFrame, circularFrame, horizontalText, circularText);
        choiceDialog.setSelectedItem(rectangularFrame);

        Optional<String> result = choiceDialog.showAndWait();
        if (result.isEmpty()) {
            return;
        }

        if (result.get().equals(rectangularFrame)) {
            drawable = new RectangularFrame();
        }
        else if (result.get().equals(circularFrame)) {
            drawable = new CircularFrame();
        }
        else if (result.get().equals(horizontalText)) {
            drawable = new HorizontalText();
        }
        else if (result.get().equals(circularText)) {
            drawable = new CircularText();
        }
        else {
                throw new RuntimeException();
        }

        TitledPane titledPane = createTitledPane(drawable);
        drawablePanes.getChildren().add(drawablePanes.getChildren().size() - 1, titledPane);
        drawables.put(drawable, titledPane);
        redrawCanvas();
    }

    protected void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Drawable drawable : drawables.keySet()) {
            drawable.draw(canvas);
        }
    }

    protected TitledPane createTitledPane(Drawable drawable) {
        String className = drawable.getClass().getSimpleName();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(StampEditorApplication.class.getResource(String.format("fxml/drawable/%1$s.fxml", className)));
            fxmlLoader.setResources(localization);
            TitledPane titledPane = new TitledPane(localization.getString(className), fxmlLoader.load());
            ((DrawableController) fxmlLoader.getController()).setMainController(this);
            ((DrawableController) fxmlLoader.getController()).setDrawable(drawable);
            ((DrawableController) fxmlLoader.getController()).initDrawable();

            return titledPane;
        }
        catch (IOException e) {
            showIOAlert(e);
        }

        return null;
    }

    protected void removeDrawable(Drawable drawable) {
        drawablePanes.getChildren().remove(drawables.get(drawable));
        drawables.remove(drawable);
        redrawCanvas();
    }

    protected boolean confirmProceedingWithoutSave() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, localization.getString("saveWarning"));
        Optional<ButtonType> response = alert.showAndWait();

        return response.isPresent() && response.get() == ButtonType.OK;
    }

    private void showIOAlert(IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, String.format("%1$s %2$s", localization.getString("ioError"), e.getLocalizedMessage()));
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> alert.close());
    }
}
