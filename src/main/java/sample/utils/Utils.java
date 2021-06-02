package sample.utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import sample.data.Engineer;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;

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

    /**
     * Группа методов, которые при редактировании самолета заполняют ChoiceBox агрегатами,которые уставлены на этот самолет
     */
    public static int setEngineIndex(Engine engine) {
        int index = -1;
        try {
            for (int i = 0; i < SaveData.enginesList.size(); i++) {
                if (engine.getSerialNumberEngine().equals(SaveData.enginesList.get(i).getSerialNumberEngine())) {
                    index = i;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Нет двигателя на самолете");
        }
        return index;
    }

    public static int setKsaIndex(Ksa ksa) {
        int index = -1;
        try {
            for (int i = 0; i < SaveData.ksaList.size(); i++) {
                if (ksa.getSerialNumberKsa().equals(SaveData.ksaList.get(i).getSerialNumberKsa())) {
                    index = i;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Нет КСА на самолете");
        }
        return index;
    }

    public static int setPlanerIndex(Planer planer) {
        int index = -1;
        for (int i = 0; i < SaveData.planersList.size(); i++) {
            if (planer.getSideNumber().equals(SaveData.planersList.get(i).getSideNumber())) {
                index = i;
            }
        }
        return index;
    }

    public static int setMainBreakIndex(MainBreak mainBreak) {
        int index = -1;
        for (int i = 0; i < SaveData.mainBreaksList.size(); i++) {
            if (mainBreak.getSerialNumber().equals(SaveData.mainBreaksList.get(i).getSerialNumber())) {
                index = i;
            }
        }
        return index;
    }

    public static int setMainWheelIndex(MainWheel mainWheel) {
        int index = -1;
        for (int i = 0; i < SaveData.mainWheelsList.size(); i++) {
            if (mainWheel.getSerialNumber().equals(SaveData.mainWheelsList.get(i).getSerialNumber())) {
                index = i;
            }
        }
        return index;
    }

    public static int setFrontBreakIndex(FrontBreak frontBreak) {
        int index = -1;
        for (int i = 0; i < SaveData.frontBreaksList.size(); i++) {
            if (frontBreak.getSerialNumber().equals(SaveData.frontBreaksList.get(i).getSerialNumber())) {
                index = i;
            }
        }
        return index;
    }

    public static int setFrontWheelIndex(FrontWheel frontWheel) {
        int index = -1;
        for (int i = 0; i < SaveData.frontWheelsList.size(); i++) {
            if (frontWheel.getSerialNumber().equals(SaveData.frontWheelsList.get(i).getSerialNumber())) {
                index = i;
            }
        }
        return index;
    }

    public static int setCylinderIndex(CylinderOfRetractionExtension cylinder) {
        int index = -1;
        for (int i = 0; i < SaveData.cylindersList.size(); i++) {
            if (cylinder.getSerialNumber().equals(SaveData.cylindersList.get(i).getSerialNumber())) {
                index = i;
            }
        }
        return index;
    }

    public static int setEngineerIndex(Engineer engineer) {
        int index = -1;
        for (int i = 0; i < SaveData.engineersList.size(); i++) {
            if (engineer.getFullName().equals(SaveData.engineersList.get(i).getFullName())) {
                index = i;
            }
        }
        return index;
    }
}
