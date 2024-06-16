package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(Path path, String type) {
        String absolutPath = "";
        if (path != null) {
            absolutPath = String.valueOf(path.toAbsolutePath().normalize());
        } else {
            System.out.println("Path is not correct");
        }

        Map<String, Object> mapParse = new LinkedHashMap<>();
        switch (type) {
            case "json" :
                ObjectMapper jsonMapper = new ObjectMapper();
                try {
                    mapParse = jsonMapper.readValue(new File(absolutPath), new TypeReference<>() { });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "yml", "yaml" :
                ObjectMapper ymlMapper = new YAMLMapper();
                try {
                    mapParse = ymlMapper.readValue(new File(absolutPath), new TypeReference<>() { });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            default :
                System.out.println("Unknown format file");
        }
        return mapParse;
    }
}
