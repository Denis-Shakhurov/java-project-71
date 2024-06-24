package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatToStylish(List<Map<String, Object>> differs) {
        return Stylish.convertToStylish(differs);
    }
    public static String formatToPlain(List<Map<String, Object>> differs) {
        return Plain.convertToPlain(differs);
    }
    public static String formatToJson(List<Map<String, Object>> differs) {
        return Json.createJson(differs);
    }


    public static String format(String nameFormat) {
        return nameFormat.toLowerCase().trim();
    }
}
