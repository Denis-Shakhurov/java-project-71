package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static String convertJson(List<Map<String, Object>> differs) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(differs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json.trim();
    }
}
