package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class TestDiffer {
    private Path pathFile1 = Path.of("C:\\Users\\disa_\\IdeaProjects\\java-project-71\\app\\src\\test\\resources\\testFile1.json");
    private Path pathFile2 = Path.of("C:\\Users\\disa_\\IdeaProjects\\java-project-71\\app\\src\\test\\resources\\testFile2.json");
    @Test
    public void testGetData() throws IOException {
        File file = new File("src\\test\\resources\\file1.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() { });

        assertTrue(map.containsKey("timeout"));
        assertEquals(map.get("host"), "hexlet.io");
    }
    @Test
    public void testGenerate() {
        String expected = "{\n" +
                "- follow: false\n" +
                "- host: hexlet.com\n" +
                "+ host: hexlet.io\n" +
                "- timeout: 50\n" +
                "+ timeout: 20\n" +
                "+ verbose: true\n" + "}";

        String actual = Differ.generate(pathFile1, pathFile2);
        assertEquals(expected, actual);
    }
}
