package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;


public class Differ {
    public static String generate(Path pathFile1, Path pathFile2, String option) {
        Map<String, Object> parseFile1;
        Map<String, Object> parseFile2;

        try {
            parseFile1 = Parser.parse(pathFile1);
            parseFile2 = Parser.parse(pathFile2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Node> diff = getMapDiffer(parseFile1, parseFile2);
        String differ = "";
        switch (option) {
            case "stylish" : differ = Formatter.stylish(diff);
            default :
                System.out.println("Unknown format option");
        }

        return differ;
    }
    public static List<Node> getMapDiffer(Map<String, Object> mapParseFile1, Map<String, Object> mapParseFile2) {
        Set<String> keys = new TreeSet<>(mapParseFile1.keySet());
        keys.addAll(mapParseFile2.keySet());

        List<Node> diff = new LinkedList<>();
        for (String key : keys) {
            Object value1;
            Object value2;
            if (!mapParseFile2.containsKey(key)) {
                value1 = mapParseFile1.get(key);
                diff.add(new Node(key, value1, "deleted"));
            } else if (mapParseFile2.containsKey(key) && mapParseFile1.containsKey(key)) {
                value1 = mapParseFile1.get(key);
                value2 = mapParseFile2.get(key);
                if (String.valueOf(value1).equals(String.valueOf(value2))) {
                    diff.add(new Node(key, value1, "unchanged"));
                } else {
                    diff.add(new Node(key, value1, "deleted"));
                    diff.add(new Node(key, value2, "added"));
                }
            } else if (!mapParseFile1.containsKey(key)) {
                value2 = String.valueOf(mapParseFile2.get(key));
                diff.add(new Node(key, value2, "added"));
            }
        }
        return diff;
    }
}
