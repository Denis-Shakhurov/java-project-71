package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String convertToPlain(List<Map<String, Object>> differs) throws Exception {
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
            if (!status.equals("unchanged") && !status.equals("changed")) {
                switch (status) {
                    case "update" -> sb.append("Property '" + keyPrint
                            + "' was updated. From " + formValue(differs.get(i - 1))
                            + " to " + formValue(differs.get(i)) + "\n");
                    case "added" -> sb.append("Property '" + keyPrint + "' was added with value: "
                            + formValue(differs.get(i)) + "\n");
                    case "deleted" -> sb.append("Property '" + keyPrint + "' was removed\n");
                    default -> throw new Exception("Unknown status");
                }
            }
        }
        return sb.toString().trim();
    }
    public static String formValue(Map<String, Object> map) {
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
