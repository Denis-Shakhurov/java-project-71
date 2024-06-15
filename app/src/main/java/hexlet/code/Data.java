package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;


public class Data {
    public static List<Node> getData(Map<String, Object> mapParseFile1, Map<String, Object> mapParseFile2) {
        Set<String> keys = new TreeSet<>(mapParseFile1.keySet());
        keys.addAll(mapParseFile2.keySet());

        List<Node> diff = new LinkedList<>();
        for (String key : keys) {
            Object value1;
            Object value2;
            if (!mapParseFile2.containsKey(key)) {
                value1 = mapParseFile1.get(key);
                diff.add(new Node(key, value1, "remove"));
            } else if (mapParseFile2.containsKey(key) && mapParseFile1.containsKey(key)) {
                value1 = mapParseFile1.get(key);
                value2 = mapParseFile2.get(key);
                if (String.valueOf(value1).equals(String.valueOf(value2))) {
                    diff.add(new Node(key, value1, "unchanged"));
                } else {
                    diff.add(new Node(key, value1, "deleted"));
                    diff.add(new Node(key, value2, "update"));
                }
            } else if (!mapParseFile1.containsKey(key)) {
                value2 = mapParseFile2.get(key);
                diff.add(new Node(key, value2, "added"));
            }
        }
        return diff;
    }
}
