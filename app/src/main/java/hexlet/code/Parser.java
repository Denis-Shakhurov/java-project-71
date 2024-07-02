package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String data, String type) throws Exception {

        Map<String, Object> mapParse = new LinkedHashMap<>();

        if (type.equals("json")) {
            mapParse = deserializeJsonToMap(data);
        } else if (type.equals("yml") || type.equals("yaml")) {
            mapParse = deserializeYamlToMap(data);
        } else {
            throw new Exception("Unknown format file");
        }
        return mapParse;
    }

    private static Map<String, Object> deserializeJsonToMap(String json) {
        Map<String, Object> map = new LinkedHashMap<>();
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            map = jsonMapper.readValue(json, new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private static Map<String, Object> deserializeYamlToMap(String yaml) {
        Map<String, Object> map = new LinkedHashMap<>();
        ObjectMapper ymlMapper = new YAMLMapper();
        try {
            map = ymlMapper.readValue(yaml, new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
