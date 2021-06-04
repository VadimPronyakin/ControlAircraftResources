package service;

import constants.TextConstants;
import data.Aircraft;
import data.Engineer;
import data.SaveData;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.*;
import data.enums.Rank;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.util.Date;

import static utils.Utils.openInformationWindow;

@Slf4j
public class DuplicateProtection {

    /**
     * Метод проверяет самолеты на совпадение бортовых номеров, в случае совпадения возвращает значение false.
     */
    public static boolean aircraftProtection(TextField serialNumber) {
        boolean isCreate = true;
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getAircraftNumber().equals(serialNumber.getText())) {
                isCreate = false;
                openInformationWindow(TextConstants.DUPLICATION_AIRCRAFT);
            }
        }
        return isCreate;
    }

    /**
     * Метод проверяет двигатели на совпадение серийных номеров, в случае совпадения возвращает значение false.
     */
    public static boolean engineProtection(TextField serialNumber) {
        boolean isCreate = true;
        for (Engine engine : SaveData.enginesList) {
            if (engine.getSerialNumber().equals(serialNumber.getText())) {
                isCreate = false;
                openInformationWindow(TextConstants.DUPLICATION_ENGINE);
            }
        }
        return isCreate;
    }

    /**
     * Метод проверяет КСА на совпадение серийных номеров, в случае совпадения возвращает значение false.
     */
    public static boolean ksaProtection(TextField serialNumber) {
        boolean isCreate = true;
        for (Ksa ksa : SaveData.ksaList) {
            if (ksa.getSerialNumber().equals(serialNumber.getText())) {
                isCreate = false;
                openInformationWindow(TextConstants.DUPLICATION_KSA);
            }
        }
        return isCreate;
    }

    /**
     * Метод проверяет планеры на совпадение бортовых номеров, в случае совпадения возвращает значение false.
     */
    public static boolean planerProtection(TextField serialNumber) {
        boolean isCreate = true;
        for (Planer planer : SaveData.planersList) {
            if (planer.getSerialNumber().equals(serialNumber.getText())) {
                isCreate = false;
                openInformationWindow(TextConstants.DUPLICATION_PLANER);
            }
        }
        return isCreate;
    }

    /**
     * Метод проверяет основные тормоза на совпадение серийных номеров, в случае совпадения возвращает значение false.
     */
    public static boolean mainBreakProtection(TextField serialNumber) {
        boolean isCreate = true;
        for (MainBreak mainBreak : SaveData.mainBreaksList) {
            if (mainBreak.getSerialNumber().equals(serialNumber.getText())) {
                isCreate = false;
                openInformationWindow(TextConstants.DUPLICATION_MAIN_BREAK);
            }
        }
        return isCreate;
    }

    /**
     * Метод проверяет основные колеса на совпадение серийных номеров, в случае совпадения возвращает значение false.
     */
    public static boolean mainWheelProtection(TextField serialNumber) {
        boolean isCreate = true;
        for (MainWheel mainWheel : SaveData.mainWheelsList) {
            if (mainWheel.getSerialNumber().equals(serialNumber.getText())) {
                isCreate = false;
                openInformationWindow(TextConstants.DUPLICATION_MAIN_WHEEL);
            }
        }
        return isCreate;
    }

    /**
     * Метод проверяет передние тормоза на совпадение серийных номеров, в случае совпадения возвращает значение false.
     */
    public static boolean frontBreakProtection(TextField serialNumber) {
        boolean isCreate = true;
        for (FrontBreak frontBreak : SaveData.frontBreaksList) {
            if (frontBreak.getSerialNumber().equals(serialNumber.getText())) {
                isCreate = false;
                openInformationWindow(TextConstants.DUPLICATION_FRONT_BREAK);
            }
        }
        return isCreate;
    }

    /**
     * Метод проверяет передние колеса на совпадение серийных номеров, в случае совпадения возвращает значение false.
     */
    public static boolean frontWheelProtection(TextField serialNumber) {
        boolean isCreate = true;
        for (FrontWheel frontWheel : SaveData.frontWheelsList) {
            if (frontWheel.getSerialNumber().equals(serialNumber.getText())) {
                isCreate = false;
                openInformationWindow(TextConstants.DUPLICATION_FRONT_WHEEL);
            }
        }
        return isCreate;
    }

    /**
     * Метод проверяет цилиндры подкоса на совпадение серийных номеров, в случае совпадения возвращает значение false.
     */
    public static boolean cylinderProtection(TextField serialNumber) {
        boolean isCreate = true;
        for (CylinderOfRetractionExtension cylinder : SaveData.cylindersList) {
            if (cylinder.getSerialNumber().equals(serialNumber.getText())) {
                isCreate = false;
                openInformationWindow(TextConstants.DUPLICATION_CYLINDER);
            }
        }
        return isCreate;
    }

    /**
     * Метод проверяет инженеров(ак) на совпадение по званию и полному имени, в случае совпадения возвращает значение false.
     */
    public static boolean engineerProtection(TextField fullName, ComboBox<Rank> ranks) {
        boolean isCreate = true;
        for (Engineer engineer : SaveData.engineersList) {
            if (engineer.getFullName().equals(fullName.getText())
                    && engineer.getRank().getDescription().equals(
                    ranks.getSelectionModel().getSelectedItem().getDescription())) {
                isCreate = false;
                openInformationWindow(TextConstants.DUPLICATION_ENGINEER);
            }
        }
        return isCreate;
    }

    /**
     * Метод проверятет, что при редактированиии инженера(ак) на самолете, пользователь меняет номер приказа,
     * если номер приказа остается прежним,а инженер(ак) изменен, появляется всплывающее окно с предупрежденме
     */
    public static boolean changesEngineerProtection(Aircraft aircraft,
                                                    ComboBox<Engineer> iak,
                                                    TextField orderNumber,
                                                    DatePicker orderDate) {
        boolean isChange = true;
        Date dateNewOrder = Date.from(orderDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (!iak.getSelectionModel().getSelectedItem().getFullName().equals(aircraft.getIak().getFullName())) {
            if (dateNewOrder.equals(aircraft.getOrderDate())
                    || orderNumber.getText().equals(String.valueOf(aircraft.getOrderNumber()))) {
                isChange = false;
                openInformationWindow(TextConstants.CHANGE_ENGINEER_IN_AIRCRAFT);
            }
        }
        return isChange;
    }
}
