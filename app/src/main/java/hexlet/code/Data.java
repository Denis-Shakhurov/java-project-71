package hexlet.code;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;


public class Data {
    public static List<Map<String, Object>> getData(Map<String, Object> parseFile1, Map<String, Object> parseFile2) {
        Set<String> keys = new TreeSet<>(parseFile1.keySet());
        keys.addAll(parseFile2.keySet());

        List<Map<String, Object>> result = new LinkedList<>();
        for (String key : keys) {
            Object value1;
            Object value2;
            if (!parseFile2.containsKey(key)) {
                value1 = parseFile1.get(key);
                result.add(createMap("deleted", key, value1));
            } else if (parseFile2.containsKey(key) && parseFile1.containsKey(key)) {
                value1 = parseFile1.get(key);
                value2 = parseFile2.get(key);
                if (String.valueOf(value1).equals(String.valueOf(value2))) {
                    result.add(createMap("unchanged", key, value1));
                } else {
                    result.add(createMap("changed", key, value1));
                    result.add(createMap("update", key, value2));
                }
            } else if (!parseFile1.containsKey(key)) {
                value2 = parseFile2.get(key);
                result.add(createMap("added", key, value2));
            }
        }
        return result;
    }

    public static Map<String, Object> createMap(String status, String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(status + " " + key, value);
        return map;
    }
}
