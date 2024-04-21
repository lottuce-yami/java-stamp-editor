package moe.lottuce.stampeditor.io;

import com.fasterxml.jackson.databind.util.StdConverter;
import javafx.scene.paint.Paint;

public class PaintConverter extends StdConverter<String, Paint> {
    @Override
    public Paint convert(String s) {
        return Paint.valueOf(s);
    }
}
