package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import hexlet.code.KeyAttribute;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Stylish {
    public static String prettyPrint(
            List<Map<String, Object>> list) {
        StringBuilder pretty = new StringBuilder();
        pretty.append("{").append(System.lineSeparator());
        list.forEach(element -> {
            pretty.append("  ");
            switch ((KeyAttribute) element.get("type")) {
                case ADDED -> {
                    pretty
                            .append("+ ")
                            .append(element.get("key"))
                            .append(": ")
                            .append(printValue(element.get("value")))
                            .append(System.lineSeparator());
                }
                case CHANGED -> {
                    pretty
                            .append("- ")
                            .append(element.get("key"))
                            .append(": ")
                            .append(printValue(element.get("value1")))
                            .append(System.lineSeparator())
                            .append("  ")
                            .append("+ ")
                            .append(element.get("key"))
                            .append(": ")
                            .append(printValue(element.get("value2")))
                            .append(System.lineSeparator());
                }
                case REMOVED -> {
                    pretty.append("- ")
                            .append(element.get("key"))
                            .append(": ")
                            .append(printValue(element.get("value")))
                            .append(System.lineSeparator());
                }
                default -> {
                    pretty.append("  ")
                            .append(element.get("key"))
                            .append(": ")
                            .append(printValue(element.get("value")))
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
        return String.valueOf(value);
    }
}
