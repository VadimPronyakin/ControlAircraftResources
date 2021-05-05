package sample.output;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputFileStream {
    public static  <T> void serialization(List<T> list) {
        try (FileWriter writer = new FileWriter(new File("C:\\Users\\Vadim\\Desktop\\ListOfEngineers.fly"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            writer.write(objectMapper.writeValueAsString(list));
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
