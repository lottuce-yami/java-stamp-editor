package moe.lottuce.stampeditor.drawables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import moe.lottuce.stampeditor.io.PaintConverter;

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

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(contentConverter = PaintConverter.class)
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
