package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class TestDiffer {
    private Path getPath(String filename) {
        return Paths.get("src/test/resources", filename).toAbsolutePath().normalize();
    }

    private String read(String filename) throws IOException {
        Path path = getPath(filename);
        return Files.readString(path);
    }
    @Test
    public void testParse() {
        Path path = getPath("testFile1.json");
        Map<String, Object> map = Parser.parse(path);
        assertTrue(map.containsKey("timeout"));
        assertEquals(map.get("host"), "hexlet.io");
    }
    @Test
    public void testGenerateJSON() {
        String expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
        String actual = Differ.generate(getPath("testFile1.json"),
                getPath("testFile2.json"), "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateYAML() {
        String expected = "{\n" +
                "  + chars1: [a, b, c]\n" +
                "  - chars2: [d, e, f]\n" +
                "  + checked: true\n" +
                "  - default: null\n" +
                "  - id: 45\n" +
                "  + id: null\n" +
                "  - key1: value1\n" +
                "  + key2: value2\n" +
                "  - numbers1: [1, 2, 3, 4]\n" +
                "  + numbers2: [22, 33, 44, 55]\n" +
                "  + numbers4: [4, 5, 6]\n" +
                "  - setting1: Some value\n" +
                "  + setting1: Another value\n" +
                "  - setting2: 200\n" +
                "  + setting2: 300\n" +
                "}";
        String actual = Differ.generate(getPath("testFile1N.yml"),
                getPath("testFile2N.yml"), "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateJSONPlain() {
        String expected = "Property 'chars2' was update. From [complex value] to false\n" +
                "Property 'checked' was update. From false to true\n" +
                "Property 'default' was update. From null to [complex value]\n" +
                "Property 'id' was update. From 45 to null\n" +
                "Property 'key1' was removed\n" +
                "Property 'key2' was added with value: 'value2'\n" +
                "Property 'numbers2' was update. From [complex value] to [complex value]\n" +
                "Property 'numbers3' was removed\n" +
                "Property 'numbers4' was added with value: [complex value]\n" +
                "Property 'obj1' was added with value: [complex value]\n" +
                "Property 'setting1' was update. From 'Some value' to 'Another value'\n" +
                "Property 'setting2' was update. From 200 to 300\n" +
                "Property 'setting3' was update. From true to 'none'";
        String actual = Differ.generate(getPath("testNestedJSON1.json"),
                getPath("testNestedJSON2.json"), "plain");
        assertEquals(expected, actual);
    }
    @Test
    public void testSerializingJSON() throws IOException {
        String expected = read("expectedJSON.json");
        String actual = Differ.generate(getPath("testNestedJSON1.json"),
                getPath("testNestedJSON2.json"), "json");
        assertEquals(expected, actual);
    }
}
