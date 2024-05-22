package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DifferTest {

    @Test
    void generate() throws IOException {
        String file1flatJsonPath = "src/test/resources/file1_flat.json";
        String file2flatJsonPath = "src/test/resources/file2_flat.json";
        String file1flatYmlPath = "src/test/resources/file1_flat.yml";
        String file2flatYmlPath = "src/test/resources/file2_flat.yml";
        String file1nestedJsonPath = "src/test/resources/file1_nested.json";
        String file2nestedJsonPath = "src/test/resources/file2_nested.json";
        String file1nestedYmlPath = "src/test/resources/file1_nested.yml";
        String file2nestedYmlPath = "src/test/resources/file2_nested.yml";
        //Flat
        String expectedFlat = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 1
                  + timeout: 2
                  + verbose: true
                }""";
        Assertions.assertEquals(expectedFlat,
                Differ.generate(file1flatJsonPath,
                        file2flatJsonPath,
                        Formatter.STYLISH));
        Assertions.assertEquals(expectedFlat,
                Differ.generate(file1flatYmlPath,
                        file2flatYmlPath,
                        Formatter.STYLISH));
        //Nested
        String expectedNested = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 0]
                  - numbers2: [2, 1, 0]
                  + numbers2: [2, 1, 1]
                  - numbers3: [0, 1, 2]
                  + numbers4: [0, 1, 2]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 2
                  + setting2: 1
                  - setting3: true
                  + setting3: none
                }""";
        Assertions.assertEquals(expectedNested,
                Differ.generate(file1nestedYmlPath,
                        file2nestedYmlPath,
                        Formatter.STYLISH));
        Assertions.assertEquals(expectedNested,
                Differ.generate(file1nestedJsonPath,
                        file2nestedJsonPath,
                        Formatter.STYLISH));
    }
}
