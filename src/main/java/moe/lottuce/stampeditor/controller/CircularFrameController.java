package moe.lottuce.stampeditor.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;
import moe.lottuce.stampeditor.drawable.*;

public class CircularFrameController extends DrawableController {
    @FXML
    protected Spinner<Double> strokeWidth;

    @FXML
    protected ColorPicker color;

    @FXML
    private Spinner<Double> diameter;

    @FXML
    private Button removeButton;

    @FXML
    private void initialize() {
        strokeWidth.valueProperty().addListener((o, oldValue, newValue) -> {
            ((CircularFrame) drawable).setStrokeWidth(newValue);
            mainController.redrawCanvas();
        });
        color.valueProperty().addListener((o, oldValue, newValue) -> {
            ((CircularFrame) drawable).setPaint(newValue);
            mainController.redrawCanvas();
        });
        diameter.valueProperty().addListener((o, oldValue, newValue) -> {
            ((CircularFrame) drawable).setDiameter(newValue);
            mainController.redrawCanvas();
        });

        removeButton.setOnAction((actionEvent) -> mainController.removeDrawable(drawable));
    }

    @Override
    public void initDrawable() {
        this.strokeWidth.getValueFactory().setValue(((CircularFrame) drawable).getStrokeWidth());
        this.color.setValue((Color) ((CircularFrame) drawable).getPaint());
        this.diameter.getValueFactory().setValue(((CircularFrame) drawable).getDiameter());
    }
}
