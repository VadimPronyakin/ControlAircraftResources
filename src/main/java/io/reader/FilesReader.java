package io.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesReader {
    /**
     * Метод десериализации данных из текстовых файлов
     */
    public static <T> List<T> readInfo(Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try (java.io.FileReader reader = new java.io.FileReader(clazz.getSimpleName() + ".fly")) {
            CollectionType listFromSave = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return objectMapper.readValue(reader, listFromSave);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
