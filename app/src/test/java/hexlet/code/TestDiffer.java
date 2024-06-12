package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class TestDiffer {
    private Path getPath(String filename) {
        return Paths.get("src/test/resources", filename).toAbsolutePath().normalize();
    }

    private String read(String filename) throws IOException {
        Path path = getPath(filename);
        return Files.readString(path);
    }
    @Test
    public void testGetData() throws IOException {
        Path path = getPath("testFile1.json");
        Map<String, Object> map = Differ.getData(path);
        assertTrue(map.containsKey("timeout"));
        assertEquals(map.get("host"), "hexlet.com");
    }
    @Test
    public void testGenerate() {
        String expected = null;
        try {
            expected = read("expectedJson.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String actual = Differ.generate(getPath("testFile1.json"), getPath("testFile2.json"));
        assertEquals(expected, actual);
    }
}
