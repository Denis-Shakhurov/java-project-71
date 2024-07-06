package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    private static String formatToStylish(List<Map<String, Object>> differs) throws Exception {
        return Stylish.convertToStylish(differs);
    }

    private static String formatToPlain(List<Map<String, Object>> differs) throws Exception {
        return Plain.convertToPlain(differs);
    }

    private static String formatToJson(List<Map<String, Object>> differs) {
        return Json.convertJson(differs);
    }

    public static String format(List<Map<String, Object>> differs, String nameFormat) throws Exception {
        nameFormat.toLowerCase().trim();
        String differ = "";
        if (nameFormat.equals("stylish")) {
            differ = formatToStylish(differs);
        } else if (nameFormat.equals("plain")) {
            differ = formatToPlain(differs);
        } else if (nameFormat.equals("json")) {
            differ = formatToJson(differs);
        } else {
            throw new Exception("Unknown format");
        }
        return differ;
    }
}
