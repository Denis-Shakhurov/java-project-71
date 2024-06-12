package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDiffer {
    private Path getPath(String filename) {
        return Paths.get("src/test/resources", filename).toAbsolutePath().normalize();
    }

    private String read(String filename) throws IOException {
        Path path = getPath(filename);
        return Files.readString(path);
    }
    /*@Test
    public void testGetData() throws IOException {
        File file = new File("src\\test\\resources\\file1.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() { });

        assertTrue(map.containsKey("timeout"));
        assertEquals(map.get("host"), "hexlet.io");
    }*/
    @Test
    public void testGenerate() {
        String expected = null;
        try {
            expected = read("expected.Json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String actual = Differ.generate(getPath("testFile1.json"), getPath("testFile2.json"));
        assertEquals(expected, actual);
    }
}
