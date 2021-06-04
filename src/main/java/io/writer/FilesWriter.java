package io.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FilesWriter {
    /**
     * Метод сериализации данных в текстовые файлы
     */
    public static <T> void serialization(List<T> list, Class<T> clazz) {
        try (FileWriter writer = new FileWriter(new File(clazz.getSimpleName() + ".fly"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            writer.write(objectMapper.writeValueAsString(list));
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
