package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static hexlet.code.Differ.KeyAttribute;
import static hexlet.code.Differ.KeyAttribute.REMOVED;
import static hexlet.code.Differ.KeyAttribute.NOT_CHANGED;
import static hexlet.code.Differ.KeyAttribute.CHANGED;
import static hexlet.code.Differ.KeyAttribute.ADDED;

class FormatterTest {
    @Test
    void prettyPrint() throws IOException {
        LinkedHashMap<Map.Entry<String, KeyAttribute>, Map.Entry<Object, Object>> map = new LinkedHashMap<>();
        map.put(Map.entry("follow", REMOVED), Map.entry(false, false));
        map.put(Map.entry("host", NOT_CHANGED), Map.entry("hexlet.io", "hexlet.io"));
        map.put(Map.entry("proxy", REMOVED), Map.entry("123.234.53.22", "123.234.53.22"));
        map.put(Map.entry("timeout", CHANGED), Map.entry(50, 20));
        map.put(Map.entry("verbose", ADDED), Map.entry(true, true));
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
