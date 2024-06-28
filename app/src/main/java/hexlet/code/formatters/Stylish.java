package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String convertToStylish(List<Map<String, Object>> differs) {
        StringBuilder sb = new StringBuilder("{\n");

        for (Map<String, Object> diff : differs) {
            var entries = diff.entrySet();
            String[] keyAndStatus;
            String status = "";
            String key = "";
            String value = "";
            for (var entry : entries) {
                keyAndStatus = entry.getKey().split(" ");
                status = keyAndStatus[0];
                key = keyAndStatus[1];
                value = String.valueOf(entry.getValue());
            }
            switch (status) {
                case "unchanged" : sb.append("    " + key + ": " + value + "\n"); break;
                case "added" : sb.append("  " + "+ " + key + ": " + value + "\n"); break;
                case "update" : sb.append("  " + "+ " + key + ": " + value + "\n"); break;
                case "changed" : sb.append("  " + "- " + key + ": " + value + "\n"); break;
                case "deleted" : sb.append("  " + "- " + key + ": " + value + "\n"); break;
                default : System.out.println("Unknown status");
            }
        }
        return sb.append("}").toString();
    }
}
