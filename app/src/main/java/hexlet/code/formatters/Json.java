package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Json {
    public static String createJson(List<Map<String, Object>> differs) {
        Map<String, Map<String, Object>> mapJson = new TreeMap<>();

        mapJson.put("changed", new TreeMap<>());
        mapJson.put("deleted", new TreeMap<>());
        mapJson.put("added", new TreeMap<>());

        ObjectMapper mapperJson = new ObjectMapper();
        for (Map<String, Object> diff : differs) {
            var keys = diff.keySet();
            String[] keyAndStatus;
            String status = "";
            String keyPrint = "";
            Object value = null;
            for (var key : keys) {
                keyAndStatus = key.split(" ");
                status = keyAndStatus[0];
                keyPrint = keyAndStatus[1];
                value = diff.get(key);
            }
            if (!status.equals("unchanged")) {
                switch (status) {
                    case "update": mapJson.get("changed").put(keyPrint, value); break;
                    case "deleted", "remove": mapJson.get("deleted").put(keyPrint, value); break;
                    case "added": mapJson.get("added").put(keyPrint, value); break;
                    default:
                        System.out.println("Unknown status"); break;
                }
            }
        }
        String fileJson = "";
        try {
            fileJson = mapperJson.writerWithDefaultPrettyPrinter().writeValueAsString(mapJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return fileJson;
    }
}
