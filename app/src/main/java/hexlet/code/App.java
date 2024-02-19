package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;


@Command(mixinStandardHelpOptions = true,
        name = "genDiff",
        version = "1.0",
        description = """
                Compares two configuration files and shows a difference.
                    filepath1         path to first file
                    filepath2         path to second file""")
public class App {

    @Option(names = {"-f", "--format"},
            help = true,
            defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}