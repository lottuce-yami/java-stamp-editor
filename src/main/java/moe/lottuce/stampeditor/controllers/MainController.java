package moe.lottuce.stampeditor.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import moe.lottuce.stampeditor.drawables.CircularFrame;
import moe.lottuce.stampeditor.drawables.CircularText;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.HorizontalText;
import moe.lottuce.stampeditor.io.Exporter;
import moe.lottuce.stampeditor.io.Reader;
import moe.lottuce.stampeditor.io.Stamp;
import moe.lottuce.stampeditor.io.Writer;

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

    private Stamp stamp;

    @FXML
    public void initialize() {
        gc = stampCanvas.getGraphicsContext2D();
    }

    @FXML
    protected void onTextChanged(ActionEvent actionEvent) {
        stamp = new Stamp(new Drawable[] {
                new CircularFrame(5, Color.NAVY, 195),
                new CircularFrame(2.5, Color.NAVY, 185),
                new CircularFrame(2.5, Color.NAVY, 150),
                new HorizontalText(primaryTextField.getText(), new Font("Times New Roman", 16), Color.NAVY, TextAlignment.CENTER, VPos.CENTER, stampCanvas.getWidth() / 2, stampCanvas.getHeight() / 2),
                new CircularText(additionalTextField.getText(), new Font("Times New Roman", 12), Color.NAVY, 165, 185, 535, 5)
        });

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
            Writer.saveAs(stampCanvas.getScene().getWindow(), stamp);
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
        try {
            stamp = Reader.open(stampCanvas.getScene().getWindow());
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

        for (Drawable drawable : stamp.drawables()) {
            drawable.draw(stampCanvas);
        }
    }
}
