package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DifferTest {

    @Test
    void generate() throws IOException {
        //Flat
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
                Differ.generate("src/test/resources/file1_flat.json",
                        "src/test/resources/file2_flat.json",
                        Formatter.STYLISH));
        Assertions.assertEquals(expected,
                Differ.generate("src/test/resources/file1_flat.yml",
                        "src/test/resources/file2_flat.yml",
                        Formatter.STYLISH));
        //Nested
        expected = """
                {
                \t  chars1: [a, b, c]
                \t- chars2: [d, e, f]
                \t+ chars2: false
                \t- checked: false
                \t+ checked: true
                \t- default: null
                \t+ default: [value1, value2]
                \t- id: 45
                \t+ id: null
                \t- key1: value1
                \t+ key2: value2
                \t  numbers1: [1, 2, 3, 4]
                \t- numbers2: [2, 3, 4, 5]
                \t+ numbers2: [22, 33, 44, 55]
                \t- numbers3: [3, 4, 5]
                \t+ numbers4: [4, 5, 6]
                \t+ obj1: {nestedKey=value, isNested=true}
                \t- setting1: Some value
                \t+ setting1: Another value
                \t- setting2: 200
                \t+ setting2: 300
                \t- setting3: true
                \t+ setting3: none
                }""";
        Assertions.assertEquals(expected,
                Differ.generate("src/test/resources/file1_nested.yml",
                        "src/test/resources/file2_nested.yml",
                        Formatter.STYLISH));
        Assertions.assertEquals(expected,
                Differ.generate("src/test/resources/file1_nested.json",
                        "src/test/resources/file2_nested.json",
                        Formatter.STYLISH));
    }

    /*@Test
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
        LinkedHashMap<String, String> actual = Differ.compare(map1, map2);
        Assertions.assertEquals(expected, actual);
    }*/

    /*@Test
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
        Assertions.assertEquals(expected, Differ.sort(map));
    }*/
}
