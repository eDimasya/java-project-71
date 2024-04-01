package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

class ParserTest {
    @Test
    void getContent() {
        final Map<String, Object> expectedMapFlat = new LinkedHashMap<>();
        expectedMapFlat.put("host", "hexlet.io");
        expectedMapFlat.put("timeout", 1);
        expectedMapFlat.put("proxy", "123.234.53.22");
        expectedMapFlat.put("follow", false);
        final JsonNode expectedFlat = new ObjectMapper().convertValue(expectedMapFlat, JsonNode.class);
        final Map<String, Object> expectedMapNested = new LinkedHashMap<>();
        expectedMapNested.put("setting1", "Another value");
        expectedMapNested.put("setting2", 1);
        expectedMapNested.put("setting3", "none");
        expectedMapNested.put("key2", "value2");
        expectedMapNested.put("numbers1", new int[]{1, 2, 0});
        expectedMapNested.put("numbers2", new int[]{2, 1, 1});
        expectedMapNested.put("id", null);
        expectedMapNested.put("default", new String[]{"value1", "value2"});
        expectedMapNested.put("checked", true);
        expectedMapNested.put("numbers4", new int[]{0, 1, 2});
        expectedMapNested.put("chars1", new String[]{"a", "b", "c"});
        expectedMapNested.put("chars2", false);
        expectedMapNested.put("obj1", new ObjectMapper().convertValue(
                new LinkedHashMap<String, Object>(
                        Map.of("nestedKey", "value",
                                "isNested", true)),
                JsonNode.class));
        JsonNode expectedNested = new ObjectMapper().convertValue(expectedMapNested, JsonNode.class);

        try {
            Assertions.assertEquals(expectedFlat,
                    Parser.getContent("src/test/resources/file1_flat.json"));
            Assertions.assertEquals(expectedFlat,
                    Parser.getContent("src/test/resources/file1_flat.yml"));
            Assertions.assertEquals(expectedNested,
                    Parser.getContent("src/test/resources/file2_nested.json"));
            Assertions.assertEquals(expectedNested,
                    Parser.getContent("src/test/resources/file2_nested.yml"));
            Assertions.assertThrows(IOException.class,
                    () ->
                            Parser.getContent("src/test/resources/fileAbsent.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
