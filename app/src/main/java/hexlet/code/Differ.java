package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    public enum KeyAttribute {
        ADDED, REMOVED, NOT_CHANGED, CHANGED
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        return Formatter.prettyPrint(
                sort(
                        compare(
                                Parser.getContent(filepath1),
                                Parser.getContent(filepath2))),
                format);
    }

    private static LinkedHashMap<Map.Entry<String, KeyAttribute>, Map.Entry<Object, Object>> sort(
            LinkedHashMap<Map.Entry<String, KeyAttribute>, Map.Entry<Object, Object>> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparing(element ->
                        element.getKey().getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (m1, m2) -> m1,
                        LinkedHashMap::new));
    }

    private static LinkedHashMap<Map.Entry<String, KeyAttribute>, Map.Entry<Object, Object>> compare(JsonNode data1,
                                                                                                     JsonNode data2) {
        LinkedHashMap<Map.Entry<String, KeyAttribute>, Map.Entry<Object, Object>> resultMap = new LinkedHashMap<>();
        data1.fields().forEachRemaining(data1entry -> {
            if (data2.has(data1entry.getKey())) {
                if (data2.get(data1entry.getKey()).equals(data1entry.getValue())) {
                    resultMap.put(Map.entry(data1entry.getKey(), KeyAttribute.NOT_CHANGED),
                            Map.entry(data1entry.getValue(), data1entry.getValue()));
                } else {
                    resultMap.put(Map.entry(data1entry.getKey(), KeyAttribute.CHANGED),
                            Map.entry(data1entry.getValue(), data2.get(data1entry.getKey())));
                }
            } else {
                resultMap.put(Map.entry(data1entry.getKey(), KeyAttribute.REMOVED),
                        Map.entry(data1entry.getValue(), data1entry.getValue()));
            }
        });
        data2.fields().forEachRemaining(data2entry -> {
            if (!data1.has(data2entry.getKey())) {
                resultMap.put(Map.entry(data2entry.getKey(), KeyAttribute.ADDED),
                        Map.entry(data2entry.getValue(), data2entry.getValue()));
            }
        });
        return resultMap;
    }
}
