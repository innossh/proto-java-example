package innossh.proto.example;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import innossh.proto.example.struct.StructData;
import innossh.proto.example.struct.StructExample;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StructExampleTest {
    private static final String EXPECTED_JSON = "{\n" +
            "  \"name\": \"test\",\n" +
            "  \"content\": {\n" +
            "    \"key1\": \"value1\",\n" +
            "    \"key2\": 2.2\n" +
            "  }\n" +
            "}";

    @Test void testGetJsonFromTextData() throws InvalidProtocolBufferException {
        final String actual = new StructExample().getJsonFromTextData("test");
        // "content" field is not a json, just string
        assertNotEquals(EXPECTED_JSON, actual);

        JsonObject jsonObject = JsonParser.parseString(actual)
                .getAsJsonObject();
        JsonElement content = JsonParser.parseString(jsonObject.get("content").getAsString());
        jsonObject.add("content", content);
        assertEquals(EXPECTED_JSON, new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
    }

    @Test void testGetJsonFromStructData() throws InvalidProtocolBufferException {
        final String actual = new StructExample().getJsonFromStructData("test");
        assertEquals(EXPECTED_JSON, actual);

        final StructData.Builder original = StructData.newBuilder();
        JsonFormat.parser().merge(actual, original);
        assertEquals("test", original.getName());
        assertEquals("value1", original.getContent().getFieldsOrThrow("key1").getStringValue());
        assertEquals(2.2, original.getContent().getFieldsOrThrow("key2").getNumberValue());
    }
}
