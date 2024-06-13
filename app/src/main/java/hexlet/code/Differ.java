package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Differ {
    public static String generate(Path pathFile1, Path pathFile2) {
        Map<String, Object> parseFile1;
        Map<String, Object> parseFile2;

        try {
            parseFile1 = Parser.parse(pathFile1);
            parseFile2 = Parser.parse(pathFile2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Set<String> keys = new TreeSet<>(parseFile1.keySet());
        keys.addAll(parseFile2.keySet());

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
        for (String key : diff.keySet()) {
            Object value = diff.get(key);
            sb.append(key + ": " + value + "\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
