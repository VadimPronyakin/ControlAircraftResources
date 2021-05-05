package sample.createNewObject;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.data.Engineer;
import sample.data.enums.Rank;

public class CreateEngineer {
    public static Engineer createNewEngineer(Rank rank, String name) {

        Engineer engineer = Engineer.builder()
                .rank(rank)
                .fullName(name)
                .build();
        return engineer;
    }
}
