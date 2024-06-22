package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, Object>> differs, String nameFormat) {
        String diff = "";
        switch (nameFormat) {
            case "stylish" : diff = Stylish.stylish(differs); break;
            case "plain" : diff = Plain.plain(differs); break;
            case "json" : diff = Json.createJson(differs); break;
            default : throw new RuntimeException("Unknown format");
        }
        return diff;
    }
}
