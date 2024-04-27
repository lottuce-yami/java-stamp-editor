package moe.lottuce.stampeditor.controllers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    private DoubleProperty drawable = new SimpleDoubleProperty();

    @FXML
    protected TextField text;

    @FXML
    protected TextField fontName;

    @FXML
    protected Spinner<Double> fontSize;

    @FXML
    protected ColorPicker color;

    @FXML
    private Spinner x;

    @FXML
    private Spinner y;

    @FXML
    private ChoiceBox textAlignment;

    @FXML
    private ChoiceBox textBaseline;

    public double getDrawable() {
        return drawable.get();
    }

    public void setDrawable(double drawable) {
        this.drawable.set(drawable);
    }

    public DoubleProperty drawableProperty() {
        return drawable;
    }

    @FXML
    private void onColorChanged() {
        System.out.println("color changed");
        setDrawable(1.0);
    }
}
