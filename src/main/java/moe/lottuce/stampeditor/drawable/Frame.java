package moe.lottuce.stampeditor.drawable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import moe.lottuce.stampeditor.io.PaintConverter;

public abstract class Frame implements Drawable {
    protected double strokeWidth;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(contentConverter = PaintConverter.class)
    protected Paint paint;

    public Frame() {
        strokeWidth = 1;
        paint = Color.BLACK;
    }

    public Frame(double strokeWidth, Paint paint) {
        setStrokeWidth(strokeWidth);
        setPaint(paint);
    }

    @Override
    public abstract void draw(Canvas canvas);

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
