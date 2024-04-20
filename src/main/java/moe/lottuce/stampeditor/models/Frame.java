package moe.lottuce.stampeditor.models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class Frame implements Drawable {
    protected final Canvas canvas;

    protected final GraphicsContext gc;

    protected double width;

    protected Paint paint;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Frame(Canvas canvas, double width, Paint paint) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        setWidth(width);
        setPaint(paint);
    }

    public abstract void draw();
}
