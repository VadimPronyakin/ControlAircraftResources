package sample.notification;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import sample.data.Aircraft;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;

import java.util.List;

import static sample.calculating.CalculatingDateResources.calculateDateInDays;
import static sample.calculating.CalculatingDateResources.calculateDateInMonth;

public class NotificationAircraft {
    public static void notifiesAircraft(Aircraft aircraft, Text[] texts) {
        if (aircraft.getLeftEngine() == null) {
            System.out.println("Нет левого двигателя на самолете");
        } else if (aircraft.getLeftEngine().getResourceReserveBefore_10hours() <= 270
                || aircraft.getLeftEngine().getResourceReserveBefore_25hours() <= 300
                || aircraft.getLeftEngine().getResourceReserveBefore_50hours() <= 300
                || aircraft.getLeftEngine().getResourceReserveBefore_100hours() <= 600
                || aircraft.getLeftEngine().getResourceReserveBefore_150hours() <= 600
                || aircraft.getLeftEngine().getResourceReserveBefore_278bulletin() <= 300
                || aircraft.getLeftEngine().getOilChange() <= 600) {
            texts[0].setVisible(true);
        }
        if (aircraft.getRightEngine() == null) {
            System.out.println("Нет правого двигателя на самолете");
        } else if (aircraft.getRightEngine().getResourceReserveBefore_10hours() <= 270
                || aircraft.getRightEngine().getResourceReserveBefore_25hours() <= 300
                || aircraft.getRightEngine().getResourceReserveBefore_50hours() <= 300
                || aircraft.getRightEngine().getResourceReserveBefore_100hours() <= 600
                || aircraft.getRightEngine().getResourceReserveBefore_150hours() <= 600
                || aircraft.getRightEngine().getResourceReserveBefore_278bulletin() <= 300
                || aircraft.getRightEngine().getOilChange() <= 600) {
            texts[1].setVisible(true);
        }
        if (aircraft.getKsa() == null) {
            System.out.println("Нет КСА на самолете");
        } else if (aircraft.getKsa().getResource_Reserve_Before_25hours() <= 300
                || aircraft.getKsa().getOilChange() <= 300) {
            texts[2].setVisible(true);
        }
        if (aircraft.getPlaner().getResource_Reserve_Before_100hours() <= 600
                || aircraft.getPlaner().getResource_Reserve_Before_200hours() <= 600
                || calculateDateInMonth(aircraft.getPlaner().getDate_Work_After_6months_Operation().getTime()) <= 432_000_000
                || calculateDateInDays(aircraft.getPlaner().getLast_Flight_Date().getTime(),
                aircraft.getPlaner().getDate_Work_After_30days_Parking().getTime()) <= 432_000_000) {
            texts[3].setVisible(true);
        }
        if (aircraft.getLeftMainWheel().getResource_Reserve_Replacement_Wheel() <= 15) {
            texts[4].setVisible(true);
        }
        if (aircraft.getRightMainWheel().getResource_Reserve_Replacement_Wheel() <= 15) {
            texts[5].setVisible(true);
        }
        if (aircraft.getLeftFrontWheel().getResource_Reserve_Replacement_Wheel() <= 15) {
            texts[6].setVisible(true);
        }
        if (aircraft.getRightFrontWheel().getResource_Reserve_Replacement_Wheel() <= 15) {
            texts[7].setVisible(true);
        }

        if (aircraft.getLeftMainBrake().getResource_Reserve_Replacement_Break() <= 15
                || aircraft.getLeftMainBrake().getResource_Reserve_Replacement_RotatingDisks() <= 15
                || aircraft.getLeftMainBrake().getResource_Reserve_Replacement_NonRotatingDisks() <= 15
                || aircraft.getLeftMainBrake().getResource_Reserve_Replacement_PressureDisk() <= 15
                && aircraft.getLeftMainBrake().getResource_Reserve_Replacement_PressureDisk() != 0
                || aircraft.getLeftMainBrake().getResource_Reserve_Replacement_ReferenceDisk() <= 15
                && aircraft.getLeftMainBrake().getResource_Reserve_Replacement_ReferenceDisk() != 0) {
            texts[8].setVisible(true);
        }
        if (aircraft.getRightMainBrake().getResource_Reserve_Replacement_Break() <= 15
                || aircraft.getRightMainBrake().getResource_Reserve_Replacement_NonRotatingDisks() <= 15
                || aircraft.getRightMainBrake().getResource_Reserve_Replacement_RotatingDisks() <= 15
                || aircraft.getRightMainBrake().getResource_Reserve_Replacement_ReferenceDisk() <= 15
                && aircraft.getRightMainBrake().getResource_Reserve_Replacement_ReferenceDisk() != 0
                || aircraft.getRightMainBrake().getResource_Reserve_Replacement_PressureDisk() <= 15
                && aircraft.getRightMainBrake().getResource_Reserve_Replacement_PressureDisk() != 0) {
            texts[9].setVisible(true);
        }
        if (aircraft.getLeftFrontBrake().getResource_Reserve_Before_First_Repair() <= 15
                || aircraft.getLeftFrontBrake().getResource_Reserve_Before_Replacement() <= 15) {
            texts[10].setVisible(true);
        }
        if (aircraft.getRightFrontBrake().getResource_Reserve_Before_First_Repair() <= 15
                || aircraft.getRightFrontBrake().getResource_Reserve_Before_Replacement() <= 15) {
            texts[11].setVisible(true);
        }
        if (aircraft.getLeftMainCylinder().getResource_Reserve_Before_First_Repair() <= 15
                || aircraft.getLeftMainCylinder().getResource_Reserve_Before_Second_Repair() <= 15
                || aircraft.getLeftMainCylinder().getResource_Reserve_Before_Replacement() <= 15) {
            texts[12].setVisible(true);
        }
        if (aircraft.getRightMainCylinder().getResource_Reserve_Before_First_Repair() <= 15
                || aircraft.getRightMainCylinder().getResource_Reserve_Before_Second_Repair() <= 15
                || aircraft.getRightMainCylinder().getResource_Reserve_Before_Replacement() <= 15) {
            texts[13].setVisible(true);
        }
        if (aircraft.getFrontCylinder().getResource_Reserve_Before_First_Repair() <= 15
                || aircraft.getFrontCylinder().getResource_Reserve_Before_Second_Repair() <= 15
                || aircraft.getFrontCylinder().getResource_Reserve_Before_Replacement() <= 15) {
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

//    protected void updateItem(TableView<Aircraft> table) {
//                    for (Aircraft aircraft : table.getItems()) {
//                        if (aircraft.getLeftEngine().getResourceReserveBefore_10hours() <= 270
//                                || aircraft.getLeftEngine().getResourceReserveBefore_25hours() <= 300
//                                || aircraft.getLeftEngine().getResourceReserveBefore_50hours() <= 300
//                                || aircraft.getLeftEngine().getResourceReserveBefore_100hours() <= 600
//                                || aircraft.getLeftEngine().getResourceReserveBefore_150hours() <= 600
//                                || aircraft.getLeftEngine().getResourceReserveBefore_278bulletin() <= 300
//                                || aircraft.getLeftEngine().getOilChange() <= 600
//                                ||  aircraft.getRightEngine().getResourceReserveBefore_10hours() <= 270
//                                || aircraft.getRightEngine().getResourceReserveBefore_25hours() <= 300
//                                || aircraft.getRightEngine().getResourceReserveBefore_50hours() <= 300
//                                || aircraft.getRightEngine().getResourceReserveBefore_100hours() <= 600
//                                || aircraft.getRightEngine().getResourceReserveBefore_150hours() <= 600
//                                || aircraft.getRightEngine().getResourceReserveBefore_278bulletin() <= 300
//                                || aircraft.getRightEngine().getOilChange() <= 600
//                                || aircraft.getKsa().getResource_Reserve_Before_25hours() <= 300
//                                || aircraft.getKsa().getOilChange() <= 300
//                                || aircraft.getPlaner().getResource_Reserve_Before_100hours() <= 600
//                                || aircraft.getPlaner().getResource_Reserve_Before_200hours() <= 600
//                                || calculateDateInMonth(aircraft.getPlaner().getDate_Work_After_6months_Operation().getTime()) <= 432_000_000
//                                || calculateDateInDays(aircraft.getPlaner().getLast_Flight_Date().getTime(),
//                                aircraft.getPlaner().getDate_Work_After_30days_Parking().getTime()) <= 432_000_000
//                                || aircraft.getLeftMainWheel().getResource_Reserve_Replacement_Wheel() <= 15
//                                || aircraft.getRightMainWheel().getResource_Reserve_Replacement_Wheel() <= 15) {
//                            table.setTextFill(Color.web("#FF76a3"));
//
//                        }
//                    }
//                }
//
//        });
//
//    }
}

