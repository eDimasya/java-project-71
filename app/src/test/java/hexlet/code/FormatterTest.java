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
        LinkedHashMap<Map.Entry<String, KeyAttribute>, Object[]> mapFlat = new LinkedHashMap<>();
        mapFlat.put(Map.entry("follow", REMOVED), new Object[] {false});
        mapFlat.put(Map.entry("host", NOT_CHANGED), new Object[] {"hexlet.io"});
        mapFlat.put(Map.entry("proxy", REMOVED), new Object[] {"123.234.53.22"});
        mapFlat.put(Map.entry("timeout", CHANGED), new Object[] {1, 2});
        mapFlat.put(Map.entry("verbose", ADDED), new Object[] {true});
        String expectedFlatStylish = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 1
                  + timeout: 2
                  + verbose: true
                }""";
        String actualFlatStylish = Formatter.prettyPrint(mapFlat, Formatter.STYLISH);
        Assertions.assertEquals(expectedFlatStylish, actualFlatStylish);

        LinkedHashMap<Map.Entry<String, KeyAttribute>, Object[]> mapNested = new LinkedHashMap<>();
        mapNested.put(Map.entry("checked", REMOVED), new Object[] {false});
        mapNested.put(Map.entry("setting1", CHANGED), new Object[] {new Object[] {1, 2}, 2});
        mapNested.put(Map.entry("setting2", CHANGED), new Object[] {true, "none"});
        mapNested.put(Map.entry("setting3", ADDED), new Object[] {new Object[] {"a", "b", "c"}});
        String expectedNestedPlain = """
                Property 'checked' was removed
                Property 'setting1' was updated. From [complex value] to 2
                Property 'setting2' was updated. From true to 'none'
                Property 'setting3' was added with value: [complex value]""";
        String actualNestedPlain = Formatter.prettyPrint(mapNested, Formatter.PLAIN);
        Assertions.assertEquals(expectedNestedPlain, actualNestedPlain);
    }
}
