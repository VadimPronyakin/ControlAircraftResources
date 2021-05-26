package sample.update;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import java.util.List;

public class UpdateList {
    public static <T> void updateList(List<T> list, TableView<T> tableView, String textConstants) {
        if (list.size() == 0) {
            tableView.setPlaceholder(new Label(textConstants));
        }
        tableView.getItems().clear();
        for (int i = 0; i < list.size(); i++) {
        tableView.getItems().addAll(list.get(i));
        }
    }
}
