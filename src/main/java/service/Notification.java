package service;

import constants.TextConstants;
import data.Aircraft;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.*;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.List;

public class Notification {

    /**
     * Метод в зависимости от значения переменной boolean isNeedAttention  делает видимыми текстовые уведомления,
     * указывающие на необходимость проверки остатка ресурсов агрегатов,у которых остался критический запас
     */
    public static void notifiesAircraft(Aircraft aircraft, Text[] textOfAlarm) {
        if (aircraft.getLeftEngine() == null) {
            System.out.println("Нет левого двигателя на самолете");
        } else if (aircraft.getLeftEngine().getIsNeedAttention()) {
            textOfAlarm[0].setVisible(true);
        }
        if (aircraft.getRightEngine() == null) {
            System.out.println("Нет правого двигателя на самолете");
        } else if (aircraft.getRightEngine().getIsNeedAttention()) {
            textOfAlarm[1].setVisible(true);
        }
        if (aircraft.getKsa() == null) {
            System.out.println("Нет КСА на самолете");
        } else if (aircraft.getKsa().getIsNeedAttention()) {
            textOfAlarm[2].setVisible(true);
        }
        if (aircraft.getPlaner().getIsNeedAttention()) {
            textOfAlarm[3].setVisible(true);
        }
        if (aircraft.getLeftMainWheel().getIsNeedAttention()) {
            textOfAlarm[4].setVisible(true);
        }
        if (aircraft.getRightMainWheel().getIsNeedAttention()) {
            textOfAlarm[5].setVisible(true);
        }
        if (aircraft.getLeftFrontWheel().getIsNeedAttention()) {
            textOfAlarm[6].setVisible(true);
        }
        if (aircraft.getRightFrontWheel().getIsNeedAttention()) {
            textOfAlarm[7].setVisible(true);
        }
        if (aircraft.getLeftMainBrake().getIsNeedAttention()) {
            textOfAlarm[8].setVisible(true);
        }
        if (aircraft.getRightMainBrake().getIsNeedAttention()) {
            textOfAlarm[9].setVisible(true);
        }
        if (aircraft.getLeftFrontBrake().getIsNeedAttention()) {
            textOfAlarm[10].setVisible(true);
        }
        if (aircraft.getRightFrontBrake().getIsNeedAttention()) {
            textOfAlarm[11].setVisible(true);
        }
        if (aircraft.getLeftMainCylinder().getIsNeedAttention()) {
            textOfAlarm[12].setVisible(true);
        }
        if (aircraft.getRightMainCylinder().getIsNeedAttention()) {
            textOfAlarm[13].setVisible(true);
        }
        if (aircraft.getFrontCylinder().getIsNeedAttention()) {
            textOfAlarm[14].setVisible(true);
        }
    }

    /**
     * Метод делает видимыми текстовые уведомления в карточке двигателя, указывающие на необходимость проверки остатка ресурсов
     * при условии if (true)
     */
    public static void notificationEngine(Engine engine, List<Text> textOfAlarm) {
        if (engine.getResourceReserveBefore_10hours() <= 270) {
            textOfAlarm.get(0).setVisible(true);
        }
        if (engine.getResourceReserveBefore_25hours() <= 300) {
            textOfAlarm.get(1).setVisible(true);
        }
        if (engine.getResourceReserveBefore_50hours() <= 300) {
            textOfAlarm.get(2).setVisible(true);
        }
        if (engine.getResourceReserveBefore_100hours() <= 600) {
            textOfAlarm.get(3).setVisible(true);
        }
        if (engine.getResourceReserveBefore_150hours() <= 600) {
            textOfAlarm.get(4).setVisible(true);
        }
        if (engine.getResourceReserveBefore_278bulletin() <= 300) {
            textOfAlarm.get(5).setVisible(true);
        }
        if (engine.getOilChange() <= 600) {
            textOfAlarm.get(6).setVisible(true);
        }
    }

    /**
     * Метод делает видимыми текстовые уведомления в карточке КСА, указывающие на необходимость проверки остатка ресурсов
     * при условии if (true)
     */
    public static void notificationKsa(Ksa ksa, Text alarm_25hours, Text alarmOilChange) {
        if (ksa.getResource_Reserve_Before_25hours() <= 300) {
            alarm_25hours.setVisible(true);
        }
        if (ksa.getOilChange() <= 600) {
            alarmOilChange.setVisible(true);
        }
    }

    /**
     * Метод делает видимыми текстовые уведомления в карточке цилиндра подкоса,
     * указывающие на необходимость проверки остатка ресурсов, при условии if (true)
     */
    public static void notificationCylinder(CylinderOfRetractionExtension cylinder,
                                            Text alarmFirstRepair, Text alarmSecondRepair, Text alarmReplacement) {
        if (cylinder.getResource_Reserve_Before_First_Repair() == 0) {
            alarmFirstRepair.setStyle("-fx-fill: green");
            alarmFirstRepair.setText(TextConstants.REPAIR_COMPLETED);
            alarmFirstRepair.setVisible(true);
        } else if (cylinder.getResource_Reserve_Before_First_Repair() <= 15
            && (cylinder.getResource_Reserve_Before_First_Repair() != 0)) {
            alarmFirstRepair.setVisible(true);
        }
        if (cylinder.getResource_Reserve_Before_Second_Repair() == 0) {
            alarmSecondRepair.setStyle("-fx-fill: green");
            alarmSecondRepair.setText(TextConstants.REPAIR_COMPLETED);
            alarmSecondRepair.setVisible(true);

        } else if (cylinder.getResource_Reserve_Before_Second_Repair() <= 15
            && cylinder.getResource_Reserve_Before_Second_Repair() != 0) {
            alarmSecondRepair.setVisible(true);
        }
        if (cylinder.getResource_Reserve_Before_Replacement() <= 15) {
            alarmReplacement.setVisible(true);
        }
    }

    /**
     * Метод делает видимыми текстовые уведомления в карточке переднего тормоза,
     * указывающие на необходимость проверки остатка ресурсов, при условии if (true)
     */
    public static void notificationFrontBreak(FrontBreak frontBreak, Text alarmFirstRepair, Text alarmReplacement) {
        if (frontBreak.getResource_Reserve_Before_First_Repair() == 0) {
            alarmFirstRepair.setStyle("-fx-fill: green");
            alarmFirstRepair.setText(TextConstants.REPAIR_COMPLETED);
            alarmFirstRepair.setVisible(true);
        }
       else if (frontBreak.getResource_Reserve_Before_First_Repair() <= 15
            && frontBreak.getResource_Reserve_Before_First_Repair() != 0) {
            alarmFirstRepair.setVisible(true);
        }
        if (frontBreak.getResource_Reserve_Before_Replacement() <= 15) {
            alarmReplacement.setVisible(true);
        }
    }

    /**
     * Метод делает видимыми текстовые уведомления в карточке переднего колеса,
     * указывающие на необходимость проверки остатка ресурсов, при условии if (true)
     */
    public static void notificationFrontWheel(FrontWheel frontWheel, Text alarmReplacement) {
        if (frontWheel.getResource_Reserve_Replacement_Wheel() <= 15) {
            alarmReplacement.setVisible(true);
        }
    }

    /**
     * Метод делает видимыми текстовые уведомления в карточке основного тормоза,
     * указывающие на необходимость проверки остатка ресурсов, при условии if (true)
     */
    public static void notificationMainBreak(MainBreak mainBreak, List<Text> textOfAlarm) {
        if (mainBreak.getResource_Reserve_Replacement_Break() <= 15) {
            textOfAlarm.get(0).setVisible(true);
        }
        if (mainBreak.getResource_Reserve_Replacement_RotatingDisks() <= 15) {
            textOfAlarm.get(1).setVisible(true);
        }
        if (mainBreak.getResource_Reserve_Replacement_NonRotatingDisks() <= 15) {
            textOfAlarm.get(2).setVisible(true);

        }
        if (mainBreak.getResource_Reserve_Replacement_PressureDisk() == 0) {
            textOfAlarm.get(3).setStyle("-fx-fill: green");
            textOfAlarm.get(3).setText(TextConstants.DISK_REPLACED);
            textOfAlarm.get(3).setVisible(true);
        } else if (mainBreak.getResource_Reserve_Replacement_PressureDisk() <= 15
            && mainBreak.getResource_Reserve_Replacement_PressureDisk() != 0) {
            textOfAlarm.get(3).setVisible(true);
        }
        if (mainBreak.getResource_Reserve_Replacement_ReferenceDisk() == 0) {
            textOfAlarm.get(4).setStyle("-fx-fill: green");
            textOfAlarm.get(4).setText(TextConstants.DISK_REPLACED);
            textOfAlarm.get(4).setVisible(true);
        } else if (mainBreak.getResource_Reserve_Replacement_ReferenceDisk() <= 15
            && mainBreak.getResource_Reserve_Replacement_ReferenceDisk() != 0) {
            textOfAlarm.get(4).setVisible(true);
        }
    }

    /**
     * Метод делает видимыми текстовые уведомления в карточке основного колеса,
     * указывающие на необходимость проверки остатка ресурсов, при условии if (true)
     */
    public static void notificationMainWheel(MainWheel mainWheel, Text alarmReplacement) {
        if (mainWheel.getResource_Reserve_Replacement_Wheel() <= 15) {
            alarmReplacement.setVisible(true);
        }
    }

    /**
     * Метод делает видимыми текстовые уведомления в карточке планера,
     * указывающие на необходимость проверки остатка ресурсов, при условии if (true)
     */
    public static void notificationPlaner(Planer planer,
                                          Label before_30Days,
                                          Label before_6Months,
                                          List<Text> textOfAlarm) {
        if (planer.getResource_Reserve_Before_100hours() <= 600) {
            textOfAlarm.get(2).setVisible(true);
        }
        if (planer.getResource_Reserve_Before_200hours() <= 600) {
            textOfAlarm.get(3).setVisible(true);
        }
        if (Integer.parseInt(before_30Days.getText()) <= 5) {
            textOfAlarm.get(0).setVisible(true);
        }
        if (Integer.parseInt(before_6Months.getText()) <= 5) {
            textOfAlarm.get(1).setVisible(true);
        }
    }
}



