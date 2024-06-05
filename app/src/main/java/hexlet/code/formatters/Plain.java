package hexlet.code.formatters;

import hexlet.code.KeyAttribute;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static hexlet.code.KeyAttribute.NOT_CHANGED;

public class Plain {
    public static String prettyPrint(
            List<Map<String, Object>> list) {
        StringBuilder pretty = new StringBuilder();
        list.forEach(element -> {
            switch ((KeyAttribute) element.get("type")) {
                case ADDED -> {
                    pretty.append("Property '")
                        .append(element.get("key"))
                        .append("' ")
                        .append("was added with value: ")
                        .append(printValue(element.get("value")));
                }
                case REMOVED -> {
                    pretty.append("Property '")
                            .append(element.get("key"))
                            .append("' ")
                            .append("was removed");
                }
                case CHANGED -> {
                    pretty.append("Property '")
                            .append(element.get("key"))
                            .append("' ")
                            .append("was updated. From ")
                            .append(printValue(element.get("value1")))
                            .append(" to ")
                            .append(printValue(element.get("value2")));
                }
                default -> {
                }
            }
            if (!Objects.equals(element.get("type"), NOT_CHANGED)) {
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
        if (value instanceof Iterable<?> || value instanceof Map<?, ?> || value instanceof Object[]) {
            return "[complex value]";
        }
        return String.valueOf(value);
    }
}
