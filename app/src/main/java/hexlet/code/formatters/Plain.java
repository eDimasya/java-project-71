package hexlet.code.formatters;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.util.LinkedHashMap;
import java.util.Map;

import static hexlet.code.Differ.KeyAttribute;

public class Plain {
    public static String prettyPrint(
            LinkedHashMap<Map.Entry<String, KeyAttribute>, Map.Entry<Object, Object>> map) {
        StringBuilder pretty = new StringBuilder();
        map.forEach((key, value) -> {
            switch (key.getValue()) {
                case ADDED -> {
                    pretty.append("Property '")
                            .append(key.getKey())
                            .append("' ")
                            .append("was added with value: ")
                            .append(printValue(value.getKey()))
                            .append(System.lineSeparator());
                }
                case REMOVED -> {
                    pretty.append("Property '")
                            .append(key.getKey())
                            .append("' ")
                            .append("was removed")
                            .append(System.lineSeparator());
                }
                case CHANGED -> {
                    pretty.append("Property '")
                            .append(key.getKey())
                            .append("' ")
                            .append("was updated. From ")
                            .append(printValue(value.getKey()))
                            .append(" to ")
                            .append(printValue(value.getValue()))
                            .append(System.lineSeparator());
                }
                default -> {
                }
            }
        });
        return pretty.toString();
    }

    private static String printValue(Object value) {
        if (value instanceof TextNode) {
            return "'" + ((TextNode) value).asText() + "'";
        }
        if (value instanceof ArrayNode || value instanceof ObjectNode) {
            return "[complex value]";
        }
        return value.toString();
    }
}
