package moe.lottuce.stampeditor.controllers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.HorizontalText;
import moe.lottuce.stampeditor.drawables.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HorizontalTextController extends DrawableController {
    @FXML
    protected TextField text;

    @FXML
    protected TextField fontName;

    @FXML
    protected Spinner<Double> fontSize;

    @FXML
    protected ColorPicker color;

    @FXML
    private Spinner<Double> x;

    @FXML
    private Spinner<Double> y;

    @FXML
    private ChoiceBox<TextAlignment> textAlignment;

    @FXML
    private ChoiceBox<VPos> textBaseline;

    public void initDrawable() {
        this.text.setText(((HorizontalText) drawable).getText());
        this.fontName.setText(((HorizontalText) drawable).getFont().getName());
        this.fontSize.getValueFactory().setValue(((HorizontalText) drawable).getFont().getSize());
        this.color.setValue((Color) ((HorizontalText) drawable).getPaint());
        this.x.getValueFactory().setValue(((HorizontalText) drawable).getX());
        this.y.getValueFactory().setValue(((HorizontalText) drawable).getY());
        this.textAlignment.setValue(((HorizontalText) drawable).getTextAlignment());
        this.textBaseline.setValue(((HorizontalText) drawable).getTextBaseline());
    }
}
