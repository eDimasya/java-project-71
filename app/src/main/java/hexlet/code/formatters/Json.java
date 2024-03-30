package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static hexlet.code.Differ.KeyAttribute;

public class Json {
    public static String prettyPrint(LinkedHashMap<Map.Entry<String, KeyAttribute>, Map.Entry<Object, Object>> map)
            throws IOException {
        LinkedHashMap<String, Object> prettyMap = new LinkedHashMap<>();
        map.forEach((key, value) -> {
            switch (key.getValue()) {
                case ADDED -> {
                    prettyMap
                            .put("+ " + key.getKey(),
                                    value.getKey());
                }
                case CHANGED -> {
                    prettyMap
                            .put("- " + key.getKey(),
                                    value.getKey());
                    prettyMap.put("+ " + key.getKey(),
                            value.getValue());
                }
                case REMOVED -> {
                    prettyMap.put("- " + key.getKey(),
                            value.getKey());
                }
                default -> {
                    prettyMap.put(key.getKey(),
                            value.getKey());
                }
            }
        });
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(prettyMap);
    }
}
