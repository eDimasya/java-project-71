package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DifferTest {

    @Test
    void generate() throws IOException {
        //Flat
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 1
                  + timeout: 2
                  + verbose: true
                }""";
        Assertions.assertEquals(expected,
                Differ.generate("src/test/resources/file1_flat.json",
                        "src/test/resources/file2_flat.json",
                        Formatter.STYLISH));
        Assertions.assertEquals(expected,
                Differ.generate("src/test/resources/file1_flat.yml",
                        "src/test/resources/file2_flat.yml",
                        Formatter.STYLISH));
        //Nested
        expected = """
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
        Assertions.assertEquals(expected,
                Differ.generate("src/test/resources/file1_nested.yml",
                        "src/test/resources/file2_nested.yml",
                        Formatter.STYLISH));
        Assertions.assertEquals(expected,
                Differ.generate("src/test/resources/file1_nested.json",
                        "src/test/resources/file2_nested.json",
                        Formatter.STYLISH));
    }
}
