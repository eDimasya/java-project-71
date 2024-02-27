package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.LinkedHashMap;

class ParserTest {
    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/file1.json", "src/test/resources/file3.yml"})
    void fileContentToMap(String filepath) {
        LinkedHashMap<String, String> expected = new LinkedHashMap<>();
        expected.put("host", "hexlet.io");
        expected.put("timeout", "50");
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", "false");
        try {
            LinkedHashMap<String, String> actual = Parser.fileContentToMap(filepath);
            Assertions.assertEquals(expected, actual);
            Assertions.assertThrows(IOException.class,
                    () ->
                            Parser.fileContentToMap("src/test/resources/fileAbsent.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
