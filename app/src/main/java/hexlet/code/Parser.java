package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Parser {
    public static JsonNode getContent(String filepath) throws IOException {
        Path path = Path.of(filepath);
        String content = Files.readString(path);
        ObjectMapper mapper;
        switch (filepath.substring(filepath.lastIndexOf(".") + 1)) {
            case "yml" -> {
                mapper = new YAMLMapper();
                return mapper.readTree(content);
            }
            case "json" -> {
                mapper = new JsonMapper();
                return mapper.readTree(content);
            }
            default -> {
                return null;
            }
        }
    }
}
