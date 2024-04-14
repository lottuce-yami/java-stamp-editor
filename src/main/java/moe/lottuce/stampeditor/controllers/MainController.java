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
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
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

        gc.clearRect(0, 0, canvasSide, canvasSide);

        drawRoundFrame(gc, 195, Color.NAVY, 5);

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
                primaryTextField.getText(),
                canvasSide / 2,
                canvasSide / 2
        );
        drawCircularText(gc, primaryTextField.getText(), 180, 180, 360);
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

    protected void drawRoundFrame(GraphicsContext gc, double diameter, Paint paint, double width) {
        double canvasSide = stampCanvas.getWidth();
        double radius = diameter / 2;

        gc.setStroke(paint);
        gc.setLineWidth(width);

        gc.strokeOval(
                canvasSide / 2 - radius,
                canvasSide / 2 - radius,
                diameter,
                diameter
        );
    }

    protected void drawCircularText(GraphicsContext gc, String text, double diameter, double startAngle, double endAngle) {
        double radius = diameter / 2;
        double angleArea = Math.abs(startAngle - endAngle);
        double angleStep = angleArea / text.length();
        double currentAngle = startAngle;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            double x = getPointAtAngle(radius, currentAngle)[0];
            double y = getPointAtAngle(radius, currentAngle)[1];

            Rotate r = new Rotate(currentAngle+90, x, y);
            gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

            gc.fillText(String.valueOf(c), x, y);
            currentAngle += angleStep;
        }

        gc.setTransform(new Affine());
    }

    protected double[] getPointAtAngle(double radius, double angle) {
        double centerX = stampCanvas.getWidth() / 2;
        double centerY = stampCanvas.getHeight() / 2;

        double x = radius * Math.cos(Math.toRadians(angle));
        double y = radius * Math.sin(Math.toRadians(angle));

        x += centerX;
        y += centerY;

        return new double[]{x, y};
    }
}
