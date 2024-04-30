package moe.lottuce.stampeditor.drawable;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

import java.util.Arrays;

public final class CircularText extends Text {
    private double diameter;

    private double startAngle;

    private double endAngle;

    private double tracking;

    public CircularText() {
        super();
        diameter = 100;
        startAngle = 180;
        endAngle = 360;
        tracking = 1;
    }

    public CircularText(String text, Font font, Paint paint, double diameter, double startAngle, double endAngle, double tracking) {
        super(text, font, paint);
        setDiameter(diameter);
        setStartAngle(startAngle);
        setEndAngle(endAngle);
        setTracking(tracking);
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double radius = diameter / 2;
        double circlePerimeter = 2 * Math.PI * radius;
        double angleInPixels = circlePerimeter / 360;
        double angleArea = Math.abs(startAngle - endAngle);

        double[] charsWidth = calculateCharsWidth(text, font, tracking);
        int spaceCount = text.length() - text.replace(" ", "").length();

        double textWidthInAngles = Arrays.stream(charsWidth).sum() / angleInPixels;
        double emptyAreaInAngles = angleArea - textWidthInAngles;
        double spaceWidthInAngles = emptyAreaInAngles / spaceCount;

        gc.setFont(font);
        gc.setFill(paint);

        double currentAngle = startAngle;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ' ') {
                currentAngle += spaceWidthInAngles;
                continue;
            }

            double[] coordinates = getPointAtAngle(radius, currentAngle, canvas.getWidth() / 2, canvas.getHeight() / 2);
            double x = coordinates[0];
            double y = coordinates[1];

            Rotate r = new Rotate(currentAngle + 90, x, y);
            gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

            gc.fillText(String.valueOf(c), x, y);
            currentAngle += charsWidth[i] / angleInPixels;
        }

        gc.setFont(Font.getDefault());
        gc.setFill(Color.BLACK);
        gc.setTransform(new Affine());
    }

    private static double[] getPointAtAngle(double radius, double angle, double xOffset, double yOffset) {
        double x = radius * Math.cos(Math.toRadians(angle));
        double y = radius * Math.sin(Math.toRadians(angle));

        x += xOffset;
        y += yOffset;

        return new double[]{x, y};
    }

    private static double[] calculateCharsWidth(String text, Font font, double tracking) {
        javafx.scene.text.Text sceneText = new javafx.scene.text.Text();
        sceneText.setFont(font);

        sceneText.setText(" ");
        double trackingInPixels = (sceneText.getLayoutBounds().getHeight() / font.getSize()) * tracking;

        double[] charsWidth = new double[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ' ') {
                charsWidth[i-1] -= trackingInPixels;
                continue;
            }

            sceneText.setText(String.valueOf(c));
            charsWidth[i] = sceneText.getLayoutBounds().getWidth() + trackingInPixels;

            if (i == text.length() - 1) {
                charsWidth[i] -= trackingInPixels;
            }
        }

        return charsWidth;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public double getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(double endAngle) {
        this.endAngle = endAngle;
    }

    public double getTracking() {
        return tracking;
    }

    public void setTracking(double tracking) {
        this.tracking = tracking;
    }
}
