package sample.builder;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.data.Aircraft;
import sample.data.Engineer;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;
import sample.write.WriteFile;

import java.time.ZoneId;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Builder {
    public static void createAircraft(TextField text, ChoiceBox[] box) {
        Aircraft aircraft = Aircraft.builder()
                .aircraftNumber("б/н " + text.getText())
                .iak((Engineer) box[0].getSelectionModel().getSelectedItem())
                .leftEngine((Engine) box[1].getSelectionModel().getSelectedItem())
                .rightEngine((Engine) box[2].getSelectionModel().getSelectedItem())
                .ksa((Ksa) box[3].getSelectionModel().getSelectedItem())
                .planer((Planer) box[4].getSelectionModel().getSelectedItem())
                .leftMainBrake((MainBreak) box[5].getSelectionModel().getSelectedItem())
                .rightMainBrake((MainBreak) box[6].getSelectionModel().getSelectedItem())
                .leftMainWheel((MainWheel) box[7].getSelectionModel().getSelectedItem())
                .rightMainWheel((MainWheel) box[8].getSelectionModel().getSelectedItem())
                .leftFrontBrake((FrontBreak) box[9].getSelectionModel().getSelectedItem())
                .rightFrontBrake((FrontBreak) box[10].getSelectionModel().getSelectedItem())
                .leftFrontWheel((FrontWheel) box[11].getSelectionModel().getSelectedItem())
                .rightFrontWheel((FrontWheel) box[12].getSelectionModel().getSelectedItem())
                .leftMainCylinder((CylinderOfRetractionExtension) box[13].getSelectionModel().getSelectedItem())
                .rightMainCylinder((CylinderOfRetractionExtension) box[14].getSelectionModel().getSelectedItem())
                .frontCylinder((CylinderOfRetractionExtension) box[15].getSelectionModel().getSelectedItem())
                .build();
        aircraft.setFullNameEngineer(aircraft.getIak().getFullName());
        SaveData.aircraftList.add(aircraft);
        WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
    }

    public static void createEngine(TextField[] fields) {
        Engine engine = Engine.builder()
                .totalOperatingTime((parseInt(fields[0].getText()) * 60) +
                        parseInt(fields[1].getText()))
                .totalStartingEngineCount(parseInt(fields[2].getText()))
                .resourceReserveBefore_10hours((parseInt(fields[3].getText()) * 60) +
                        parseInt(fields[4].getText()))
                .resourceReserveBefore_25hours((parseInt(fields[5].getText()) * 60) +
                        parseInt(fields[6].getText()))
                .resourceReserveBefore_50hours((parseInt(fields[7].getText()) * 60) +
                        parseInt(fields[8].getText()))
                .resourceReserveBefore_100hours((parseInt(fields[9].getText()) * 60) +
                        parseInt(fields[10].getText()))
                .resourceReserveBefore_150hours((parseInt(fields[11].getText()) * 60) +
                        parseInt(fields[12].getText()))
                .resourceReserveBefore_278bulletin((parseInt(fields[13].getText()) * 60) +
                        parseInt(fields[14].getText()))
                .oilChange((parseInt(fields[15].getText()) * 60) +
                        parseInt(fields[16].getText()))
                .serialNumberEngine(fields[17].getText())
                .build();
        SaveData.enginesList.add(engine);
        WriteFile.serialization(SaveData.enginesList, Engine.class);
    }

    public static void createFrontBreak(TextField[] fields) {
        FrontBreak frontBreak = FrontBreak.builder()
                .totalLandings(parseInt(fields[0].getText()))
                .resource_Reserve_Before_First_Repair(parseInt(fields[1].getText()))
                .resource_Reserve_Before_Replacement(parseInt(fields[2].getText()))
                .serialNumber(fields[3].getText())
                .build();
        SaveData.frontBreaksList.add(frontBreak);
        WriteFile.serialization(SaveData.frontBreaksList, FrontBreak.class);
    }

    public static void createFrontWheel(TextField field1, TextField field2, TextField field3) {
        FrontWheel frontWheel = FrontWheel.builder()
                .totalLandings(parseInt(field1.getText()))
                .resource_Reserve_Replacement_Wheel(parseInt(field2.getText()))
                .serialNumber(field3.getText())
                .build();
        SaveData.frontWheelsList.add(frontWheel);
        WriteFile.serialization(SaveData.frontWheelsList, FrontWheel.class);
    }

    public static void createKsa(TextField[] fields) {
        Ksa ksa = Ksa.builder()
                .total_Operating_Time((parseInt(fields[0].getText()) * 60) +
                        parseInt(fields[1].getText()))
                .starting_Left_Count(parseInt(fields[2].getText()))
                .starting_Right_Count(parseInt(fields[3].getText()))
                .total_Starting_Ksa_Count(parseInt(fields[4].getText()))
                .resource_Reserve_Before_25hours((parseInt(fields[5].getText()) * 60) +
                        parseInt(fields[6].getText()))
                .oilChange((parseInt(fields[7].getText()) * 60) +
                        parseInt(fields[8].getText()))
                .serialNumberKsa(fields[9].getText())
                .build();
        SaveData.ksaList.add(ksa);
        WriteFile.serialization(SaveData.ksaList, Ksa.class);
    }

    public static void createMainBreak(TextField[] fields) {
        MainBreak mainBreak = MainBreak.builder()
                .totalLandings(parseInt(fields[0].getText()))
                .resource_Reserve_Replacement_Break(parseInt(fields[1].getText()))
                .resource_Reserve_Replacement_RotatingDisks(parseInt(fields[2].getText()))
                .resource_Reserve_Replacement_NonRotatingDisks(parseInt(fields[3].getText()))
                .resource_Reserve_Replacement_PressureDisk(parseInt(fields[4].getText()))
                .resource_Reserve_Replacement_ReferenceDisk(parseInt(fields[5].getText()))
                .serialNumber(fields[6].getText())
                .build();
        SaveData.mainBreaksList.add(mainBreak);
        WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);
    }

    public static void createMainWheel(TextField field1, TextField field2, TextField field3) {
        MainWheel mainWheel = MainWheel.builder()
                .totalLandings(parseInt(field1.getText()))
                .resource_Reserve_Replacement_Wheel(parseInt(field2.getText()))
                .serialNumber(field3.getText())
                .build();
        SaveData.mainWheelsList.add(mainWheel);
        WriteFile.serialization(SaveData.mainWheelsList, MainWheel.class);
    }

    public static void createPlaner(TextField[] fields, DatePicker date1, DatePicker date2, DatePicker date3) {
        Planer planer = Planer.builder()
                .sideNumber(fields[0].getText())
                .total_Landing_Count(parseInt(fields[1].getText()))
                .last_Flight_Date(Date.from(date1.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .date_Work_After_6months_Operation(Date.from(date2.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .date_Work_After_30days_Parking(Date.from(date3.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .total_Operating_Time((parseInt(fields[2].getText()) * 60) +
                        parseInt(fields[3].getText()))
                .resource_Reserve_Before_100hours((parseInt(fields[4].getText()) * 60) +
                        parseInt(fields[5].getText()))
                .resource_Reserve_Before_200hours((parseInt(fields[6].getText()) * 60) +
                        parseInt(fields[7].getText()))
                .build();
        SaveData.planersList.add(planer);
        WriteFile.serialization(SaveData.planersList, Planer.class);
    }

    public static void createCylinder(TextField[] fields) {
        CylinderOfRetractionExtension cylinder = CylinderOfRetractionExtension.builder()
                .totalLandings(parseInt(fields[0].getText()))
                .resource_Reserve_Before_First_Repair(parseInt(fields[1].getText()))
                .resource_Reserve_Before_Second_Repair(parseInt(fields[2].getText()))
                .resource_Reserve_Before_Replacement(parseInt(fields[3].getText()))
                .serialNumber(fields[4].getText())
                .build();
        SaveData.cylindersList.add(cylinder);
        WriteFile.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
    }
}
