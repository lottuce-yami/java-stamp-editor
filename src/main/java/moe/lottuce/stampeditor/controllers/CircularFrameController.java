package moe.lottuce.stampeditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import moe.lottuce.stampeditor.drawables.CircularFrame;
import moe.lottuce.stampeditor.drawables.Drawable;

import java.net.URL;
import java.util.ResourceBundle;

public class CircularFrameController extends FrameController {
    @FXML
    private Spinner diameter;

    @Override
    public void initDrawable(Drawable drawable) {
        super.initDrawable(drawable);

        if (drawable instanceof CircularFrame) {
            this.diameter.getValueFactory().setValue(((CircularFrame) drawable).getDiameter());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
