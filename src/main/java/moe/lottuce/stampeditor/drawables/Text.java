package moe.lottuce.stampeditor.drawables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import moe.lottuce.stampeditor.io.FontDeserializer;
import moe.lottuce.stampeditor.io.FontSerializer;
import moe.lottuce.stampeditor.io.PaintConverter;

public abstract class Text implements Drawable {
    protected String text;

    @JsonSerialize(using = FontSerializer.class)
    @JsonDeserialize(using = FontDeserializer.class)
    protected Font font;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(contentConverter = PaintConverter.class)
    protected Paint paint;

    public Text() {
        text = "Example text";
        font = Font.getDefault();
        paint = Color.BLACK;
    }

    public Text(String text, Font font, Paint paint) {
        setText(text);
        setFont(font);
        setPaint(paint);
    }

    @Override
    public abstract void draw(Canvas canvas);

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
