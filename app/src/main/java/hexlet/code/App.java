package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "Help demo v1.2.3", header = "%nAutomatic Help Demo%n",
        description = "Compares two configuration files and shows a difference.")

public class App implements Runnable {
    @Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    boolean usageHelpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    boolean versionHelpRequested;

    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    File archive;

    @Parameters(paramLabel = "filePath1", description = "path to first file")
    File filePath1;
    @Parameters(paramLabel = "filePath2", description = "path to second file")
    File filePath2;

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        CommandLine.run(new App(), System.err, args);
    }
}
