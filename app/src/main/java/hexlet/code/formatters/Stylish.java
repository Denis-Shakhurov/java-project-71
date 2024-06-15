package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.List;

public class Stylish {
    public static String stylish(List<Node> nodes) {
        StringBuilder sb = new StringBuilder("{\n");
        String space = " ";
        for (Node node : nodes) {
            String status = node.getStatus();
            String name = node.getName();
            String value = String.valueOf(node.getValue());
            switch (status) {
                case "unchanged" : sb.append(space.repeat(4) + name + ": " + value + "\n"); break;
                case "added" : sb.append(space.repeat(2) + "+ " + name + ": " + value + "\n"); break;
                case "update" : sb.append(space.repeat(2) + "+ " + name + ": " + value + "\n"); break;
                case "deleted" : sb.append(space.repeat(2) + "- " + name + ": " + value + "\n"); break;
                case "remove" : sb.append(space.repeat(2) + "- " + name + ": " + value + "\n"); break;
                default :
                    System.out.println("Unknown status");
            }
        }
        return sb.append("}").toString().trim();
    }
}
