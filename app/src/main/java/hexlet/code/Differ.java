package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    private static final String ADDED = "+";
    private static final String REMOVED = "-";
    private static final String NOT_CHANGED = " ";

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        return Formatter.prettyPrint(
                sort(
                        compare(
                                Parser.getContent(filepath1),
                                Parser.getContent(filepath2))),
                format);
    }

    private static LinkedHashMap<Map.Entry<String, String>, Object> sort(
            LinkedHashMap<Map.Entry<String, String>, Object> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparing(element ->
                        element.getKey().getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (m1, m2) -> m1,
                        LinkedHashMap::new));
    }

    private static LinkedHashMap<Map.Entry<String, String>, Object> compare(JsonNode data1,
                                                                            JsonNode data2) {
        LinkedHashMap<Map.Entry<String, String>, Object> resultMap = new LinkedHashMap<>();
        data1.fields().forEachRemaining(entry -> {
            if (data2.has(entry.getKey())) {
                if (data2.get(entry.getKey()).toString()
                        .equals(entry.getValue().toString())) {
                    resultMap.put(Map.entry(entry.getKey(), NOT_CHANGED),
                            entry.getValue());
                } else {
                    resultMap.put(Map.entry(entry.getKey(), REMOVED),
                            entry.getValue());
                    resultMap.put(Map.entry(entry.getKey(), ADDED),
                            data2.get(entry.getKey()));
                }
            } else {
                resultMap.put(Map.entry(entry.getKey(), REMOVED),
                        entry.getValue());
            }
        });
        data2.fields().forEachRemaining(entry -> {
            if (!data1.has(entry.getKey())) {
                resultMap.put(Map.entry(entry.getKey(), ADDED), entry.getValue());
            }
        });
        return resultMap;
    }
}
