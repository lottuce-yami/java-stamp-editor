package moe.lottuce.stampeditor.drawables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import moe.lottuce.stampeditor.io.PaintConverter;

public abstract class Frame implements Drawable {
    protected double width;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(contentConverter = PaintConverter.class)
    protected Paint paint;

    public Frame() {
        width = 1;
        paint = Color.BLACK;
    }

    public Frame(double width, Paint paint) {
        setWidth(width);
        setPaint(paint);
    }

    @Override
    public abstract void draw(Canvas canvas);

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
}
