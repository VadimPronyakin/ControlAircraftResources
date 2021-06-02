package sample.delete;

import javafx.scene.control.TableView;
import sample.write.WriteFile;

import java.util.List;

/**
 * Метод удаляет объект из списка
 */
public class DeleteObject {
    public static <T> void delete(List<T> list, TableView<T> tableView, Class<T> clazz) {
        list.remove(tableView.getSelectionModel().getSelectedItem());
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
        tableView.refresh();
        WriteFile.serialization(list, clazz);
    }
}
