package moe.lottuce.stampeditor.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;

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
    protected void onPrimaryTextChanged(ActionEvent actionEvent) {
        GraphicsContext gc = stampCanvas.getGraphicsContext2D();

        double canvasSide = stampCanvas.getWidth();
        double circleDiameter = canvasSide - 5;
        double circleRadius = circleDiameter / 2;

        gc.strokeOval(
                canvasSide / 2 - circleRadius,
                canvasSide / 2 - circleRadius,
                circleDiameter,
                circleDiameter
        );

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
                primaryTextField.getText(),
                canvasSide / 2,
                canvasSide / 2
        );
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
