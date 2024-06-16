package hexlet.code;

public final class Node {
    private String name;
    private Object value;
    private String status;

    public Node(String name, Object value, String status) {
        this.name = name;
        this.value = value;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public String getStatus() {
        return status;
    }

}
