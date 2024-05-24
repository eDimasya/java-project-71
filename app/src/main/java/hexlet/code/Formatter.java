package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON = "json";

    public static String prettyPrint(
            List<Map<String, Object>> list,
            String format) throws IOException {
        return switch (format) {
            case STYLISH -> Stylish.prettyPrint(list);
            case PLAIN -> Plain.prettyPrint(list);
            case JSON -> Json.prettyPrint(list);
            default -> null;
        };
    }
}
