package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static hexlet.code.Differ.KeyAttribute;

public class Stylish {
    public static String prettyPrint(
            LinkedHashMap<Map.Entry<String, KeyAttribute>, Map.Entry<Object, Object>> map) {
        StringBuilder pretty = new StringBuilder();
        pretty.append("{").append(System.lineSeparator());
        map.forEach((key, value) -> {
            pretty.append("  ");
            switch (key.getValue()) {
                case ADDED -> {
                    pretty
                            .append("+ ")
                            .append(key.getKey())
                            .append(": ")
                            .append(printValue(value.getKey()))
                            .append(System.lineSeparator());
                }
                case CHANGED -> {
                    pretty
                            .append("- ")
                            .append(key.getKey())
                            .append(": ")
                            .append(printValue(value.getKey()))
                            .append(System.lineSeparator())
                            .append("  ")
                            .append("+ ")
                            .append(key.getKey())
                            .append(": ")
                            .append(printValue(value.getValue()))
                            .append(System.lineSeparator());
                }
                case REMOVED -> {
                    pretty.append("- ")
                            .append(key.getKey())
                            .append(": ")
                            .append(printValue(value.getKey()))
                            .append(System.lineSeparator());
                }
                default -> {
                    pretty.append("  ")
                            .append(key.getKey())
                            .append(": ")
                            .append(printValue(value.getKey()))
                            .append(System.lineSeparator());
                }
            }
        });
        pretty.append("}");
        return pretty.toString();
    }

    public static String printValue(Object value) {
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
