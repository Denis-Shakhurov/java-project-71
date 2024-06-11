package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;


public class Differ {
    public static String generate(Path pathFile1, Path pathFile2) throws IOException {
        Map<String, Object> parseFile1 = getData(pathFile1);
        Map<String, Object> parseFile2 = getData(pathFile2);

        List<String> keys = new ArrayList<>(parseFile1.keySet());
        keys.addAll(parseFile2.keySet());
        Collections.sort(keys);

        Map<String, Object> diff = new LinkedHashMap<>();
        for (String key : keys) {
            Object value1;
            Object value2;
            if (!parseFile2.containsKey(key)) {
                value1 = parseFile1.get(key);
                diff.put("- " + key, value1);
            } else if (parseFile2.containsKey(key) && parseFile1.containsKey(key)) {
                value1 = parseFile1.get(key);
                value2 = parseFile2.get(key);
                if (value1.equals(value2)) {
                    diff.put("  " + key, value1);
                } else {
                    diff.put("- " + key, value1);
                    diff.put("+ " + key, value2);
                }
            } else if (!parseFile1.containsKey(key)) {
                value2 = parseFile2.get(key);
                diff.put("+ " + key, value2);
            }
        }
        
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
