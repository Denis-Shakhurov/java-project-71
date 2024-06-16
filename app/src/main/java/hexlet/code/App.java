package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "Differ v 1.0",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<String> {
    @Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    private boolean usageHelpRequested;

    @Option(names = {"-v", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    private boolean versionHelpRequested;

    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]",
            defaultValue = "stylish")
    private String option;

    @Parameters(paramLabel = "filePath1", description = "path to first file")
    private String filePath1;
    @Parameters(paramLabel = "filePath2", description = "path to second file")
    private String filePath2;

    @Override
    public String call() throws Exception {
        System.out.println(Differ.generate(filePath1, filePath2, option));
        return null;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
