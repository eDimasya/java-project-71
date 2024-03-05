package hexlet.code;

import lombok.Getter;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Getter
@Command(mixinStandardHelpOptions = true,
        name = "genDiff",
        version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Parameters(index = "0",
            descriptionKey = "filepath1",
            description = "path to first file")
    private String filepath1;

    @Parameters(index = "1",
            descriptionKey = "filepath2",
            description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"},
            help = true,
            defaultValue = Formatter.STYLISH,
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public Integer call() throws IOException {
        System.out.println(Differ.generate(this.filepath1, this.filepath2, format));
        return 0;
    }
}
