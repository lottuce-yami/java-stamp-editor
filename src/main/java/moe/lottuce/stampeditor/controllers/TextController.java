/*
package moe.lottuce.stampeditor.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import moe.lottuce.stampeditor.drawables.Drawable;
import moe.lottuce.stampeditor.drawables.Text;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class TextController implements DrawableController, Initializable {
    @FXML
    protected TextField text;

    @FXML
    protected TextField fontName;

    @FXML
    protected Spinner<Double> fontSize;

    @FXML
    protected ColorPicker color;

    protected Text drawable;

    @Override
    public void initDrawable(Drawable drawable) {
        if (drawable instanceof Text) {
            this.drawable = (Text) drawable;

            this.text.setText(((Text) drawable).getText());
            this.fontName.setText(((Text) drawable).getFont().getName());
            this.fontSize.getValueFactory().setValue(((Text) drawable).getFont().getSize());
            this.color.setValue((Color) ((Text) drawable).getPaint());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
*/
