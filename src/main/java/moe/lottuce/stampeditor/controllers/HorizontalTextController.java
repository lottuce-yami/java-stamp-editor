package moe.lottuce.stampeditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.HorizontalText;
import moe.lottuce.stampeditor.drawables.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HorizontalTextController {
    @FXML
    protected TextField text;

    @FXML
    protected TextField fontName;

    @FXML
    protected Spinner<Double> fontSize;

    @FXML
    protected ColorPicker color;

    protected HorizontalText drawable;

    @FXML
    private Spinner x;

    @FXML
    private Spinner y;

    @FXML
    private ChoiceBox textAlignment;

    @FXML
    private ChoiceBox textBaseline;

    public HorizontalTextController(Drawable drawable) {
        this.drawable = (HorizontalText) drawable;
    }

    public void initialize() {
        this.text.setText(drawable.getText());
        this.fontName.setText(drawable.getFont().getName());
        this.fontSize.getValueFactory().setValue(drawable.getFont().getSize());
        this.color.setValue((Color) drawable.getPaint());

        this.x.getValueFactory().setValue(drawable.getX());
        this.y.getValueFactory().setValue(drawable.getY());
        this.textAlignment.setValue(drawable.getTextAlignment());
        this.textBaseline.setValue(drawable.getTextBaseline());
    }
}
