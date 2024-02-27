package hexlet.code;

import java.util.LinkedHashMap;

public class Utils {

    public static String getFileExtension(String filepath) {
        return filepath.substring(filepath.lastIndexOf(".") + 1);
    }

    public static String prettyPrintMap(LinkedHashMap<String, String> map) {
        StringBuilder pretty = new StringBuilder();
        pretty.append("{").append(System.lineSeparator());
        map.forEach((key, value) ->
                pretty.append("\t").append(key).append(": ").append(value).append(System.lineSeparator())
        );
        pretty.append("}");
        return pretty.toString();
    }
}
