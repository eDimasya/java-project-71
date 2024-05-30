package hexlet.code;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class Diff {
    public static List<Map<String, Object>> compare(
            Map<String, Object> data1,
            Map<String, Object> data2) {
        List<Map<String, Object>> compared = new LinkedList<>();
        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());
        keys.forEach(key -> {
            Map<String, Object> value = new LinkedHashMap<>();
            value.put("key", key);
            if (!data2.containsKey(key)) {
                value.put("type", KeyAttribute.REMOVED);
                value.put("value", data1.get(key));
                compared.add(value);
            }
            if (!data1.containsKey(key)) {
                value.put("type", KeyAttribute.ADDED);
                value.put("value", data2.get(key));
                compared.add(value);
            }
            if (data1.containsKey(key) && data2.containsKey(key)) {
                if (Objects.equals(data1.get(key), data2.get(key))) {
                    value.put("type", KeyAttribute.NOT_CHANGED);
                    value.put("value", data1.get(key));
                    compared.add(value);
                }
                if (!Objects.equals(data1.get(key), data2.get(key))) {
                    value.put("type", KeyAttribute.CHANGED);
                    value.put("value1", data1.get(key));
                    value.put("value2", data2.get(key));
                    compared.add(value);
                }
            }
        });
        return compared;
    }
}
