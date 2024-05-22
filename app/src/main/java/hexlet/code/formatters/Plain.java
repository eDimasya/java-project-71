package hexlet.code.formatters;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static hexlet.code.Differ.KeyAttribute;

public class Plain {
    public static String prettyPrint(
            LinkedHashMap<Map.Entry<String, KeyAttribute>, Object[]> map) {
        StringBuilder pretty = new StringBuilder();
        map.forEach((key, value) -> {
            switch (key.getValue()) {
                case ADDED -> {
                    pretty.append("Property '")
                            .append(key.getKey())
                            .append("' ")
                            .append("was added with value: ")
                            .append(printValue(value[0]));
                }
                case REMOVED -> {
                    pretty.append("Property '")
                            .append(key.getKey())
                            .append("' ")
                            .append("was removed");
                }
                case CHANGED -> {
                    pretty.append("Property '")
                            .append(key.getKey())
                            .append("' ")
                            .append("was updated. From ")
                            .append(printValue(value[0]))
                            .append(" to ")
                            .append(printValue(value[1]));
                }
                default -> {
                }
            }
            if (key.getValue() != KeyAttribute.NOT_CHANGED) {
                pretty.append(System.lineSeparator());
            }
        });
        pretty.deleteCharAt(pretty.lastIndexOf(System.lineSeparator()));
        return pretty.toString();
    }

    private static String printValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof ArrayNode || value instanceof ObjectNode || value instanceof Object[]) {
            return "[complex value]";
        }
        return String.valueOf(value);
    }
}
