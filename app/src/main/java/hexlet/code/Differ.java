package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String path1, String path2, String nameFormat) throws Exception {
        Path pathFile1 = fileToPath(path1);
        Path pathFile2 = fileToPath(path2);

        String fileType1 = getTypeFile(path1);
        String fileType2 = getTypeFile(path2);

        Map<String, Object> parseFile1 = Parser.parse(pathFile1, fileType1);
        Map<String, Object> parseFile2 = Parser.parse(pathFile2, fileType2);

        List<Node> nodes = Data.getData(parseFile1, parseFile2);

        return Formatter.getFormat(nodes, nameFormat);
    }
    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }
    private static Path fileToPath(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (Files.notExists(path)) {
            throw new Exception("File not exists");
        }
        return path;
    }

    public static String getTypeFile(String path) {
        String type = "";
        if (path != null || path != "") {
            type = path.substring(path.lastIndexOf("."), path.length());
        } else {
            System.out.println("Path is not correct");
        }
        return type;
    }
}
