package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Differ {

    public enum KeyAttribute {
        ADDED, REMOVED, NOT_CHANGED, CHANGED
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        return Formatter.prettyPrint(
                sort(compare(
                        Objects.requireNonNull(Parser.parseContent(
                                Files.readString(Path.of(filepath1)),
                                filepath1.substring(filepath1.lastIndexOf(".") + 1))),
                        Objects.requireNonNull(Parser.parseContent(
                                Files.readString(Path.of(filepath2)),
                                filepath2.substring(filepath2.lastIndexOf(".") + 1))))),
                format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, Formatter.STYLISH);
    }

    private static LinkedHashMap<Map.Entry<String, KeyAttribute>, Object[]> sort(
            LinkedHashMap<Map.Entry<String, KeyAttribute>, Object[]> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparing(element ->
                        element.getKey().getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (m1, m2) -> m1,
                        LinkedHashMap::new));
    }

    private static LinkedHashMap<Map.Entry<String, KeyAttribute>, Object[]> compare(
            HashMap<String, Object> data1, HashMap<String, Object> data2) {
        LinkedHashMap<Map.Entry<String, KeyAttribute>, Object[]> resultMap = new LinkedHashMap<>();
        data1.forEach((key, value) -> {
            if (data2.containsKey(key)) {
                if (Objects.equals(data2.get(key), value)) {
                    resultMap.put(Map.entry(key, KeyAttribute.NOT_CHANGED),
                            new Object[]{value});
                } else {
                    resultMap.put(Map.entry(key, KeyAttribute.CHANGED),
                            new Object[]{value, data2.get(key)});
                }
            } else {
                resultMap.put(Map.entry(key, KeyAttribute.REMOVED),
                        new Object[]{value});
            }
        });
        data2.forEach((key, value) -> {
            if (!data1.containsKey(key)) {
                resultMap.put(Map.entry(key, KeyAttribute.ADDED),
                        new Object[]{value});
            }
        });
        return resultMap;
    }
}
