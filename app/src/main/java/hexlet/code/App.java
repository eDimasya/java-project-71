package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class App {
    @Option(names = {"-h", "--help"},
            usageHelp = true,
            description = """
                    Usage: genDiff [-hV]
                    Compares two configuration files and shows a difference.
                      -h, --help      Show this help message and exit.
                      -V, --version   Print version information and exit.""")
    private boolean helpRequested = false;
    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
        //System.out.println("Hello world!");
    }
}