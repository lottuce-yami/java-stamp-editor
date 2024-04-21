package moe.lottuce.stampeditor.drawables;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public abstract class Text implements Drawable {
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

    public Text() {

    }

    public Text(String text, Font font, Paint paint) {
        setText(text);
        setFont(font);
        setPaint(paint);
    }

    @Override
    public abstract void draw(Canvas canvas);
}
