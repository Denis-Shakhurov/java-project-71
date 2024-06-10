package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestDiffer {
    @Test
    public void testGetData() throws IOException {
        File file = new File("src\\test\\resources\\file1.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() { });

        assertTrue(map.containsKey("timeout"));
        assertEquals(map.get("host"), "hexlet.io");
    }
}
