package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.Comparator;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        return Formatter.prettyPrint(
                compareAndSort(
                        Objects.requireNonNull(Parser.parseContent(
                                Files.readString(Path.of(filepath1)),
                                filepath1.substring(filepath1.lastIndexOf(".") + 1))),
                        Objects.requireNonNull(Parser.parseContent(
                                Files.readString(Path.of(filepath2)),
                                filepath2.substring(filepath2.lastIndexOf(".") + 1)))),
                format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, Formatter.STYLISH);
    }

    public static List<Map<String, Object>> compareAndSort(
            HashMap<String, Object> data1,
            HashMap<String, Object> data2) {
        List<Map<String, Object>> compared = new ArrayList<>();
        data1.forEach((key, value) -> {
            if (data2.containsKey(key)) {
                if (Objects.equals(data2.get(key), value)) {
                    Map<String, Object> notChanged = new HashMap<>();
                    notChanged.put("key", key);
                    notChanged.put("type", KeyAttribute.NOT_CHANGED);
                    notChanged.put("value", value);
                    compared.add(notChanged);
                } else {
                    Map<String, Object> changed = new HashMap<>();
                    changed.put("key", key);
                    changed.put("type", KeyAttribute.CHANGED);
                    changed.put("oldValue", value);
                    changed.put("newValue", data2.get(key));
                    compared.add(changed);
                }
            } else {
                Map<String, Object> removed = new HashMap<>();
                removed.put("key", key);
                removed.put("type", KeyAttribute.REMOVED);
                removed.put("value", value);
                compared.add(removed);
            }
        });
        data2.forEach((key, value) -> {
            if (!data1.containsKey(key)) {
                Map<String, Object> added = new HashMap<>();
                added.put("key", key);
                added.put("type", KeyAttribute.ADDED);
                added.put("value", value);
                compared.add(added);
            }
        });
        compared.sort(Comparator.comparing(o ->
                o.get("key").toString()));
        return compared;
    }

}
