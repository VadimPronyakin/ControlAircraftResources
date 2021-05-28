package sample.works;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.MainBreak;
import sample.data.enums.TypesOfWorks;

import java.time.ZoneId;
import java.util.Date;

public class MakeWorks {
    public static void doWorksEngine(Engine engine, ComboBox<TypesOfWorks> list) {

        switch (list.getSelectionModel().getSelectedItem()) {
            case WORKS_AFTER_10_HOURS:
                engine.setResourceReserveBefore_10hours(list.getSelectionModel().getSelectedItem().getResource());
//                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case WORKS_AFTER_25_HOURS:
                engine.setResourceReserveBefore_25hours(list.getSelectionModel().getSelectedItem().getResource());
//                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case WORKS_AFTER_50_HOURS:
                engine.setResourceReserveBefore_50hours(list.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
//                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case WORKS_AFTER_100_HOURS:
                engine.setResourceReserveBefore_100hours(list.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_50hours(list.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
//                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case WORKS_AFTER_150_HOURS:
                engine.setResourceReserveBefore_150hours(list.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_50hours(list.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
//                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case WORKS_AFTER_278_BULLETIN:
                engine.setResourceReserveBefore_278bulletin(list.getSelectionModel().getSelectedItem().getResource());
//                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case OIL_CHANGE_OPERATIONS:
                engine.setOilChange(list.getSelectionModel().getSelectedItem().getResource());
//                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
        }
    }

   public static void doWorkKsa(Ksa ksa, ComboBox<TypesOfWorks> list) {
        try {
            switch (list.getSelectionModel().getSelectedItem()) {

                case WORKS_AFTER_25_HOURS:
                    ksa.setResource_Reserve_Before_25hours(list.getSelectionModel().getSelectedItem().getResource());
//                    WriteFile.serialization(SaveData.ksaList, Ksa.class);
                    break;
                case OIL_CHANGE_OPERATIONS:
                    ksa.setOilChange(list.getSelectionModel().getSelectedItem().getResource());
//                    WriteFile.serialization(SaveData.ksaList, Ksa.class);
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
   public static void doWorksMainBreak(MainBreak mainBreak, ComboBox<TypesOfWorks> list) {
        try {
            switch (list.getSelectionModel().getSelectedItem()) {

                case REPLACEMENT_ROTATING_DISKS:
                    mainBreak.setResource_Reserve_Replacement_RotatingDisks(list
                            .getSelectionModel()
                            .getSelectedItem()
                            .getResource());
//                    WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);
                    break;
                case REPLACEMENT_NON_ROTATING_DISKS:
                    mainBreak.setResource_Reserve_Replacement_NonRotatingDisks(list
                            .getSelectionModel()
                            .getSelectedItem()
                            .getResource());
//                    WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);
                    break;
                case REPLACEMENT_PRESSURE_DISKS:
                    mainBreak.setResource_Reserve_Replacement_PressureDisk(list
                            .getSelectionModel()
                            .getSelectedItem()
                            .getResource());
                    break;
                case REPLACEMENT_REFERENCE_DISKS:
                    mainBreak.setResource_Reserve_Replacement_ReferenceDisk(list.getSelectionModel()
                            .getSelectedItem()
                            .getResource());
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public static void doWorksPlaner(Planer planer, ChoiceBox<TypesOfWorks> list, DatePicker date) {

        switch (list.getSelectionModel().getSelectedItem()) {
            case WORKS_AFTER_100_HOURS_PLANER:
                planer.setResource_Reserve_Before_100hours(list.getSelectionModel().getSelectedItem().getResource());
//                WriteFile.serialization(SaveData.planersList, Planer.class);
                break;
            case WORKS_AFTER_200_HOURS_PLANER:
                planer.setResource_Reserve_Before_200hours(list.getSelectionModel().getSelectedItem().getResource());
                planer.setResource_Reserve_Before_100hours(TypesOfWorks.WORKS_AFTER_100_HOURS_PLANER.getResource());
//                WriteFile.serialization(SaveData.planersList, Planer.class);
                break;
            case WORKS_AFTER_30_DAYS_PARKING:
                planer.setDate_Work_After_30days_Parking (Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//                WriteFile.serialization(SaveData.planersList, Planer.class);
                break;
            case WORKS_AFTER_6_MONTH_OPERATION:
                planer.setDate_Work_After_6months_Operation(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//                WriteFile.serialization(SaveData.planersList, Planer.class);
                break;
        }
    }
}
