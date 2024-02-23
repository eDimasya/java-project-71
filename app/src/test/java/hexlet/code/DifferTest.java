package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

class DifferTest {

    @Test
    void generate() throws IOException {
        String expected = """
                {
                \t- follow: false
                \t  host: hexlet.io
                \t- proxy: 123.234.53.22
                \t- timeout: 50
                \t+ timeout: 20
                \t+ verbose: true
                }""";
        Assertions.assertEquals(expected,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
    }

    @Test
    void compareMaps() {
        LinkedHashMap<String, String> map1 = new LinkedHashMap<>(
                Map.of("host", "hexlet.io",
                        "timeout", "50",
                        "proxy", "123.234.53.22",
                        "follow", "false"));
        LinkedHashMap<String, String> map2 = new LinkedHashMap<>(
                Map.of("timeout", "20",
                        "verbose", "true",
                        "host", "hexlet.io"));
        LinkedHashMap<String, String> expected = new LinkedHashMap<>(
                Map.of("- follow", "false",
                        "  host", "hexlet.io",
                        "- proxy", "123.234.53.22",
                        "- timeout", "50",
                        "+ timeout", "20",
                        "+ verbose", "true"));
        LinkedHashMap<String, String> actual = Differ.compareMaps(map1, map2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sortMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("- follow", "false");
        map.put("+ verbose", "true");
        map.put("- timeout", "50");
        map.put("  host", "hexlet.io");
        map.put("- proxy", "123.234.53.22");
        map.put("+ timeout", "20");
        LinkedHashMap<String, String> expected = new LinkedHashMap<>();
        expected.put("- follow", "false");
        expected.put("  host", "hexlet.io");
        expected.put("- proxy", "123.234.53.22");
        expected.put("- timeout", "50");
        expected.put("+ timeout", "20");
        expected.put("+ verbose", "true");
        Assertions.assertEquals(expected, Differ.sortMap(map));
    }
}
