package moe.lottuce.stampeditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import moe.lottuce.stampeditor.drawables.CircularText;
import moe.lottuce.stampeditor.drawables.Drawable;

import java.net.URL;
import java.util.ResourceBundle;

public class CircularTextController extends TextController {
    @FXML
    private Spinner diameter;

    @FXML
    private Spinner startAngle;

    @FXML
    private Spinner endAngle;

    @FXML
    private Spinner tracking;

    @Override
    public void initDrawable(Drawable drawable) {
        super.initDrawable(drawable);

        if (drawable instanceof CircularText) {
            this.diameter.getValueFactory().setValue(((CircularText) drawable).getDiameter());
            this.startAngle.getValueFactory().setValue(((CircularText) drawable).getStartAngle());
            this.endAngle.getValueFactory().setValue(((CircularText) drawable).getEndAngle());
            this.tracking.getValueFactory().setValue(((CircularText) drawable).getTracking());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
