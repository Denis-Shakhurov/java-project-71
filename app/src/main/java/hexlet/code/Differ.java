package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


public class Differ {
    public static String generate(Path pathFile1, Path pathFile2) throws IOException {
        Map<String, Object> parseFile1 = getData(pathFile1);
        Map<String, Object> parseFile2 = getData(pathFile2);

        Map<String, Object> diff = new LinkedHashMap<>();

        for (Map.Entry<String, Object> entry : parseFile1.entrySet()) {
            String key = entry.getKey();
            Object value1 = parseFile1.get(key);
            Object value2 = parseFile2.get(key);
            if (!parseFile2.containsKey(key)) {
                diff.put("- " + key, value1);
            } else if (parseFile2.containsKey(key) && value1.equals(value2)) {
                diff.put("  " + key, value1);
            } else if (parseFile2.containsKey(key) && !value1.equals(value2)) {
                diff.put("- " + key, value1);
                diff.put("+ " + key, value2);
            }
        }
        for (String key : parseFile2.keySet()) {
            Object value = parseFile2.get(key);
            if (!parseFile1.containsKey(key)) {
                diff.put("+ " + key, value);
            }
        }
        diff.entrySet().stream().sorted(Map.Entry.<String, Object>comparingByKey());
        StringBuilder sb = new StringBuilder("{\n");
        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            Object value = diff.get(key);
            sb.append(key + ": " + value + "\n");
        }
        sb.append("}");
        return sb.toString();
    }
    public static Map<String, Object> getData(Path path) throws IOException {
        String absolutPath = String.valueOf(path.toAbsolutePath().normalize());

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(absolutPath), new TypeReference<TreeMap<String, Object>>() { });
    }
}
