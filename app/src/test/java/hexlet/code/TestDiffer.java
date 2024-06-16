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
    public void testParse() throws IOException {
        Path path = getPath("testFile1.json");
        Map<String, Object> map = Parser.parse(path);
        assertTrue(map.containsKey("timeout"));
        assertEquals(map.get("host"), "hexlet.io");
    }
    @Test
    public void testGenerateJSON() throws IOException {
        String expected = read("expectedJson.txt");
        String actual = Differ.generate(getPath("testFile1.json"),
                getPath("testFile2.json"), "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateYAML() throws IOException {
        String expected = read("expectedYAML.txt");
        String actual = Differ.generate(getPath("testFile1N.yml"),
                getPath("testFile2N.yml"), "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateJSONPlain() throws IOException {
        String expected = read("expectedJSONPlain.txt");
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
