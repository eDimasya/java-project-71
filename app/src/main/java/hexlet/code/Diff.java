package hexlet.code;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

import static hexlet.code.KeyAttribute.ADDED;
import static hexlet.code.KeyAttribute.CHANGED;
import static hexlet.code.KeyAttribute.NOT_CHANGED;
import static hexlet.code.KeyAttribute.REMOVED;

public class Diff {
    public static List<Map<String, Object>> compare(
            Map<String, Object> data1,
            Map<String, Object> data2) {
        List<Map<String, Object>> compared = new LinkedList<>();
        TreeSet<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        keys.forEach(key -> {
            Map<String, Object> value = new LinkedHashMap<>();
            value.put("key", key);
            if (!data2.containsKey(key)) {
                value.put("type", REMOVED);
                value.put("value", data1.get(key));
                compared.add(value);
            } else if (!data1.containsKey(key)) {
                value.put("type", ADDED);
                value.put("value", data2.get(key));
                compared.add(value);
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                value.put("type", NOT_CHANGED);
                value.put("value", data1.get(key));
                compared.add(value);
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                value.put("type", CHANGED);
                value.put("value1", data1.get(key));
                value.put("value2", data2.get(key));
                compared.add(value);
            }
        });
        return compared;
    }
}
