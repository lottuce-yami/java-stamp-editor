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
    private MainController mainController;

    private HorizontalText drawable;

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

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = (HorizontalText) drawable;
    }

    public void initDrawable() {
        this.text.setText(drawable.getText());
        this.fontName.setText(drawable.getFont().getName());
        this.fontSize.getValueFactory().setValue(drawable.getFont().getSize());
        this.color.setValue((Color) drawable.getPaint());
        this.x.getValueFactory().setValue(drawable.getX());
        this.y.getValueFactory().setValue(drawable.getY());
        this.textAlignment.setValue(drawable.getTextAlignment());
        this.textBaseline.setValue(drawable.getTextBaseline());
    }

    @FXML
    private void onColorChanged() {
        ((HorizontalText) drawable).setPaint(color.getValue());
        mainController.redrawCanvas();
    }
}
