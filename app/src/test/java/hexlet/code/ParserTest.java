package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        final Map<String, Object> expectedFlat =
                new ObjectMapper().convertValue(expectedMapFlat, new TypeReference<>() {
                });
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
        Map<String, Object> expectedNested =
                new ObjectMapper().convertValue(expectedMapNested, new TypeReference<>() {
                });
        String file1flatJsonPath = "src/test/resources/file1_flat.json";
        String file1flatYmlPath = "src/test/resources/file1_flat.yml";
        String file2nestedJsonPath = "src/test/resources/file2_nested.json";
        String file2nestedYmlPath = "src/test/resources/file2_nested.yml";
        String fileAbsentPath = "src/test/resources/fileAbsent.json";
        try {
            Assertions.assertEquals(expectedFlat,
                    Parser.parseContent(Files.readString(Path.of(file1flatJsonPath)),
                            file1flatJsonPath.substring(file1flatJsonPath.lastIndexOf(".") + 1)));
            Assertions.assertEquals(expectedFlat,
                    Parser.parseContent(Files.readString(Path.of(file1flatYmlPath)),
                            file1flatYmlPath.substring(file1flatYmlPath.lastIndexOf(".") + 1)));
            Assertions.assertEquals(expectedNested,
                    Parser.parseContent(Files.readString(Path.of(file2nestedJsonPath)),
                            file2nestedJsonPath.substring(file2nestedJsonPath.lastIndexOf(".") + 1)));
            Assertions.assertEquals(expectedNested,
                    Parser.parseContent(Files.readString(Path.of(file2nestedYmlPath)),
                            file2nestedYmlPath.substring(file2nestedYmlPath.lastIndexOf(".") + 1)));
            Assertions.assertThrows(IOException.class,
                    () ->
                            Parser.parseContent(Files.readString(Path.of(fileAbsentPath)),
                                    fileAbsentPath.substring(fileAbsentPath.lastIndexOf(".") + 1)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
