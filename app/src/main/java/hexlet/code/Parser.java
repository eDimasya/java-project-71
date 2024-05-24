package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;

public class Parser {

    public static HashMap<String, Object> parseContent(String content, String format) throws JsonProcessingException {
        ObjectMapper mapper;
        switch (format) {
            case "yml" -> {
                mapper = new YAMLMapper();
                return mapper.readValue(content, new TypeReference<>() {
                });
            }
            case "json" -> {
                mapper = new JsonMapper();
                return mapper.readValue(content, new TypeReference<>() {
                });
            }
            default -> {
                return null;
            }
        }
    }
}
