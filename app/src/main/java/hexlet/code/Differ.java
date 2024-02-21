package hexlet.code;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        LinkedHashMap<String, String> result =
                compareMaps(Utils.stringToJson(filepath1),
                        Utils.stringToJson(filepath2))
                        .entrySet().stream()
                        .sorted(Comparator.comparing(m -> m.getKey().substring(2)))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (m1, m2) -> m1, LinkedHashMap::new));
        return Utils.prettyPrintMap(result);
    }

    public static LinkedHashMap<String, String> compareMaps(LinkedHashMap<String, String> map1,
                                                            LinkedHashMap<String, String> map2) {
        LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
        map1.forEach((key, value) -> {
            if (map2.containsKey(key)) {
                if (map2.get(key).equals(value)) {
                    resultMap.put("  " + key, value);
                } else {
                    resultMap.put("- " + key, value);
                    resultMap.put("+ " + key, map2.get(key));
                }
            } else {
                resultMap.put("- " + key, value);
            }
        });
        map2.forEach((key, value) -> {
            if (!map1.containsKey(key)) {
                resultMap.put("+ " + key, value);
            }
        });
        return resultMap;
    }
}
