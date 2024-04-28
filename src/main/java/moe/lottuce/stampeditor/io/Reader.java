package moe.lottuce.stampeditor.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import moe.lottuce.stampeditor.drawables.Drawable;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Reader {
    public static Stamp open(Window window) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Відкрити...");
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
