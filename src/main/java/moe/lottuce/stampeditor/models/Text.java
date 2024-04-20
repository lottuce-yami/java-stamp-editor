package moe.lottuce.stampeditor.models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public abstract class Text implements Drawable {
    protected final Canvas canvas;

    protected final GraphicsContext gc;

    protected String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    protected Font font;

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    protected Paint paint;

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Text(Canvas canvas, String text, Font font, Paint paint) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        setText(text);
        setFont(font);
        setPaint(paint);
    }

    public abstract void draw();
}
