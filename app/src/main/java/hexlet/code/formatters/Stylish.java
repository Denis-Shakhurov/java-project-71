package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.List;

public class Stylish {
    public static String stylish(List<Node> nodes) {
        StringBuilder sb = new StringBuilder("{\n");

        for (Node node : nodes) {
            String status = node.getStatus();
            String name = node.getName();
            String value = String.valueOf(node.getValue());
            switch (status) {
                case "unchanged" : sb.append("    " + name + ": " + value + "\n"); break;
                case "added" : sb.append("  " + "+ " + name + ": " + value + "\n"); break;
                case "update" : sb.append("  " + "+ " + name + ": " + value + "\n"); break;
                case "deleted" : sb.append("  " + "- " + name + ": " + value + "\n"); break;
                case "remove" : sb.append("  " + "- " + name + ": " + value + "\n"); break;
                default :
                    System.out.println("Unknown status");
            }
        }
        return sb.append("}").toString();
    }
}
