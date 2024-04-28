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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MainController {
    @FXML
    private Canvas stampCanvas;

    @FXML
    private TextField primaryTextField;

    @FXML
    private TextField additionalTextField;

    @FXML
    private TextField microTextField;

    @FXML
    private VBox drawablePanes;

    private GraphicsContext gc;

    private Map<Drawable, TitledPane> drawables = new HashMap<>();

    @FXML
    private void initialize() throws IOException {
        gc = stampCanvas.getGraphicsContext2D();

        Drawable[] drawables = {
                new HorizontalText("Example text", new Font("Times New Roman", 16), Color.NAVY, TextAlignment.CENTER, VPos.CENTER, stampCanvas.getWidth() / 2, stampCanvas.getHeight() / 2),
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
    }

    @FXML
    protected void onTextChanged(ActionEvent actionEvent) {
        redrawCanvas();
    }

    @FXML
    protected void onExport(ActionEvent actionEvent) {
        try {
            Exporter.exportAs(stampCanvas);
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("При експорті виникла помилка: %1$s", e.getLocalizedMessage()));
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());
        }
    }

    @FXML
    protected void onSaveAs(ActionEvent actionEvent) {
        try {
            Writer.saveAs(stampCanvas.getScene().getWindow(), new Stamp(drawables.keySet().stream().toList()));
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("При зберіганні виникла помилка: %1$s", e.getLocalizedMessage()));
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
            List<Drawable> drawables = Reader.open(stampCanvas.getScene().getWindow()).drawables();

            for (Drawable drawable : drawables) {
                TitledPane titledPane = createTitledPane(drawable);
                drawablePanes.getChildren().add(drawablePanes.getChildren().size() - 1, titledPane);
                this.drawables.put(drawable, titledPane);
            }

            redrawCanvas();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("При відкритті виникла помилка: %1$s", e.getLocalizedMessage()));
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());
        }
    }

    protected void redrawCanvas() {
        gc.clearRect(0, 0, stampCanvas.getWidth(), stampCanvas.getHeight());

        for (Drawable drawable : drawables.keySet()) {
            drawable.draw(stampCanvas);
        }
    }

    protected boolean confirmProceedingWithoutSave() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ви не зберегли зміни в поточному файлі! Продовжити?");
        Optional<ButtonType> response = alert.showAndWait();

        return response.isPresent() && response.get() == ButtonType.OK;
    }

    @FXML
    protected void onDrawableAdded(ActionEvent actionEvent) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        ChoiceDialog<Class<?>> choiceDialog = new ChoiceDialog<>();
        JsonSubTypes.Type[] types = Drawable.class.getAnnotation(JsonSubTypes.class).value();
        for (JsonSubTypes.Type type : types) {
            choiceDialog.getItems().add(type.value());
        }

        Optional<Class<?>> result = choiceDialog.showAndWait();
        if (result.isEmpty()) {
            return;
        }

        Class<?> type = result.get();

        Drawable drawable = (Drawable) type.getConstructor().newInstance();
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
        FXMLLoader fxmlLoader = new FXMLLoader(StampEditorApplication.class.getResource(String.format("fxml/drawable/%1$s.fxml", drawable.getClass().getSimpleName())));
        TitledPane titledPane = new TitledPane(drawable.getClass().getSimpleName(), fxmlLoader.load());
        ((DrawableController) fxmlLoader.getController()).setMainController(this);
        ((DrawableController) fxmlLoader.getController()).setDrawable(drawable);
        ((DrawableController) fxmlLoader.getController()).initDrawable();

        return titledPane;
    }
}
