package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;

public class Parser {
    public static final String JSON_EXTENSION = "json";
    public static final String YAML_EXTENSION = "yml";

    public static LinkedHashMap<String, String> fileContentToMap(String filepath) throws IOException {
        Path path = Path.of(filepath);
        return switch (Utils.getFileExtension(filepath)) {
            case YAML_EXTENSION -> new YAMLMapper().readValue(Files.readString(path), new TypeReference<>() {
            });
            case JSON_EXTENSION -> new JsonMapper().readValue(Files.readString(path), new TypeReference<>() {
            });
            default -> null;
        };
    }
}
