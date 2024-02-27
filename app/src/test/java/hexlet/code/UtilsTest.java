package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

class UtilsTest {

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

    @Test
    void getFileExtension() {
        Assertions.assertEquals(Utils.getFileExtension("src/test/resources/file1.json"), Parser.JSON_EXTENSION);
        Assertions.assertEquals(Utils.getFileExtension("src/test/resources/file3.yml"), Parser.YAML_EXTENSION);
    }
}
