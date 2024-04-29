package moe.lottuce.stampeditor.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.ResourceBundle;

public class Reader {
    private static final ResourceBundle localization = ResourceBundle.getBundle("moe/lottuce/stampeditor/bundles/StampEditor");

    public static Stamp open(Window window) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(localization.getString("open"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );

        File openFile = fileChooser.showOpenDialog(window);

        if (openFile != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(openFile, Stamp.class);
        }

        return new Stamp(Collections.emptyList());
    }
}
