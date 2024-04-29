package moe.lottuce.stampeditor.controller;

import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import moe.lottuce.stampeditor.drawable.HorizontalText;

import java.util.Map;
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
    private ChoiceBox<String> textAlignment;

    @FXML
    private ChoiceBox<String> textBaseline;

    @FXML
    private Button removeButton;

    private static final ResourceBundle localization = ResourceBundle.getBundle("moe/lottuce/stampeditor/bundles/StampEditor");

    @FXML
    private void initialize() {
        Map<String, TextAlignment> alignmentMap = Map.of(
                localization.getString(TextAlignment.LEFT.name()), TextAlignment.LEFT,
                localization.getString(TextAlignment.CENTER.name()), TextAlignment.CENTER,
                localization.getString(TextAlignment.RIGHT.name()), TextAlignment.RIGHT,
                localization.getString(TextAlignment.JUSTIFY.name()), TextAlignment.JUSTIFY
        );
        Map<String, VPos> baselineMap = Map.of(
                localization.getString(VPos.TOP.name()), VPos.TOP,
                localization.getString(VPos.CENTER.name()), VPos.CENTER,
                localization.getString(VPos.BASELINE.name()), VPos.BASELINE,
                localization.getString(VPos.BOTTOM.name()), VPos.BOTTOM
        );

        textBaseline.getItems().addAll(baselineMap.keySet());
        textAlignment.getItems().addAll(alignmentMap.keySet());

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
            ((HorizontalText) drawable).setTextAlignment(alignmentMap.get(newValue));
            mainController.redrawCanvas();
        });
        textBaseline.valueProperty().addListener((o, oldValue, newValue) -> {
            ((HorizontalText) drawable).setTextBaseline(baselineMap.get(newValue));
            mainController.redrawCanvas();
        });

        removeButton.setOnAction((actionEvent) -> mainController.removeDrawable(drawable));
    }

    public void initDrawable() {
        this.text.setText(((HorizontalText) drawable).getText());

        Font font = ((HorizontalText) drawable).getFont();
        this.fontName.setText(font.getName());
        this.fontSize.getValueFactory().setValue(font.getSize());

        this.color.setValue((Color) ((HorizontalText) drawable).getPaint());
        this.x.getValueFactory().setValue(((HorizontalText) drawable).getX());
        this.y.getValueFactory().setValue(((HorizontalText) drawable).getY());
        this.textAlignment.setValue(localization.getString(((HorizontalText) drawable).getTextAlignment().name()));
        this.textBaseline.setValue(localization.getString(((HorizontalText) drawable).getTextBaseline().name()));
    }
}
