package moe.lottuce.stampeditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

    @FXML
    private void initialize() {
        text.textProperty().addListener((o, oldValue, newValue) -> {
            ((CircularText) drawable).setText(newValue);
            mainController.redrawCanvas();
        });
        fontName.textProperty().addListener((o, oldValue, newValue) -> {
            ((CircularText) drawable).setFont(new Font(newValue, fontSize.getValue()));
            mainController.redrawCanvas();
        });
        fontSize.valueProperty().addListener((o, oldValue, newValue) -> {
            ((CircularText) drawable).setFont(new Font(fontName.getText(), newValue));
            mainController.redrawCanvas();
        });
        color.valueProperty().addListener((o, oldValue, newValue) -> {
            ((CircularText) drawable).setPaint(newValue);
            mainController.redrawCanvas();
        });
        diameter.valueProperty().addListener((o, oldValue, newValue) -> {
            ((CircularText) drawable).setDiameter(newValue);
            mainController.redrawCanvas();
        });
        startAngle.valueProperty().addListener((o, oldValue, newValue) -> {
            ((CircularText) drawable).setStartAngle(newValue);
            mainController.redrawCanvas();
        });
        endAngle.valueProperty().addListener((o, oldValue, newValue) -> {
            ((CircularText) drawable).setEndAngle(newValue);
            mainController.redrawCanvas();
        });
        tracking.valueProperty().addListener((o, oldValue, newValue) -> {
            ((CircularText) drawable).setTracking(newValue);
            mainController.redrawCanvas();
        });
    }

    public void initDrawable() {
        this.text.setText(((CircularText) drawable).getText());
        Font font = ((CircularText) drawable).getFont();
        this.fontName.setText(font.getName());
        this.fontSize.getValueFactory().setValue(font.getSize());
        this.color.setValue((Color) ((CircularText) drawable).getPaint());
        this.diameter.getValueFactory().setValue(((CircularText) drawable).getDiameter());
        this.startAngle.getValueFactory().setValue(((CircularText) drawable).getStartAngle());
        this.endAngle.getValueFactory().setValue(((CircularText) drawable).getEndAngle());
        this.tracking.getValueFactory().setValue(((CircularText) drawable).getTracking());
    }
}
