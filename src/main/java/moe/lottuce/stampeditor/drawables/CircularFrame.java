package moe.lottuce.stampeditor.drawables;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public final class CircularFrame extends Frame {
    private double diameter;

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public CircularFrame(Canvas canvas, double width, Paint paint, double diameter) {
        super(canvas, width, paint);
        setDiameter(diameter);
    }

    @Override
    public void draw() {
        double radius = diameter / 2;

        gc.setStroke(paint);
        gc.setLineWidth(width);

        gc.strokeOval(
                canvas.getWidth() / 2 - radius,
                canvas.getHeight() / 2 - radius,
                diameter,
                diameter
        );

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
    }
}
