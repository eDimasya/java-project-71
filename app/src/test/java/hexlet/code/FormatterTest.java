package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class FormatterTest {

    @Test
    void prettyPrint() {
        LinkedHashMap<Map.Entry<String, String>, Object> map = new LinkedHashMap<>();
        map.put(Map.entry("follow", "-"), false);
        map.put(Map.entry("host", " "), "hexlet.io");
        map.put(Map.entry("proxy", "-"), "123.234.53.22");
        map.put(Map.entry("timeout", "-"), 50);
        map.put(Map.entry("timeout", "+"), 20);
        map.put(Map.entry("verbose", "+"), true);
        String expected = """
                {
                \t- follow: false
                \t  host: hexlet.io
                \t- proxy: 123.234.53.22
                \t- timeout: 50
                \t+ timeout: 20
                \t+ verbose: true
                }""";
        String actual = Formatter.prettyPrint(map, Formatter.STYLISH);
        Assertions.assertEquals(expected, actual);
    }
}
