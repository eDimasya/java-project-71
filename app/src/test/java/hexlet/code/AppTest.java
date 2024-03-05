package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class AppTest {
    @Test
    void mainTest() {
        String expected = """
                {
                \t- follow: false
                \t  host: hexlet.io
                \t- proxy: 123.234.53.22
                \t- timeout: 50
                \t+ timeout: 20
                \t+ verbose: true
                }
                """;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        App.main(new String[]{
            "src/test/resources/file1_flat.yml",
            "src/test/resources/file2_flat.yml",
            "-f " + Formatter.STYLISH});
        Assertions.assertEquals(expected, byteArrayOutputStream.toString());
    }
}
