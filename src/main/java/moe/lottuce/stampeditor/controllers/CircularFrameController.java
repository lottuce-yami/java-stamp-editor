package moe.lottuce.stampeditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;
import moe.lottuce.stampeditor.drawables.CircularFrame;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.Frame;
import moe.lottuce.stampeditor.drawables.HorizontalText;

import java.net.URL;
import java.util.ResourceBundle;

public class CircularFrameController extends DrawableController {
    @FXML
    protected Spinner<Double> width;

    @FXML
    protected ColorPicker color;

    @FXML
    private Spinner<Double> diameter;

    public void initDrawable() {
        this.width.getValueFactory().setValue(((CircularFrame) drawable).getWidth());
        this.color.setValue((Color) ((CircularFrame) drawable).getPaint());
        this.diameter.getValueFactory().setValue(((CircularFrame) drawable).getDiameter());
    }
}
