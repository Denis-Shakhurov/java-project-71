package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
        Map<String, Object> map = Parser.parse(path, ".json");
        assertTrue(map.containsKey("timeout"));
        assertEquals(map.get("host"), "hexlet.io");
    }
    @Test
    public void testGenerateJSON() throws Exception {
        String expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
        String actual = Differ.generate(getPath("testFile1.json").toString(),
                getPath("testFile2.json").toString(), "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateYAML() throws Exception {
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
        String actual = Differ.generate(getPath("testFile1N.yml").toString(),
                getPath("testFile2N.yml").toString(), "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateJSONPlain() throws Exception {
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
        String actual = Differ.generate(getPath("testNestedJSON1.json").toString(),
                getPath("testNestedJSON2.json").toString(), "plain");
        assertEquals(expected, actual);
    }
    @Test
    public void testSerializingJSON() throws Exception {
        String expected = read("expectedJSON.json");
        String actual = Differ.generate(getPath("testNestedJSON1.json").toString(),
                getPath("testNestedJSON2.json").toString(), "json");
        assertEquals(expected, actual);
    }
}
