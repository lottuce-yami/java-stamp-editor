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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import moe.lottuce.stampeditor.models.CircularFrame;
import moe.lottuce.stampeditor.models.HorizontalText;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
        CircularFrame[] frames = {
                new CircularFrame(stampCanvas, 5, Color.NAVY, 195),
                new CircularFrame(stampCanvas, 2.5, Color.NAVY, 185),
                new CircularFrame(stampCanvas, 2.5, Color.NAVY, 150)
        };

        HorizontalText[] texts = {
                new HorizontalText(stampCanvas, primaryTextField.getText(), new Font("Times New Roman", 16), Color.NAVY)
        };

        gc.clearRect(0, 0, stampCanvas.getWidth(), stampCanvas.getHeight());

        for (CircularFrame frame : frames) {
            frame.draw();
        }

        for (HorizontalText text : texts) {
            text.draw();
        }

        Font additionalFont = new Font("Times New Roman", 12);
        drawCircularText(additionalTextField.getText(), additionalFont, Color.NAVY, 5, 165, 185, 535);
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

    protected void drawCircularText(String text, Font font, Paint paint, double tracking, double diameter, double startAngle, double endAngle) {
        double radius = diameter / 2;
        double circlePerimeter = 2 * Math.PI * radius;
        double angleInPixels = circlePerimeter / 360;
        double angleArea = Math.abs(startAngle - endAngle);

        int letterCount = text.replace(" ", "").length();
        int spaceCount = text.length() - letterCount;

        Text sceneText = new Text();
        sceneText.setFont(font);

        sceneText.setText(" ");
        double trackingInPixels = (sceneText.getLayoutBounds().getHeight() / font.getSize()) * tracking;

        double[] symbolsWidth = new double[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ' ') {
                symbolsWidth[i-1] -= trackingInPixels;
                continue;
            }

            sceneText.setText(String.valueOf(c));
            symbolsWidth[i] = sceneText.getLayoutBounds().getWidth() + trackingInPixels;

            if (i == text.length() - 1) {
                symbolsWidth[i] -= trackingInPixels;
            }
        }

        double textWidthInAngles = Arrays.stream(symbolsWidth).sum() / angleInPixels;
        double emptyAreaInAngles = angleArea - textWidthInAngles;
        double spaceWidthInAngles = emptyAreaInAngles / spaceCount;

        gc.setFont(font);
        gc.setFill(paint);

        double currentAngle = startAngle;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ' ') {
                currentAngle += spaceWidthInAngles;
                continue;
            }

            double x = getPointAtAngle(radius, currentAngle)[0];
            double y = getPointAtAngle(radius, currentAngle)[1];

            Rotate r = new Rotate(currentAngle + 90, x, y);
            gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

            gc.fillText(String.valueOf(c), x, y);
            currentAngle += symbolsWidth[i] / angleInPixels;
        }

        gc.setFont(Font.getDefault());
        gc.setFill(Color.BLACK);
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
