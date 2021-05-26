package sample.visible;

import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.awt.*;

public class ButtonVisible {
    public static void setButtonVisible(Button create,
                                    Button change,
                                    Button createForAircraft,
                                    Button makeWorks,
                                    Text selectionWorks,
                                    ComboBox listOfWorks,
                                    String string) {
        if (string.equals("Добавить")) {
            create.setVisible(true);
            createForAircraft.setVisible(false);
        } else if (string.equals("Изменить запись")) {
            change.setVisible(true);
            createForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createForAircraft.setVisible(false);
        } else if (string.equals("Выполнить работы")) {
            createForAircraft.setVisible(false);
            selectionWorks.setVisible(true);
            listOfWorks.setVisible(true);
            makeWorks.setVisible(true);
        }
    }
}
