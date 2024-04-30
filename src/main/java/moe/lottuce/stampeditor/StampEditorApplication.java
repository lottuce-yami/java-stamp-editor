package moe.lottuce.stampeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class StampEditorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle localization = ResourceBundle.getBundle("moe/lottuce/stampeditor/bundles/StampEditor");
        stage.setTitle(localization.getString("StampEditor"));

        FXMLLoader fxmlLoader = new FXMLLoader(StampEditorApplication.class.getResource("fxml/view/main-view.fxml"));
        fxmlLoader.setResources(localization);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}