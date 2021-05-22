package sample.notification;

import javafx.scene.text.Text;
import sample.data.Aircraft;

import java.util.List;

public class NotificationAircraft {
    public static void notifies(Aircraft aircraft, List<Text> list) {
        if (aircraft.getLeftEngine().getResourceReserveBefore_10hours() < 270
                || aircraft.getLeftEngine().getResourceReserveBefore_25hours() < 300
                || aircraft.getLeftEngine().getResourceReserveBefore_50hours() < 300
                || aircraft.getLeftEngine().getResourceReserveBefore_100hours() < 600
                || aircraft.getLeftEngine().getResourceReserveBefore_150hours() < 600
                || aircraft.getLeftEngine().getResourceReserveBefore_278bulletin() < 300
                || aircraft.getLeftEngine().getOilChange() < 600) {
            list.get(0).setVisible(true);
        }
        if (aircraft.getRightEngine().getResourceReserveBefore_10hours() < 270
                || aircraft.getRightEngine().getResourceReserveBefore_25hours() < 300
                || aircraft.getRightEngine().getResourceReserveBefore_50hours() < 300
                || aircraft.getRightEngine().getResourceReserveBefore_100hours() < 600
                || aircraft.getRightEngine().getResourceReserveBefore_150hours() < 600
                || aircraft.getRightEngine().getResourceReserveBefore_278bulletin() < 300
                || aircraft.getRightEngine().getOilChange() < 600) {
            list.get(1).setVisible(true);
        }
        if (aircraft.getKsa().getResource_Reserve_Before_25hours() < 300
                || aircraft.getKsa().getOilChange() < 300) {
            list.get(2).setVisible(true);
        }
        if (aircraft.getPlaner().getResource_Reserve_Before_100hours() < 600
                || aircraft.getPlaner().getResource_Reserve_Before_200hours() < 600) {
            list.get(3).setVisible(true);
        }
        if (aircraft.getLeftMainWheel().getResource_Reserve_Replacement_Wheel() < 15) {
            list.get(4).setVisible(true);
        }
        if (aircraft.getRightMainWheel().getResource_Reserve_Replacement_Wheel() < 15) {
            list.get(5).setVisible(true);
        }
        if (aircraft.getLeftFrontWheel().getResource_Reserve_Replacement_Wheel() < 15) {
            list.get(6).setVisible(true);
        }
        if (aircraft.getRightFrontWheel().getResource_Reserve_Replacement_Wheel() < 15) {
            list.get(7).setVisible(true);
        }
        if (aircraft.getLeftMainBrake().getResource_Reserve_Replacement_Break() < 15
                || aircraft.getLeftMainBrake().getResource_Reserve_Replacement_RotatingDisks() < 15
                || aircraft.getLeftMainBrake().getResource_Reserve_Replacement_NonRotatingDisks() < 15
                || aircraft.getLeftMainBrake().getResource_Reserve_Replacement_PressureDisk() < 15
                || aircraft.getLeftMainBrake().getResource_Reserve_Replacement_ReferenceDisk() < 15) {
            list.get(8).setVisible(true);
        }
        if (aircraft.getRightMainBrake().getResource_Reserve_Replacement_Break() < 15
                || aircraft.getRightMainBrake().getResource_Reserve_Replacement_NonRotatingDisks() < 15
                || aircraft.getRightMainBrake().getResource_Reserve_Replacement_RotatingDisks() < 15
                || aircraft.getRightMainBrake().getResource_Reserve_Replacement_ReferenceDisk() < 15
                || aircraft.getRightMainBrake().getResource_Reserve_Replacement_PressureDisk() < 15) {
            list.get(9).setVisible(true);
        }
        if (aircraft.getLeftFrontBrake().getResource_Reserve_Before_First_Repair() < 15
                || aircraft.getLeftFrontBrake().getResource_Reserve_Before_Replacement() < 15) {
            list.get(10).setVisible(true);
        }
        if (aircraft.getRightFrontBrake().getResource_Reserve_Before_First_Repair() < 15
                || aircraft.getRightFrontBrake().getResource_Reserve_Before_Replacement() < 15) {
            list.get(11).setVisible(true);
        }
        if (aircraft.getLeftMainCylinder().getResource_Reserve_Before_First_Repair() < 15
                || aircraft.getLeftMainCylinder().getResource_Reserve_Before_Second_Repair() < 15
                || aircraft.getLeftMainCylinder().getResource_Reserve_Before_Replacement() < 15) {
            list.get(12).setVisible(true);
        }
        if (aircraft.getRightMainCylinder().getResource_Reserve_Before_First_Repair() < 15
                || aircraft.getRightMainCylinder().getResource_Reserve_Before_Second_Repair() < 15
                || aircraft.getRightMainCylinder().getResource_Reserve_Before_Replacement() < 15) {
            list.get(13).setVisible(true);
        }
        if (aircraft.getFrontCylinder().getResource_Reserve_Before_First_Repair() < 15
                || aircraft.getFrontCylinder().getResource_Reserve_Before_Second_Repair() < 15
                || aircraft.getFrontCylinder().getResource_Reserve_Before_Replacement() < 15) {
            list.get(14).setVisible(true);
        }
    }
}
