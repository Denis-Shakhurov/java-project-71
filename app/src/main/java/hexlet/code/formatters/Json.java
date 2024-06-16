package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Node;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Json {
    public static String createJson(List<Node> nodes) {
        Map<String, Map<String, Object>> mapJson = new TreeMap<>();

        mapJson.put("changed", new TreeMap<>());
        mapJson.put("deleted", new TreeMap<>());
        mapJson.put("added", new TreeMap<>());

        ObjectMapper mapperJson = new ObjectMapper();

        for (Node node : nodes) {
            String key = node.getName();
            Object value = node.getValue();
            String status = node.getStatus();
            if (!status.equals("unchanged")) {
                switch (status) {
                    case "update": mapJson.get("changed").put(key, value); break;
                    case "deleted", "remove": mapJson.get("deleted").put(key, value); break;
                    case "added": mapJson.get("added").put(key, value); break;
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
