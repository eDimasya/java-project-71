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
                \t- follow: false
                \t  host: hexlet.io
                \t- proxy: 123.234.53.22
                \t- timeout: 50
                \t+ timeout: 20
                \t+ verbose: true
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
                \t  chars1: [a, b, c]
                \t- chars2: [d, e, f]
                \t+ chars2: false
                \t- checked: false
                \t+ checked: true
                \t- default: null
                \t+ default: [value1, value2]
                \t- id: 45
                \t+ id: null
                \t- key1: value1
                \t+ key2: value2
                \t  numbers1: [1, 2, 3, 4]
                \t- numbers2: [2, 3, 4, 5]
                \t+ numbers2: [22, 33, 44, 55]
                \t- numbers3: [3, 4, 5]
                \t+ numbers4: [4, 5, 6]
                \t+ obj1: {nestedKey=value, isNested=true}
                \t- setting1: Some value
                \t+ setting1: Another value
                \t- setting2: 200
                \t+ setting2: 300
                \t- setting3: true
                \t+ setting3: none
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
