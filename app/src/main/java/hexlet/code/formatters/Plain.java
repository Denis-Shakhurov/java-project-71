package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String plain(List<Map<String, Object>> differs) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < differs.size(); i++) {
            var keys = differs.get(i).keySet();
            String[] keyAndStatus;
            String status = "";
            String keyPrint = "";
            for (var key : keys) {
                keyAndStatus = key.split(" ");
                status = keyAndStatus[0];
                keyPrint = keyAndStatus[1];
            }

            if (status.equals("update")) {
                sb.append("Property '" + keyPrint + "' was updated. From " + printValue(differs.get(i - 1))
                        + " to " + printValue(differs.get(i)) + "\n");
            } else if (status.equals("added")) {
                sb.append("Property '" + keyPrint + "' was added with value: "
                        + printValue(differs.get(i)) +  "\n");
            } else if (status.equals("remove")) {
                sb.append("Property '" + keyPrint + "' was removed\n");
            }
        }
        return sb.toString().trim();
    }
    public static String printValue(Map<String, Object> map) {
        var keys = map.keySet();
        Object value = null;
        for (var key : keys) {
            value = map.get(key);
        }
        String result = String.valueOf(value);
        if (value == null) {
            result = "null";
        } else if (value instanceof String) {
            result = "'" + result + "'";
        } else if (value instanceof Map || value instanceof List) {
            result = "[complex value]";
        }
        return result;
    }
}
