package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Formatter {
    public static final String STYLISH = "stylish";

    public static String prettyPrint(LinkedHashMap<Map.Entry<String, String>, Object> map, String format) {
        return switch (format) {
            case STYLISH -> prettyPrintStylish(map);
            default -> prettyPrintStylish(map);
        };
    }

    private static String prettyPrintStylish(LinkedHashMap<Map.Entry<String, String>, Object> map) {
        StringBuilder pretty = new StringBuilder();
        pretty.append("{").append(System.lineSeparator());
        map.forEach((key, value) -> pretty.append("\t")
                .append(key.getValue())
                .append(" ")
                .append(key.getKey())
                .append(": ")
                .append(printValueStylish(value))
                .append(System.lineSeparator()));
        pretty.append("}");
        return pretty.toString();
    }

    private static String printValueStylish(Object value) {
        if (value instanceof TextNode) {
            return ((TextNode) value).asText();
        }
        if (value instanceof ArrayNode) {
            return String.valueOf(new ObjectMapper().convertValue(value, ArrayList.class));
        }
        if (value instanceof ObjectNode) {
            return String.valueOf(new ObjectMapper().convertValue(value, LinkedHashMap.class));
        }
        return value.toString();
    }
}
