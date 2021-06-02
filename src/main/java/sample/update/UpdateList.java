package sample.update;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.write.WriteFile;

import java.util.List;

import static sample.calculating.CalculatingDateResources.calculateDateInDays;
import static sample.calculating.CalculatingDateResources.calculateDateInMonth;
import static sample.setBoolean.SetBooleanValue.setBooleanValueAircraft;

public class UpdateList {
    /**
     * Метод обновляет содержимое TableView
     */
    public static <T> void updateList(List<T> list, TableView<T> tableView, String textConstants) {
        if (list.size() == 0) {
            tableView.setPlaceholder(new Label(textConstants));
        }
        tableView.getItems().clear();
        for (int i = 0; i < list.size(); i++) {
            tableView.getItems().addAll(list.get(i));
        }
    }

    /**
     * метод обновялет индикацию самолета в списке самолетов
     */
    public static void updateNotificationOnAircraft() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            aircraft.setIsNeedAttention(setBooleanValueAircraft(aircraft));
        }
        WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
    }

    /**
     * Метод обновялет остаток до работ по планеру, которые считаются в днях,
     * каждый день считая разницу между датой из файла и актуальной на сегодняшний день
     */
    public static void updateReserveDays() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            aircraft.getPlaner().setDays_Reserve_Before_30DaysParking(calculateDateInDays(aircraft.getPlaner().getLast_Flight_Date(),
                    aircraft.getPlaner().getDate_Work_After_30days_Parking()));
            aircraft.getPlaner().setDays_Reserve_Before_6months_Operating(calculateDateInMonth(aircraft.getPlaner().getDate_Work_After_6months_Operation()));
        }
        WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
    }

}
