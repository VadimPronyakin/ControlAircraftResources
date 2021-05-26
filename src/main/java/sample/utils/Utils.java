package sample.utils;


import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;

public class Utils {
    public static boolean checkInput(TextField... fields) {
        for (TextField field : fields) {
            if (StringUtils.isBlank(field.getCharacters())) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkInputPlaner(DatePicker date, DatePicker date2, DatePicker date3, TextField... fields) {
        for (TextField field : fields) {
            if (StringUtils.isBlank(field.getCharacters())) {
                StringUtils.isBlank((date.getEditor().getCharacters()));
                StringUtils.isBlank((date2.getEditor().getCharacters()));
                StringUtils.isBlank((date3.getEditor().getCharacters()));
                return false;
            }
        }
        return true;
    }
}
