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
    private Spinner x;

    @FXML
    private Spinner y;

    @FXML
    private ChoiceBox textAlignment;

    @FXML
    private ChoiceBox textBaseline;

    @FXML
    protected TextField text;

    @FXML
    protected TextField fontName;

    @FXML
    protected Spinner<Double> fontSize;

    @FXML
    protected ColorPicker color;

    protected Text drawable;


    public HorizontalTextController(Drawable drawable) {
        this.drawable = (Text) drawable;
    }


    public void initialize() {
        this.text.setText(((Text) drawable).getText());
        this.fontName.setText(((Text) drawable).getFont().getName());
        this.fontSize.getValueFactory().setValue(((Text) drawable).getFont().getSize());
        this.color.setValue((Color) ((Text) drawable).getPaint());
        this.x.getValueFactory().setValue(((HorizontalText) drawable).getX());
        this.y.getValueFactory().setValue(((HorizontalText) drawable).getY());
        this.textAlignment.setValue(((HorizontalText) drawable).getTextAlignment());
        this.textBaseline.setValue(((HorizontalText) drawable).getTextBaseline());
    }
}
