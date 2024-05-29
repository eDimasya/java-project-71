package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

class DifferTest {

    @Test
    void generate() throws IOException {
        String pathToTestResources = "src/test/resources/";
        String file1nestedJsonPath = pathToTestResources + "file1_nested.json";
        String file2nestedJsonPath = pathToTestResources + "file2_nested.json";
        String file1nestedYmlPath = pathToTestResources + "file1_nested.yml";
        String file2nestedYmlPath = pathToTestResources + "file2_nested.yml";

        String expectedNestedStylish =
                Files.readString(Utils.getPath(pathToTestResources + "expected/" + "expectedNestedStylish"));
        Assertions.assertEquals(expectedNestedStylish,
                Differ.generate(
                        file1nestedYmlPath,
                        file2nestedYmlPath,
                        Formatter.STYLISH));
        Assertions.assertEquals(expectedNestedStylish,
                Differ.generate(
                        file1nestedJsonPath,
                        file2nestedJsonPath,
                        Formatter.STYLISH));

        String expectedNestedPlain =
                Files.readString(Utils.getPath(pathToTestResources + "expected/" + "expectedNestedPlain"));
        Assertions.assertEquals(
                expectedNestedPlain,
                Differ.generate(
                        file1nestedYmlPath,
                        file2nestedYmlPath,
                        Formatter.PLAIN));

        String expectedNestedJson =
                Files.readString(Utils.getPath(pathToTestResources + "expected/" + "expectedNestedJson"));
        Assertions.assertEquals(
                expectedNestedJson,
                Differ.generate(
                        file1nestedYmlPath,
                        file2nestedYmlPath,
                        Formatter.JSON));

    }
}
