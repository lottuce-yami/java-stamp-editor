package moe.lottuce.stampeditor.drawable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.scene.canvas.Canvas;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RectangularFrame.class, name = "RectangularFrame"),
        @JsonSubTypes.Type(value = CircularFrame.class, name = "CircularFrame"),
        @JsonSubTypes.Type(value = HorizontalText.class, name = "HorizontalText"),
        @JsonSubTypes.Type(value = CircularText.class, name = "CircularText")
})
public interface Drawable {
    void draw(Canvas canvas);
}
