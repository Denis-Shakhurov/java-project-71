package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;


public class Differ {
    public static String generate(Path pathFile1, Path pathFile2) throws IOException {
        Map<String, String> parseFile1 = getData(pathFile1);
        Map<String, String> parseFile2 = getData(pathFile2);

        StringBuilder diff = new StringBuilder();
        diff.append("{\n");

        for (Map.Entry<String, String> entry : parseFile1.entrySet()) {
            String key = entry.getKey();
            String value1 = entry.getValue();
            String value2 = parseFile2.get(key);

            if (value2 == null) {
                diff.append(" - " + key + ": " + value1);
                diff.append("\n");
            } else if (!value1.equals(value2)) {
                diff.append(" - " + key + ": " + value1);
                diff.append("\n");
                diff.append(" + " + key + ": " + value2);
                diff.append("\n");
            } else if (parseFile2.containsKey(key) && value1.equals(value2)) {
                diff.append("   " + key + ": " + value1);
                diff.append("\n");
            }
        }
        for (String key : parseFile2.keySet()) {
            if (!parseFile1.containsKey(key)) {
                diff.append(" + " + key + ": " + parseFile2.get(key));
                diff.append("\n");
            }
        }
        diff.append("}");
        return diff.toString();
    }
    public static Map<String, String> getData(Path path) throws IOException {
        String absolutPath = String.valueOf(path);
        if (!path.isAbsolute()) {
            absolutPath = String.valueOf(path.toAbsolutePath());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(new File(absolutPath),
                new TypeReference<TreeMap<String, String>>() { });

        return map;
    }
}
