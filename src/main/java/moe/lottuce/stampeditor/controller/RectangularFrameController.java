package moe.lottuce.stampeditor.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;
import moe.lottuce.stampeditor.drawable.RectangularFrame;

public class RectangularFrameController extends DrawableController {
    @FXML
    private Spinner<Double> strokeWidth;

    @FXML
    private ColorPicker color;

    @FXML
    private Spinner<Double> x;

    @FXML
    private Spinner<Double> y;

    @FXML
    private Spinner<Double> width;

    @FXML
    private Spinner<Double> height;

    @FXML
    private Button removeButton;

    @FXML
    private void initialize() {
        strokeWidth.valueProperty().addListener((o, oldValue, newValue) -> {
            ((RectangularFrame) drawable).setStrokeWidth(newValue);
            mainController.redrawCanvas();
        });
        color.valueProperty().addListener((o, oldValue, newValue) -> {
            ((RectangularFrame) drawable).setPaint(newValue);
            mainController.redrawCanvas();
        });
        x.valueProperty().addListener((o, oldValue, newValue) -> {
            ((RectangularFrame) drawable).setX(newValue);
            mainController.redrawCanvas();
        });
        y.valueProperty().addListener((o, oldValue, newValue) -> {
            ((RectangularFrame) drawable).setY(newValue);
            mainController.redrawCanvas();
        });
        width.valueProperty().addListener((o, oldValue, newValue) -> {
            ((RectangularFrame) drawable).setWidth(newValue);
            mainController.redrawCanvas();
        });
        height.valueProperty().addListener((o, oldValue, newValue) -> {
            ((RectangularFrame) drawable).setHeight(newValue);
            mainController.redrawCanvas();
        });

        removeButton.setOnAction((actionEvent) -> mainController.removeDrawable(drawable));
    }

    @Override
    public void initDrawable() {
        this.strokeWidth.getValueFactory().setValue(((RectangularFrame) drawable).getStrokeWidth());
        this.color.setValue((Color) ((RectangularFrame) drawable).getPaint());
        this.x.getValueFactory().setValue(((RectangularFrame) drawable).getX());
        this.y.getValueFactory().setValue(((RectangularFrame) drawable).getY());
        this.width.getValueFactory().setValue(((RectangularFrame) drawable).getWidth());
        this.height.getValueFactory().setValue(((RectangularFrame) drawable).getHeight());
    }
}
