/*
package moe.lottuce.stampeditor.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.Frame;

public abstract class FrameController {
    @FXML
    protected Spinner<Double> width;

    @FXML
    protected ColorPicker color;

    protected Frame drawable;

    public void initialize() {}

    public void initDrawable(Drawable drawable) {
        if (drawable instanceof Frame) {
            this.drawable = (Frame) drawable;

            this.width.getValueFactory().setValue(((Frame) drawable).getWidth());
            this.color.setValue((Color) ((Frame) drawable).getPaint());
        }
    }
}
*/
