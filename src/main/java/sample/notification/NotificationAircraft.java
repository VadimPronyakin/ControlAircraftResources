package sample.notification;

import javafx.scene.control.Label;
import javafx.scene.text.Text;
import sample.data.Aircraft;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;

import java.util.List;

public class NotificationAircraft {
    public static void notifiesAircraft(Aircraft aircraft, Text[] texts) {
        if (aircraft.getLeftEngine() == null) {
            System.out.println("Нет левого двигателя на самолете");
        } else if (aircraft.getLeftEngine().getIsNeedAttention() == true) {
            texts[0].setVisible(true);
        }
        if (aircraft.getRightEngine() == null) {
            System.out.println("Нет правого двигателя на самолете");
        } else if (aircraft.getRightEngine().getIsNeedAttention() == true) {
            texts[1].setVisible(true);
        }
        if (aircraft.getKsa() == null) {
            System.out.println("Нет КСА на самолете");
        } else if (aircraft.getKsa().getIsNeedAttention() == true) {
            texts[2].setVisible(true);
        }
        if (aircraft.getPlaner().getIsNeedAttention() == true) {
            texts[3].setVisible(true);
        }
        if (aircraft.getLeftMainWheel().getIsNeedAttention() == true) {
            texts[4].setVisible(true);
        }
        if (aircraft.getRightMainWheel().getIsNeedAttention() == true) {
            texts[5].setVisible(true);
        }
        if (aircraft.getLeftFrontWheel().getIsNeedAttention() == true) {
            texts[6].setVisible(true);
        }
        if (aircraft.getRightFrontWheel().getIsNeedAttention() == true) {
            texts[7].setVisible(true);
        }
        if (aircraft.getLeftMainBrake().getIsNeedAttention() == true) {
            texts[8].setVisible(true);
        }
        if (aircraft.getRightMainBrake().getIsNeedAttention() == true) {
            texts[9].setVisible(true);
        }
        if (aircraft.getLeftFrontBrake().getIsNeedAttention() == true) {
            texts[10].setVisible(true);
        }
        if (aircraft.getRightFrontBrake().getIsNeedAttention() == true) {
            texts[11].setVisible(true);
        }
        if (aircraft.getLeftMainCylinder().getIsNeedAttention() == true) {
            texts[12].setVisible(true);
        }
        if (aircraft.getRightMainCylinder().getIsNeedAttention() == true) {
            texts[13].setVisible(true);
        }
        if (aircraft.getFrontCylinder().getIsNeedAttention() == true) {
            texts[14].setVisible(true);
        }

    }

    public static void notificationEngine(Engine engine, List<Text> list) {
        if (engine.getResourceReserveBefore_10hours() <= 270) {
            list.get(0).setVisible(true);
        }
        if (engine.getResourceReserveBefore_25hours() <= 300) {
            list.get(1).setVisible(true);
        }
        if (engine.getResourceReserveBefore_50hours() <= 300) {
            list.get(2).setVisible(true);
        }
        if (engine.getResourceReserveBefore_100hours() <= 600) {
            list.get(3).setVisible(true);
        }
        if (engine.getResourceReserveBefore_150hours() <= 600) {
            list.get(4).setVisible(true);
        }
        if (engine.getResourceReserveBefore_278bulletin() <= 300) {
            list.get(5).setVisible(true);
        }
        if (engine.getOilChange() <= 600) {
            list.get(6).setVisible(true);
        }
    }

    public static void notificationKsa(Ksa ksa, Text text1, Text text2) {
        if (ksa.getResource_Reserve_Before_25hours() <= 300) {
            text1.setVisible(true);
        }
        if (ksa.getOilChange() <= 600) {
            text2.setVisible(true);
        }
    }

    public static void notificationCylinder(CylinderOfRetractionExtension cylinder,
                                            Text text1, Text text2, Text text3) {
        if (cylinder.getResource_Reserve_Before_First_Repair() <= 15) {
            text1.setVisible(true);
        }
        if (cylinder.getResource_Reserve_Before_Second_Repair() <= 15) {
            text2.setVisible(true);
        }
        if (cylinder.getResource_Reserve_Before_Replacement() <= 15) {
            text3.setVisible(true);
        }
    }

    public static void notificationFrontBreak(FrontBreak frontBreak, Text text1, Text text2) {
        if (frontBreak.getResource_Reserve_Before_First_Repair() <= 15) {
            text1.setVisible(true);
        }
        if (frontBreak.getResource_Reserve_Before_Replacement() <= 15) {
            text2.setVisible(true);
        }
    }

    public static void notificationFrontWheel(FrontWheel frontWheel, Text text) {
        if (frontWheel.getResource_Reserve_Replacement_Wheel() <= 15) {
            text.setVisible(true);
        }
    }

    public static void notificationMainBreak(MainBreak mainBreak, List<Text> list) {
        if (mainBreak.getResource_Reserve_Replacement_Break() <= 15) {
            list.get(0).setVisible(true);
        }
        if (mainBreak.getResource_Reserve_Replacement_RotatingDisks() <= 15) {
            list.get(1).setVisible(true);
        }
        if (mainBreak.getResource_Reserve_Replacement_NonRotatingDisks() <= 15) {
            list.get(2).setVisible(true);

        }
        if (mainBreak.getResource_Reserve_Replacement_PressureDisk() == 0) {
            list.get(3).setStyle("-fx-fill: green");
            list.get(3).setText("ДИСК ЗАМЕНЕН");
            list.get(3).setVisible(true);
        } else if (mainBreak.getResource_Reserve_Replacement_PressureDisk() <= 15) {
            list.get(3).setVisible(true);
        }
        if (mainBreak.getResource_Reserve_Replacement_ReferenceDisk() == 0) {
            list.get(4).setStyle("-fx-fill: green");
            list.get(4).setText("ДИСК ЗАМЕНЕН");
            list.get(4).setVisible(true);
        } else if (mainBreak.getResource_Reserve_Replacement_ReferenceDisk() <= 15) {
            list.get(4).setVisible(true);
        }
    }

    public static void notificationMainWheel(MainWheel mainWheel, Text text) {
        if (mainWheel.getResource_Reserve_Replacement_Wheel() <= 15) {
            text.setVisible(true);
        }
    }

    public static void notificationPlaner(Planer planer, Label label1, Label label2, List<Text> list) {
        if (planer.getResource_Reserve_Before_100hours() <= 600) {
            list.get(2).setVisible(true);
        }
        if (planer.getResource_Reserve_Before_200hours() <= 600) {
            list.get(3).setVisible(true);
        }
        if (Integer.parseInt(label1.getText()) <= 5) {
            list.get(0).setVisible(true);
        }
        if (Integer.parseInt(label2.getText()) <= 5) {
            list.get(1).setVisible(true);
        }
    }
}



