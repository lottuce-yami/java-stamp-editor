package moe.lottuce.stampeditor.controllers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

    @FXML
    private void initialize() {
        text.textProperty().addListener((o, oldValue, newValue) -> {
            ((HorizontalText) drawable).setText(newValue);
            mainController.redrawCanvas();
        });
        fontName.textProperty().addListener((o, oldValue, newValue) -> {
            ((HorizontalText) drawable).setFont(new Font(newValue, fontSize.getValue()));
            mainController.redrawCanvas();
        });
        fontSize.valueProperty().addListener((o, oldValue, newValue) -> {
            ((HorizontalText) drawable).setFont(new Font(fontName.getText(), newValue));
            mainController.redrawCanvas();
        });
        color.valueProperty().addListener((o, oldValue, newValue) -> {
            ((HorizontalText) drawable).setPaint(newValue);
            mainController.redrawCanvas();
        });
        x.valueProperty().addListener((o, oldValue, newValue) -> {
            ((HorizontalText) drawable).setX(newValue);
            mainController.redrawCanvas();
        });
        y.valueProperty().addListener((o, oldValue, newValue) -> {
            ((HorizontalText) drawable).setY(newValue);
            mainController.redrawCanvas();
        });
        textAlignment.valueProperty().addListener((o, oldValue, newValue) -> {
            ((HorizontalText) drawable).setTextAlignment(newValue);
            mainController.redrawCanvas();
        });
        textBaseline.valueProperty().addListener((o, oldValue, newValue) -> {
            ((HorizontalText) drawable).setTextBaseline(newValue);
            mainController.redrawCanvas();
        });
    }

    public void initDrawable() {
        this.text.setText(((HorizontalText) drawable).getText());
        Font font = ((HorizontalText) drawable).getFont();
        this.fontName.setText(font.getName());
        this.fontSize.getValueFactory().setValue(font.getSize());
        this.color.setValue((Color) ((HorizontalText) drawable).getPaint());
        this.x.getValueFactory().setValue(((HorizontalText) drawable).getX());
        this.y.getValueFactory().setValue(((HorizontalText) drawable).getY());
        this.textAlignment.setValue(((HorizontalText) drawable).getTextAlignment());
        this.textBaseline.setValue(((HorizontalText) drawable).getTextBaseline());
    }
}
