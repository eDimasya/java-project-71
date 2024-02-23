package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;

class UtilsTest {

    @Test
    void fileContentToMap() {
        LinkedHashMap<String, String> expected = new LinkedHashMap<>();
        expected.put("host", "hexlet.io");
        expected.put("timeout", "50");
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", "false");
        try {
            LinkedHashMap<String, String> actual = Utils.fileContentToMap("src/test/resources/file1.json");
            Assertions.assertEquals(expected, actual);
            Assertions.assertThrows(IOException.class,
                    () ->
                            Utils.fileContentToMap("src/test/resources/file3.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void prettyPrintMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("- follow", "false");
        map.put("  host", "hexlet.io");
        map.put("- proxy", "123.234.53.22");
        map.put("- timeout", "50");
        map.put("+ timeout", "20");
        map.put("+ verbose", "true");
        String expected = """
                {
                \t- follow: false
                \t  host: hexlet.io
                \t- proxy: 123.234.53.22
                \t- timeout: 50
                \t+ timeout: 20
                \t+ verbose: true
                }""";
        String actual = Utils.prettyPrintMap(map);
        Assertions.assertEquals(expected, actual);
    }
}
