package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(Path pathFile1, Path pathFile2, String nameFormat) {
        Map<String, Object> parseFile1;
        Map<String, Object> parseFile2;

        try {
            parseFile1 = Parser.parse(pathFile1);
            parseFile2 = Parser.parse(pathFile2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Node> nodes = Data.getData(parseFile1, parseFile2);

        String differ = Formatter.getFormat(nodes, nameFormat);
        return differ;
    }
}
