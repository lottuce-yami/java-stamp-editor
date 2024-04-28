package moe.lottuce.stampeditor.drawables;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public final class HorizontalText extends Text {
    private double x;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    private double y;

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    private TextAlignment textAlignment;

    public TextAlignment getTextAlignment() {
        return textAlignment;
    }

    public void setTextAlignment(TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
    }

    private VPos textBaseline;

    public VPos getTextBaseline() {
        return textBaseline;
    }

    public void setTextBaseline(VPos textBaseline) {
        this.textBaseline = textBaseline;
    }

    public HorizontalText() {
        super();
        x = 100;
        y = 100;
        textAlignment = TextAlignment.LEFT;
        textBaseline = VPos.BASELINE;
    }

    public HorizontalText(String text, Font font, Paint paint, TextAlignment textAlignment, VPos textBaseline, double x, double y) {
        super(text, font, paint);
        setX(x);
        setY(y);
        setTextAlignment(textAlignment);
        setTextBaseline(textBaseline);
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setTextAlign(textAlignment);
        gc.setTextBaseline(textBaseline);
        gc.setFont(font);
        gc.setFill(paint);

        gc.fillText(text, x, y);

        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.BASELINE);
        gc.setFont(Font.getDefault());
        gc.setFill(Color.BLACK);
    }
}
