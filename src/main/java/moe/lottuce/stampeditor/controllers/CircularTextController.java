package moe.lottuce.stampeditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import moe.lottuce.stampeditor.drawables.CircularText;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.HorizontalText;

import java.net.URL;
import java.util.ResourceBundle;

public class CircularTextController {
    @FXML
    protected TextField text;

    @FXML
    protected TextField fontName;

    @FXML
    protected Spinner<Double> fontSize;

    @FXML
    protected ColorPicker color;

    protected CircularText drawable;

    @FXML
    private Spinner diameter;

    @FXML
    private Spinner startAngle;

    @FXML
    private Spinner endAngle;

    @FXML
    private Spinner tracking;

    public CircularTextController(Drawable drawable) {
        this.drawable = (CircularText) drawable;
    }

    public void initialize() {
        this.text.setText(drawable.getText());
        this.fontName.setText(drawable.getFont().getName());
        this.fontSize.getValueFactory().setValue(drawable.getFont().getSize());
        this.color.setValue((Color) drawable.getPaint());

        this.diameter.getValueFactory().setValue(((CircularText) drawable).getDiameter());
        this.startAngle.getValueFactory().setValue(((CircularText) drawable).getStartAngle());
        this.endAngle.getValueFactory().setValue(((CircularText) drawable).getEndAngle());
        this.tracking.getValueFactory().setValue(((CircularText) drawable).getTracking());
    }
}
