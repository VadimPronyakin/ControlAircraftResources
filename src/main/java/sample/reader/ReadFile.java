package sample.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public static <T> List<T> readInfo(Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileReader reader = new FileReader(clazz.getSimpleName() + ".fly")) {
            CollectionType listFromSave = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return objectMapper.readValue(reader, listFromSave);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
