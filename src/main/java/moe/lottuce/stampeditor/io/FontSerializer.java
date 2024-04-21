package moe.lottuce.stampeditor.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javafx.scene.text.Font;

import java.io.IOException;

public class FontSerializer extends StdSerializer<Font> {
    public FontSerializer() {
        this(null);
    }

    protected FontSerializer(Class<Font> t) {
        super(t);
    }

    @Override
    public void serialize(Font font, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", font.getName());
        jsonGenerator.writeFieldName("size");
        jsonGenerator.writeNumber(font.getSize());
        jsonGenerator.writeEndObject();
    }
}
