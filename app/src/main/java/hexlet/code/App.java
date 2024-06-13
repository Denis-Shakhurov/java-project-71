package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "Help demo v1.2.3",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {
    @Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    boolean usageHelpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    boolean versionHelpRequested;

    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]",
            defaultValue = "stylish")
    String option;

    @Parameters(paramLabel = "filePath1", description = "path to first file")
    Path filePath1;
    @Parameters(paramLabel = "filePath2", description = "path to second file")
    Path filePath2;

    @Override
    public String call() {
        System.out.println(Differ.generate(filePath1, filePath2, option));
        return "";
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
