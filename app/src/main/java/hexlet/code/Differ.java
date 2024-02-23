package hexlet.code;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    static String added = "+ ";
    static String changed = "+ ";
    static String removed = "- ";
    static String notChanged = "  ";
    public static String generate(String filepath1, String filepath2) throws IOException {
        return Utils.prettyPrintMap(
                sortMap(compareMaps(
                        Utils.fileContentToMap(filepath1),
                        Utils.fileContentToMap(filepath2))));
    }

    public static LinkedHashMap<String, String> sortMap(LinkedHashMap<String, String> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparing(m ->
                        m.getKey().substring(
                                (m.getKey().startsWith(added)
                                        || m.getKey().startsWith(removed)
                                        || m.getKey().startsWith(notChanged)
                                        || m.getKey().startsWith(changed))
                                        ? 2 : 0)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (m1, m2) -> m1,
                        LinkedHashMap::new));
    }

    public static LinkedHashMap<String, String> compareMaps(LinkedHashMap<String, String> map1,
                                                            LinkedHashMap<String, String> map2) {
        LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
        map1.forEach((key, value) -> {
            if (map2.containsKey(key)) {
                if (map2.get(key).equals(value)) {
                    resultMap.put(notChanged + key, value);
                } else {
                    resultMap.put(removed + key, value);
                    resultMap.put(changed + key, map2.get(key));
                }
            } else {
                resultMap.put(removed + key, value);
            }
        });
        map2.forEach((key, value) -> {
            if (!map1.containsKey(key)) {
                resultMap.put(added + key, value);
            }
        });
        return resultMap;
    }
}
