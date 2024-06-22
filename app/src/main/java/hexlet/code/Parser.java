package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String data, String type) {

        Map<String, Object> mapParse = new LinkedHashMap<>();
        switch (type) {
            case "json" : mapParse = deserializeJsonToMap(data); break;
            case "yml", "yaml" : mapParse = deserializeYamlToMap(data); break;
            default : System.out.println("Unknown format file");
        }
        return mapParse;
    }
    public static Map<String, Object> deserializeJsonToMap(String json) {
        Map<String, Object> map = new LinkedHashMap<>();
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            map = jsonMapper.readValue(json, new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
    public static Map<String, Object> deserializeYamlToMap(String yaml) {
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
