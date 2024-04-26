package moe.lottuce.stampeditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;
import moe.lottuce.stampeditor.drawables.CircularFrame;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.Frame;

import java.net.URL;
import java.util.ResourceBundle;

public class CircularFrameController {
    @FXML
    protected Spinner<Double> width;

    @FXML
    protected ColorPicker color;

    protected CircularFrame drawable;

    @FXML
    private Spinner diameter;

    public CircularFrameController(Drawable drawable) {
        this.drawable = (CircularFrame) drawable;
    }

    public void initialize() {
        this.width.getValueFactory().setValue(((Frame) drawable).getWidth());
        this.color.setValue((Color) ((Frame) drawable).getPaint());

        this.diameter.getValueFactory().setValue(((CircularFrame) drawable).getDiameter());
    }
}
