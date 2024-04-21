package moe.lottuce.stampeditor.drawables;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

public abstract class Frame implements Drawable {
    protected double width;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    protected Paint paint;

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Frame() {

    }

    public Frame(double width, Paint paint) {
        setWidth(width);
        setPaint(paint);
    }

    @Override
    public abstract void draw(Canvas canvas);
}
