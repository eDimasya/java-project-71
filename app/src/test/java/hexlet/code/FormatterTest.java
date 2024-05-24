package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FormatterTest {
    @Test
    void prettyPrint() throws IOException {
        List<Map<String, Object>> listFlat = new ArrayList<>();
        Map<String, Object> changed = new HashMap<>();
        changed.put("key", "changedKey");
        changed.put("type", hexlet.code.KeyAttribute.CHANGED);
        changed.put("oldValue", "old value");
        changed.put("newValue", "new value");
        listFlat.add(changed);
        Map<String, Object> notChanged = new HashMap<>();
        notChanged.put("key", "notChangedKey");
        notChanged.put("type", hexlet.code.KeyAttribute.NOT_CHANGED);
        notChanged.put("value", false);
        listFlat.add(notChanged);
        Map<String, Object> removed = new HashMap<>();
        removed.put("key", "removedKey");
        removed.put("type", hexlet.code.KeyAttribute.REMOVED);
        removed.put("value", null);
        listFlat.add(removed);
        Map<String, Object> added = new HashMap<>();
        added.put("key", "addedKey");
        added.put("type", hexlet.code.KeyAttribute.ADDED);
        added.put("value", 0);
        listFlat.add(added);
        String expectedFlatStylish = """
                {
                  - changedKey: old value
                  + changedKey: new value
                    notChangedKey: false
                  - removedKey: null
                  + addedKey: 0
                }""";
        String actualFlatStylish = Formatter.prettyPrint(listFlat, Formatter.STYLISH);
        Assertions.assertEquals(expectedFlatStylish, actualFlatStylish);
        List<Map<String, Object>> listNested = new ArrayList<>(listFlat);
        Map<String, Object> addedNested = new HashMap<>();
        addedNested.put("key", "addedKeyNested");
        addedNested.put("type", hexlet.code.KeyAttribute.ADDED);
        addedNested.put("value", new Integer[] {1, 0});
        listNested.add(addedNested);
        String expectedNestedPlain = """
                Property 'changedKey' was updated. From 'old value' to 'new value'
                Property 'removedKey' was removed
                Property 'addedKey' was added with value: 0
                Property 'addedKeyNested' was added with value: [complex value]""";
        String actualNestedPlain = Formatter.prettyPrint(listNested, Formatter.PLAIN);
        Assertions.assertEquals(expectedNestedPlain, actualNestedPlain);
    }
}
