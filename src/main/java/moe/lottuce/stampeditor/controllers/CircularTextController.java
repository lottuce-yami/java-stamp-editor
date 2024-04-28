package moe.lottuce.stampeditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import moe.lottuce.stampeditor.drawables.CircularFrame;
import moe.lottuce.stampeditor.drawables.CircularText;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.HorizontalText;

import java.net.URL;
import java.util.ResourceBundle;

public class CircularTextController extends DrawableController {
    @FXML
    protected TextField text;

    @FXML
    protected TextField fontName;

    @FXML
    protected Spinner<Double> fontSize;

    @FXML
    protected ColorPicker color;

    @FXML
    private Spinner<Double> diameter;

    @FXML
    private Spinner<Double> startAngle;

    @FXML
    private Spinner<Double> endAngle;

    @FXML
    private Spinner<Double> tracking;

    public void initDrawable() {
        this.text.setText(((CircularText) drawable).getText());
        this.fontName.setText(((CircularText) drawable).getFont().getName());
        this.fontSize.getValueFactory().setValue(((CircularText) drawable).getFont().getSize());
        this.color.setValue((Color) ((CircularText) drawable).getPaint());
        this.diameter.getValueFactory().setValue(((CircularText) drawable).getDiameter());
        this.startAngle.getValueFactory().setValue(((CircularText) drawable).getStartAngle());
        this.endAngle.getValueFactory().setValue(((CircularText) drawable).getEndAngle());
        this.tracking.getValueFactory().setValue(((CircularText) drawable).getTracking());
    }
}
