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
        LinkedHashMap<Map.Entry<String, KeyAttribute>, Object[]> map = new LinkedHashMap<>();
        map.put(Map.entry("follow", REMOVED), new Object[] {false});
        map.put(Map.entry("host", NOT_CHANGED), new Object[] {"hexlet.io"});
        map.put(Map.entry("proxy", REMOVED), new Object[] {"123.234.53.22"});
        map.put(Map.entry("timeout", CHANGED), new Object[] {1, 2});
        map.put(Map.entry("verbose", ADDED), new Object[] {true});
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 1
                  + timeout: 2
                  + verbose: true
                }""";
        String actual = Formatter.prettyPrint(map, Formatter.STYLISH);
        Assertions.assertEquals(expected, actual);
    }
}
