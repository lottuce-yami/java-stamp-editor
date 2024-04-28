package moe.lottuce.stampeditor.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class Writer {
    public static File saveAs(Window window, Stamp stamp) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Зберегти як...");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );
        fileChooser.setInitialFileName("stamp-preset");

        File saveFile = fileChooser.showSaveDialog(window);

        save(saveFile, stamp);
        return saveFile;
    }

    public static void save(File file, Stamp stamp) throws IOException {
        if (file != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(file, stamp);
        }
    }
}
