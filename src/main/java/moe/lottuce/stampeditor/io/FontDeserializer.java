package moe.lottuce.stampeditor.io;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.scene.text.Font;

import java.io.IOException;

public class FontDeserializer extends StdDeserializer<Font> {
    public FontDeserializer() {
        this(null);
    }

    protected FontDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Font deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String name = node.get("name").asText();
        double size = node.get("size").asDouble();

        return new Font(name, size);
    }
}
