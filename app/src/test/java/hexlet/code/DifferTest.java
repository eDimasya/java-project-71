package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DifferTest {

    @Test
    void testGenerate() throws IOException {
        System.out.println(Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
    }

}