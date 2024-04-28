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
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.HorizontalText;
import moe.lottuce.stampeditor.io.Exporter;
import moe.lottuce.stampeditor.io.Reader;
import moe.lottuce.stampeditor.io.Stamp;
import moe.lottuce.stampeditor.io.Writer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private Drawable[] drawables;

    @FXML
    private void initialize() throws IOException {
        gc = stampCanvas.getGraphicsContext2D();

        this.drawables = new Drawable[] {
                new HorizontalText("Example text", new Font("Times New Roman", 16), Color.NAVY, TextAlignment.CENTER, VPos.CENTER, stampCanvas.getWidth() / 2, stampCanvas.getHeight() / 2)/*,
                new CircularFrame(5, Color.NAVY, 195),
                new CircularFrame(2.5, Color.NAVY, 185),
                new CircularFrame(2.5, Color.NAVY, 150),
                new CircularText("Example text", new Font("Times New Roman", 12), Color.NAVY, 165, 185, 535, 5)*/
        };

        List<TitledPane> titledPanes = new ArrayList<>();
        for (Drawable drawable : drawables) {
            FXMLLoader fxmlLoader = new FXMLLoader(StampEditorApplication.class.getResource(String.format("fxml/drawable/%1$s.fxml", drawable.getClass().getSimpleName())));

            /*switch (drawable) {
                case CircularFrame circularFrame -> fxmlLoader.setController(new CircularFrameController(drawable));
                case CircularText circularText -> fxmlLoader.setController(new CircularTextController(drawable));
                case HorizontalText horizontalText -> fxmlLoader.setController(new HorizontalTextController((HorizontalText) drawable));
                default -> throw new RuntimeException();
            }*/



            TitledPane titledPane = new TitledPane(drawable.getClass().getSimpleName(), fxmlLoader.load());
            ((HorizontalTextController) fxmlLoader.getController()).setMainController(this);
            ((HorizontalTextController) fxmlLoader.getController()).setDrawable(drawable);
            titledPanes.add(titledPane);
        }

        drawablePanes.getChildren().addAll(0, titledPanes);

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
            Writer.saveAs(stampCanvas.getScene().getWindow(), new Stamp(drawables));
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
            drawables = Reader.open(stampCanvas.getScene().getWindow()).drawables();
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

        for (Drawable drawable : drawables) {
            drawable.draw(stampCanvas);
        }
    }

    protected boolean confirmProceedingWithoutSave() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ви не зберегли зміни в поточному файлі! Продовжити?");
        Optional<ButtonType> response = alert.showAndWait();

        return response.isPresent() && response.get() == ButtonType.OK;
    }

    @FXML
    protected void onDrawableAdded(ActionEvent actionEvent) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
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
        drawables = new Drawable[]{(Drawable) type.getConstructor().newInstance()};
        redrawCanvas();
    }
}
