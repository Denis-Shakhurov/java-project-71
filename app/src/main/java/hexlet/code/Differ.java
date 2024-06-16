package hexlet.code;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(Path pathFile1, Path pathFile2, String nameFormat) {
        Map<String, Object> parseFile1 = Parser.parse(pathFile1);
        Map<String, Object> parseFile2 = Parser.parse(pathFile2);

        List<Node> nodes = Data.getData(parseFile1, parseFile2);

        String differ = Formatter.getFormat(nodes, nameFormat);
        return differ;
    }
}
