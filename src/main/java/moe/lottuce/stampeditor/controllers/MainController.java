package moe.lottuce.stampeditor.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import moe.lottuce.stampeditor.models.CircularFrame;
import moe.lottuce.stampeditor.models.CircularText;
import moe.lottuce.stampeditor.models.Drawable;
import moe.lottuce.stampeditor.models.HorizontalText;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
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
    protected void onExport(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Експорт...");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setInitialFileName("stamp");

        File exportFile = fileChooser.showSaveDialog(stampCanvas.getScene().getWindow());
        if (exportFile != null) {
            int canvasSize = (int) stampCanvas.getWidth();
            WritableImage writableImage = new WritableImage(canvasSize, canvasSize);

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            stampCanvas.snapshot(params, writableImage);

            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "PNG", exportFile);
        }
    }
}
