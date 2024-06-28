package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String path1, String path2, String nameFormat) {
        String data1 = fileToString(path1);
        String data2 = fileToString(path2);

        String fileType1 = getTypeFile(path1);
        String fileType2 = getTypeFile(path2);

        Map<String, Object> parseFile1 = Parser.parse(data1, fileType1);
        Map<String, Object> parseFile2 = Parser.parse(data2, fileType2);

        List<Map<String, Object>> differs = Data.getData(parseFile1, parseFile2);

        return Formatter.format(differs, nameFormat);
    }

    public static String generate(String path1, String path2) {
        return generate(path1, path2, "stylish");
    }

    private static Path fileToPath(String fileName) throws Exception {
        Path path = Paths.get(fileName).toAbsolutePath().normalize();

        if (Files.notExists(path)) {
            throw new Exception("File not exists");
        }
        return path;
    }

    public static String fileToString(String fileName) {
        Path path;
        String result;
        try {
            path = fileToPath(fileName);
            result = Files.readString(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getTypeFile(String path) {
        String type = "";
        if (path != null || path != "") {
            type = path.substring(path.lastIndexOf(".") + 1, path.length());
        } else {
            System.out.println("Path is not correct");
        }
        return type;
    }
}
