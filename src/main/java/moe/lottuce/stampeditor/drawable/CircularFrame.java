package moe.lottuce.stampeditor.drawable;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public final class CircularFrame extends Frame {
    private double diameter;

    public CircularFrame() {
        super();
        diameter = 290;
    }

    public CircularFrame(double strokeWidth, Paint paint, double diameter) {
        super(strokeWidth, paint);
        setDiameter(diameter);
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double radius = diameter / 2;

        gc.setStroke(paint);
        gc.setLineWidth(strokeWidth);

        gc.strokeOval(
                canvas.getWidth() / 2 - radius,
                canvas.getHeight() / 2 - radius,
                diameter,
                diameter
        );

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }
}
