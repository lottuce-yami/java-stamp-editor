package moe.lottuce.stampeditor.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;

public class MainController {
    @FXML
    private Canvas stampCanvas;

    @FXML
    private TextField primaryTextField;

    @FXML
    protected void onPrimaryTextChanged(ActionEvent actionEvent) {
        GraphicsContext gc = stampCanvas.getGraphicsContext2D();

        double canvasSide = stampCanvas.getWidth();
        double circleDiameter = canvasSide - 5;
        double circleRadius = circleDiameter / 2;

        gc.strokeOval(
                canvasSide / 2 - circleRadius,
                canvasSide / 2 - circleRadius,
                circleDiameter,
                circleDiameter
        );

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
                primaryTextField.getText(),
                canvasSide / 2,
                canvasSide / 2
        );
    }
}
