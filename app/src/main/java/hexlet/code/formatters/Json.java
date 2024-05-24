package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.KeyAttribute;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json {
    public static String prettyPrint(
            List<Map<String, Object>> list)
            throws IOException {
        LinkedHashMap<String, Object> prettyMap = new LinkedHashMap<>();
        list.forEach(element -> {
            switch ((KeyAttribute) element.get("type")) {
                case ADDED -> {
                    prettyMap
                            .put("+ " + element.get("key"),
                                    element.get("value"));
                }
                case CHANGED -> {
                    prettyMap
                            .put("- " + element.get("key"),
                                    element.get("oldValue"));
                    prettyMap.put("+ " + element.get("key"),
                            element.get("newValue"));
                }
                case REMOVED -> {
                    prettyMap.put("- " + element.get("key"),
                            element.get("value"));
                }
                default -> {
                    prettyMap.put(element.get("key").toString(),
                            element.get("value"));
                }
            }
        });
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(prettyMap);
    }
}
