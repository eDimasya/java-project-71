package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Json {
    public static String prettyPrint(
            List<Map<String, Object>> list)
            throws IOException {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(list);
    }
}
