package sample.write;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteFile {
    public static  <T> void serialization(List<T> list, Class<T> clazz) {
        try (FileWriter writer = new FileWriter(new File(clazz.getSimpleName() + ".fly"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            writer.write(objectMapper.writeValueAsString(list));
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
