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
    public static Map<String, Object> parse(Path path) throws IOException {
        String absolutPath = String.valueOf(path.toAbsolutePath().normalize());
        String format = getFormat(absolutPath);

        Map<String, Object> mapParse = new LinkedHashMap<>();
        switch (format) {
            case ".json" :
                ObjectMapper jsonMapper = new ObjectMapper();
                mapParse = jsonMapper.readValue(new File(absolutPath), new TypeReference<>() { }); break;
            case ".yml", ".yaml" :
                ObjectMapper ymlMapper = new YAMLMapper();
                mapParse = ymlMapper.readValue(new File(absolutPath), new TypeReference<>() { }); break;
            default :
                System.out.println("Unknown format file");
        }
        return mapParse;
    }
    public static String getFormat(String path) {
        return path.substring(path.lastIndexOf("."), path.length());
    }
}
