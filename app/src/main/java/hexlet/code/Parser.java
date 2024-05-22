package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;

public class Parser {

    public static HashMap<String, Object> parseContent(String content, String format) {
        ObjectMapper mapper;
        switch (format) {
            case "yml" -> {
                mapper = new YAMLMapper();
                try {
                    return mapper.readValue(content, new TypeReference<>() {
                    });
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            case "json" -> {
                mapper = new JsonMapper();
                try {
                    return mapper.readValue(content, new TypeReference<>() {
                    });
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> {
                return null;
            }
        }
    }
}
