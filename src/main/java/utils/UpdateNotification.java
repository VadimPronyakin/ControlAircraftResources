package utils;

import data.Aircraft;
import data.SaveData;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.MainBreak;
import io.writer.FilesWriter;
import javafx.scene.text.Text;

import static service.BooleanValueSetter.setBooleanValueAircraft;

public class UpdateNotification {

    /**
     * Метод обновляет видимость надписей - сигнализаторов,которые находятся в карточке самолета в зависимости от того,
     * как изменяется значение boolean isNeedAttention после выполнения работ на двигателях
     */
    public static void updateNotificationEngine(Engine engine, Aircraft aircraft,
                                                Text alarmLeftEngine, Text alarmRightEngine) {
        if (engine.getSerialNumber().equals(aircraft.getLeftEngine().getSerialNumber())) {
            if (!engine.getIsNeedAttention()) {
                alarmLeftEngine.setVisible(false);
            }
        } else if (engine.getSerialNumber().equals(aircraft.getRightEngine().getSerialNumber())) {
            if (!engine.getIsNeedAttention()) {
                alarmRightEngine.setVisible(false);
            }
        }
    }

    /**
     * Метод обновляет видимость надписи - сигнализатора,которая находится в карточке самолета в зависимости от того,
     * как изменяется значение boolean isNeedAttention после выполнения работ на КСА
     */
    public static void updateNotificationKsa(Ksa ksa, Aircraft aircraft, Text alarmKsa) {
        if (ksa.getSerialNumber().equals(aircraft.getKsa().getSerialNumber())) {
            if (!ksa.getIsNeedAttention()) {
                alarmKsa.setVisible(false);
            }
        }
    }

    /**
     * Метод обновляет видимость надписи - сигнализатора,которая находится в карточке самолета в зависимости от того,
     * как изменяется значение boolean isNeedAttention после выполнения работ на планере
     */
    public static void updateNotificationPlaner(Planer planer, Aircraft aircraft, Text alarmPlaner) {
        if (planer.getSerialNumber().equals(aircraft.getPlaner().getSerialNumber())) {
            alarmPlaner.setVisible(planer.getIsNeedAttention());
        }
    }

    /**
     * Метод обновляет видимость надписей - сигнализаторов,которые находятся в карточке самолета в зависимости от того,
     * как изменяется значение boolean isNeedAttention после выполнения работ на основных тормозах
     */
    public static void updateNotificationMainBreak(MainBreak mainbreak, Aircraft aircraft,
                                                   Text alarm_Left_Main_Break, Text alarm_Right_Main_Break) {
        if (mainbreak.getSerialNumber().equals(aircraft.getLeftMainBrake().getSerialNumber())) {
            if (!mainbreak.getIsNeedAttention()) {
                alarm_Left_Main_Break.setVisible(false);
            }
        } else if (mainbreak.getSerialNumber().equals(aircraft.getRightMainBrake().getSerialNumber())) {
            if (!mainbreak.getIsNeedAttention()) {
                alarm_Right_Main_Break.setVisible(false);
            }
        }
    }

    /**
     * метод обновялет индикацию самолета в списке самолетов
     */
    public static void updateNotificationOnAircraft() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            aircraft.setIsNeedAttention(setBooleanValueAircraft(aircraft));
        }
        FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
    }
}
