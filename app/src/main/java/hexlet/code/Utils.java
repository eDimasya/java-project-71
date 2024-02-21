package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;

public class Utils {
    public static LinkedHashMap<String, String> stringToJson(String filepath) throws IOException {
        Path path = Path.of(filepath);
        String content = Files.readString(path);
        return new ObjectMapper().readValue(content, new TypeReference<>(){});
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
