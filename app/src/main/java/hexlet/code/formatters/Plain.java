package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String plain(List<Node> nodes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < nodes.size(); i++) {

            String name = nodes.get(i).getName();
            String status = nodes.get(i).getStatus();

            if (status.equals("update")) {
                sb.append("Property '" + name + "' was update. From " + printValue(nodes.get(i - 1))
                        + " to " + printValue(nodes.get(i)) + "\n");
            } else if (status.equals("added")) {
                sb.append("Property '" + name + "' was added with value: "
                        + printValue(nodes.get(i)) +  "\n");
            } else if (status.equals("remove")) {
                sb.append("Property '" + name + "' was removed\n");
            }
        }
        return sb.toString();
    }
    public static String printValue(Node node) {
        Object value = node.getValue();
        String result = String.valueOf(value);
        if (value == null) {
            result = "null";
        } else if (value instanceof String) {
            result = "'" + result + "'";
        } else if (value instanceof Map
        || value instanceof List) {
            result = "[complex value]";
        }
        return result;
    }
}
