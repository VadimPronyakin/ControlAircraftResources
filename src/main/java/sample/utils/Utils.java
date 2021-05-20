package sample.utils;


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
}
