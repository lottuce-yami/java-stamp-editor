package moe.lottuce.stampeditor.drawable;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class RectangularFrame extends Frame {
    private double x;

    private double y;

    private double width;

    private double height;

    public RectangularFrame() {
        super();
        x = 5;
        y = 55;
        width = 290;
        height = 190;
    }

    public RectangularFrame(double strokeWidth, Paint paint, double x, double y, double width, double height) {
        super(strokeWidth, paint);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(paint);
        gc.setLineWidth(strokeWidth);

        gc.strokeRect(x ,y, width, height);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
