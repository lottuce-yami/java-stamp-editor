package moe.lottuce.stampeditor.io;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class Exporter {
    private static final ResourceBundle localization = ResourceBundle.getBundle("moe/lottuce/stampeditor/bundles/StampEditor");

    public static void exportAs(Canvas canvas) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(localization.getString("export"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setInitialFileName("stamp");

        File exportFile = fileChooser.showSaveDialog(canvas.getScene().getWindow());

        if (exportFile != null) {
            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            canvas.snapshot(params, writableImage);

            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "PNG", exportFile);
        }
    }
}
