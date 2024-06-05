package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import static hexlet.code.Formatter.STYLISH;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        return Formatter.prettyPrint(
                Diff.compare(
                        Objects.requireNonNull(Parser.parseContent(
                                Files.readString(Utils.getPath(filepath1)),
                                Utils.getFileExtension(filepath1))),
                        Objects.requireNonNull(Parser.parseContent(
                                Files.readString(Utils.getPath(filepath2)),
                                Utils.getFileExtension(filepath2)))),
                format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, STYLISH);
    }
}
