package sample.update;

import javafx.scene.text.Text;
import sample.data.Aircraft;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.MainBreak;

public class UpdateNotification {
    public static void updateNotificationEngine(Engine engine, Aircraft aircraft,
                                                Text text1, Text text2) {
        if (engine.getSerialNumberEngine().equals(aircraft.getLeftEngine().getSerialNumberEngine())) {
            if (engine.getIsNeedAttention() == false) {
                text1.setVisible(false);
            }
        } else if (engine.getSerialNumberEngine().equals(aircraft.getRightEngine().getSerialNumberEngine())) {
            if (engine.getIsNeedAttention() == false) {
                text2.setVisible(false);
            }
        }
    }
    public static void updateNotificationKsa(Ksa ksa, Aircraft aircraft, Text text) {
        if (ksa.getSerialNumberKsa().equals(aircraft.getKsa().getSerialNumberKsa())) {
            if (ksa.getIsNeedAttention() == false) {
                text.setVisible(false);
            }
        }
    }
    public static void updateNotificationPlaner(Planer planer, Aircraft aircraft, Text text) {
        if (planer.getSideNumber().equals(aircraft.getPlaner().getSideNumber())) {
            if (planer.getIsNeedAttention() == false) {
                text.setVisible(false);
            } else {
                text.setVisible(true);
            }
        }
    }
    public static void updateNotificationMainBreak(MainBreak mainbreak, Aircraft aircraft, Text text1, Text text2) {
        if (mainbreak.getSerialNumber().equals(aircraft.getLeftMainBrake().getSerialNumber())) {
            if (mainbreak.getIsNeedAttention() == false) {
                text1.setVisible(false);
            }
        } else if (mainbreak.getSerialNumber().equals(aircraft.getRightMainBrake().getSerialNumber())) {
            if (mainbreak.getIsNeedAttention() == false) {
                text2.setVisible(false);
            }
        }
    }
}
