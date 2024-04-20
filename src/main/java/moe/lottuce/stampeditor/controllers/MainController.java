package moe.lottuce.stampeditor.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import moe.lottuce.stampeditor.drawables.CircularFrame;
import moe.lottuce.stampeditor.drawables.CircularText;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.HorizontalText;
import moe.lottuce.stampeditor.io.Exporter;

import java.io.IOException;

public class MainController {
    @FXML
    private Canvas stampCanvas;

    @FXML
    private TextField primaryTextField;

    @FXML
    private TextField additionalTextField;

    @FXML
    private TextField microTextField;

    private GraphicsContext gc;

    @FXML
    public void initialize() {
        gc = stampCanvas.getGraphicsContext2D();
    }

    @FXML
    protected void onTextChanged(ActionEvent actionEvent) {
        Drawable[] drawables = {
                new CircularFrame(stampCanvas, 5, Color.NAVY, 195),
                new CircularFrame(stampCanvas, 2.5, Color.NAVY, 185),
                new CircularFrame(stampCanvas, 2.5, Color.NAVY, 150),
                new HorizontalText(stampCanvas, primaryTextField.getText(), new Font("Times New Roman", 16), Color.NAVY),
                new CircularText(stampCanvas, additionalTextField.getText(), new Font("Times New Roman", 12), Color.NAVY, 165, 185, 535, 5)
        };

        gc.clearRect(0, 0, stampCanvas.getWidth(), stampCanvas.getHeight());

        for (Drawable drawable : drawables) {
            drawable.draw();
        }
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
}
