package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {

    public static String getFormat(List<Node> nodes, String nameFormat) {
        String diff = "";
        switch (nameFormat) {
            case "stylish" : diff = Stylish.stylish(nodes); break;
            case "plain" : diff = Plain.plain(nodes); break;
            case "json" : diff = Json.createJson(nodes); break;
            default :
                System.out.println("Unknown format");
        }
        return diff;
    }
}
