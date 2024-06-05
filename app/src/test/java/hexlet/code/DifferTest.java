package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import static hexlet.code.Formatter.JSON;
import static hexlet.code.Formatter.PLAIN;
import static hexlet.code.Formatter.STYLISH;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

class DifferTest {

    @Test
    void generate() throws IOException {
        String testResPath = "src/test/resources/";
        String file1JsonPath = testResPath + "file1.json";
        String file2JsonPath = testResPath + "file2.json";
        String file1YmlPath = testResPath + "file1.yml";
        String file2YmlPath = testResPath + "file2.yml";
        //Stylish
        String expectedStylish = Files.readString(Utils.getPath(testResPath + "expected/stylish"));
        assertEquals(expectedStylish, Differ.generate(file1YmlPath, file2YmlPath, STYLISH));
        assertEquals(expectedStylish, Differ.generate(file1JsonPath, file2JsonPath, STYLISH));
        assertEquals(expectedStylish, Differ.generate(file1YmlPath, file2YmlPath));
        assertEquals(expectedStylish, Differ.generate(file1JsonPath, file2JsonPath));
        //Plain
        String expectedPlain = Files.readString(Utils.getPath(testResPath + "expected/plain"));
        assertEquals(expectedPlain, Differ.generate(file1YmlPath, file2YmlPath, PLAIN));
        //Json
        ObjectMapper mapper = new ObjectMapper();
        String expectedJson = Files.readString(Utils.getPath(testResPath + "expected/json"));
        assertEquals(mapper.readTree(expectedJson),
                mapper.readTree(Differ.generate(file1YmlPath, file2YmlPath, JSON)));

    }
}
