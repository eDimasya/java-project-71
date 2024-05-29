package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

    public static Path getPath(String filepath) {
        return Paths.get(filepath).toAbsolutePath().normalize();
    }

    public static String getFileExtension(String filepath) throws IOException {
        if (getPath(filepath).getFileName().toString().contains(".")) {
            return filepath.substring(filepath.lastIndexOf(".") + 1);
        } else {
            throw new IOException();
        }
    }
}
