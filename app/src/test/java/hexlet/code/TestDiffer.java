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
        return Paths.get("src/test/resources/fixtures/", filename).toAbsolutePath().normalize();
    }

    private String read(String filename) throws IOException {
        Path path = getPath(filename);
        return Files.readString(path);
    }
    @Test
    public void testGenerateJSON() throws Exception {
        String expected = read("expectedJSONToStylish.txt");
        String actual = Differ.generate(getPath("testNestedJSON1.json").toString(),
                getPath("testNestedJSON2.json").toString());
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateYAML() throws Exception {
        String expected = read("expectedYAMLToStylish.txt");
        String actual = Differ.generate(getPath("testNestedYAML1.yml").toString(),
                getPath("testNestedYAML2.yml").toString());
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateJSONToStylish() throws Exception {
        String expected = read("expectedJSONToStylish.txt");
        String actual = Differ.generate(getPath("testNestedJSON1.json").toString(),
                getPath("testNestedJSON2.json").toString(), "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateYAMLToStylish() throws Exception {
        String expected = read("expectedYAMLToStylish.txt");
        String actual = Differ.generate(getPath("testNestedYAML1.yml").toString(),
                getPath("testNestedYAML2.yml").toString(), "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateJSONToPlain() throws Exception {
        String expected = read("expectedJSONToPlain.txt");
        String actual = Differ.generate(getPath("testNestedJSON1.json").toString(),
                getPath("testNestedJSON2.json").toString(), "plain");
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateYAMLToPlain() throws Exception {
        String expected = read("expectedYAMLToPlain.txt");
        String actual = Differ.generate(getPath("testNestedYAML1.yml").toString(),
                getPath("testNestedYAML2.yml").toString(), "plain");
        assertEquals(expected, actual);
    }
    @Test
    public void testSerializingJSONToJSON() throws Exception {
        String expected = read("expectedJSON.json");
        String actual = Differ.generate(getPath("testNestedJSON1.json").toString(),
                getPath("testNestedJSON2.json").toString(), "json");
        assertEquals(expected, actual);
    }
    @Test
    public void testSerializingYAMLToJSON() throws Exception {
        String expected = read("expectedYAMLToJSON.json");
        String actual = Differ.generate(getPath("testNestedYAML1.yml").toString(),
                getPath("testNestedYAML2.yml").toString(), "json");
        assertEquals(expected, actual);
    }
}
