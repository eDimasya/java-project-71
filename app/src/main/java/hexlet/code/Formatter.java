package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Formatter {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON = "json";

    public static String prettyPrint(
            LinkedHashMap<Map.Entry<String, Differ.KeyAttribute>, Object[]> map,
            String format) throws IOException {
        return switch (format) {
            case STYLISH -> Stylish.prettyPrint(map);
            case PLAIN -> Plain.prettyPrint(map);
            case JSON -> Json.prettyPrint(map);
            default -> null;
        };
    }
}
