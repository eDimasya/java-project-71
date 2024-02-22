package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class UtilsTest {

    @Test
    void testStringToJson() {
        try {
            System.out.println(Utils.fileContentToMap("src/test/resources/file1.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
