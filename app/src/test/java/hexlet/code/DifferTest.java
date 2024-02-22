package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DifferTest {

    @Test
    void testGenerate() throws IOException {
        String expected = """
                {
                \t- follow: false
                \t  host: hexlet.io
                \t- proxy: 123.234.53.22
                \t- timeout: 50
                \t+ timeout: 20
                \t+ verbose: true
                }""";
        Assertions.assertEquals(expected,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
    }
}
