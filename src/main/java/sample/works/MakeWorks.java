package sample.works;

import javafx.scene.control.ComboBox;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.enums.TypesOfWorks;
import sample.write.WriteFile;

public class MakeWorks {
    public void doWorksEngine(Engine engine, ComboBox<TypesOfWorks> list) {

        switch (list.getSelectionModel().getSelectedItem()) {
            case WORKS_AFTER_10_HOURS:
                engine.setResourceReserveBefore_10hours(list.getSelectionModel().getSelectedItem().getResource());
                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case WORKS_AFTER_25_HOURS:
                engine.setResourceReserveBefore_25hours(list.getSelectionModel().getSelectedItem().getResource());
                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case WORKS_AFTER_50_HOURS:
                engine.setResourceReserveBefore_50hours(list.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case WORKS_AFTER_100_HOURS:
                engine.setResourceReserveBefore_100hours(list.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_50hours(list.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case WORKS_AFTER_150_HOURS:
                engine.setResourceReserveBefore_150hours(list.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_50hours(list.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case WORKS_AFTER_278_BULLETIN:
                engine.setResourceReserveBefore_278bulletin(list.getSelectionModel().getSelectedItem().getResource());
                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
            case OIL_CHANGE_OPERATIONS:
                engine.setOilChange(list.getSelectionModel().getSelectedItem().getResource());
                WriteFile.serialization(SaveData.enginesList, Engine.class);
                break;
        }
    }

   public void doWorkKsa(Ksa ksa, ComboBox<TypesOfWorks> list) {
        try {
            switch (list.getSelectionModel().getSelectedItem()) {

                case WORKS_AFTER_25_HOURS:
                    ksa.setResource_Reserve_Before_25hours(list.getSelectionModel().getSelectedItem().getResource());
                    WriteFile.serialization(SaveData.ksaList, Ksa.class);
                    break;
                case OIL_CHANGE_OPERATIONS:
                    ksa.setOilChange(list.getSelectionModel().getSelectedItem().getResource());
                    WriteFile.serialization(SaveData.ksaList, Ksa.class);
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
