package sample.update;

import javafx.scene.control.TextField;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;
import sample.write.WriteFile;

import static java.lang.Integer.parseInt;
import static sample.setBoolean.SetBooleanValue.*;

public class UpdateAircraftComponents {
    public static void updatingComponents(Aircraft aircraft, TextField flightHours,
                                      TextField flightMinutes, TextField flightAndEarthHours,
                                      TextField flightAndEarthMinutes, TextField totalLandings) {
        for (Engine engine : SaveData.enginesList) {
            if (engine.getSerialNumberEngine().equals(aircraft.getLeftEngine().getSerialNumberEngine())
                || engine.getSerialNumberEngine().equals(aircraft.getRightEngine().getSerialNumberEngine())) {
                engine.setTotalOperatingTime(engine.getTotalOperatingTime() +
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                //ПОПРАВИТЬ ПОСЛЕ УТОЧНЕНИЯ
                engine.setTotalStartingEngineCount(engine.getTotalStartingEngineCount() +
                        parseInt(totalLandings.getText()));
                engine.setResourceReserveBefore_10hours(engine.getResourceReserveBefore_10hours() -
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                engine.setResourceReserveBefore_25hours(engine.getResourceReserveBefore_25hours() -
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                engine.setResourceReserveBefore_50hours(engine.getResourceReserveBefore_50hours() -
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                engine.setResourceReserveBefore_100hours(engine.getResourceReserveBefore_100hours() -
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                engine.setResourceReserveBefore_150hours(engine.getResourceReserveBefore_150hours() -
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                engine.setResourceReserveBefore_278bulletin(engine.getResourceReserveBefore_278bulletin() -
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                engine.setOilChange(engine.getOilChange() -
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                engine.setIsNeedAttention(setBooleanValueEngine(engine));
            }
        }
        WriteFile.serialization(SaveData.enginesList, Engine.class);

        for (Planer planer : SaveData.planersList) {
            if (planer.getSideNumber().equals(aircraft.getPlaner().getSideNumber())) {
                planer.setTotal_Operating_Time(planer.getTotal_Operating_Time() +
                        (parseInt(flightHours.getText()) * 60 + parseInt(flightMinutes.getText())));
                planer.setTotal_Landing_Count(planer.getTotal_Landing_Count() +
                        parseInt(totalLandings.getText()));
                planer.setResource_Reserve_Before_100hours(planer.getResource_Reserve_Before_100hours() -
                        (parseInt(flightHours.getText()) * 60 + parseInt(flightMinutes.getText())));
                planer.setResource_Reserve_Before_200hours(planer.getResource_Reserve_Before_200hours() -
                        (parseInt(flightHours.getText()) * 60 + parseInt(flightMinutes.getText())));
                planer.setIsNeedAttention(setBooleanValuePlaner(planer));
            }
        }
        WriteFile.serialization(SaveData.planersList, Planer.class);

        for (Ksa ksa : SaveData.ksaList) {
            if (ksa.getSerialNumberKsa().equals(aircraft.getKsa().getSerialNumberKsa())) {
                ksa.setTotal_Operating_Time(ksa.getTotal_Operating_Time() +
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                //ПОПРАВИТЬ ПОСЛЕ УТОЧНЕНИЯ
                ksa.setTotal_Starting_Ksa_Count(ksa.getTotal_Operating_Time() +
                        parseInt(totalLandings.getText()));
                ksa.setResource_Reserve_Before_25hours(ksa.getResource_Reserve_Before_25hours() -
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                ksa.setOilChange(ksa.getOilChange() -
                        (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
                ksa.setIsNeedAttention(setBooleanValueKsa(ksa));
            }
        }
        WriteFile.serialization(SaveData.ksaList, Ksa.class);

        for (MainBreak mainBreak : SaveData.mainBreaksList) {
            if (mainBreak.getSerialNumber().equals(aircraft.getLeftMainBrake().getSerialNumber())
                || mainBreak.getSerialNumber().equals(aircraft.getRightMainBrake().getSerialNumber())) {
                mainBreak.setTotalLandings(mainBreak.getTotalLandings() +
                        parseInt(totalLandings.getText()));
                mainBreak.setResource_Reserve_Replacement_Break(mainBreak.getResource_Reserve_Replacement_Break() -
                        parseInt(totalLandings.getText()));
                mainBreak.setResource_Reserve_Replacement_RotatingDisks(mainBreak.getResource_Reserve_Replacement_RotatingDisks() -
                        parseInt(totalLandings.getText()));
                mainBreak.setResource_Reserve_Replacement_NonRotatingDisks(mainBreak.getResource_Reserve_Replacement_NonRotatingDisks() -
                        parseInt(totalLandings.getText()));
                mainBreak.setResource_Reserve_Replacement_PressureDisk(mainBreak.getResource_Reserve_Replacement_PressureDisk() -
                        parseInt(totalLandings.getText()));
                mainBreak.setResource_Reserve_Replacement_ReferenceDisk(mainBreak.getResource_Reserve_Replacement_ReferenceDisk() -
                        parseInt(totalLandings.getText()));
                mainBreak.setIsNeedAttention(setBooleanValueMainBreak(mainBreak));
            }
        }
        WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);

        for (MainWheel mainWheel : SaveData.mainWheelsList) {
            if (mainWheel.getSerialNumber().equals(aircraft.getLeftMainWheel().getSerialNumber())
                || mainWheel.getSerialNumber().equals(aircraft.getRightMainWheel().getSerialNumber())) {
                mainWheel.setTotalLandings(mainWheel.getTotalLandings() + parseInt(totalLandings.getText()));
                mainWheel.setResource_Reserve_Replacement_Wheel(mainWheel.getResource_Reserve_Replacement_Wheel() -
                        parseInt(totalLandings.getText()));
                mainWheel.setIsNeedAttention(setBooleanValueMainWheel(mainWheel));
            }
        }
        WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);

        for (FrontBreak frontBreak : SaveData.frontBreaksList) {
            if (frontBreak.getSerialNumber().equals(aircraft.getLeftFrontBrake().getSerialNumber())
                || frontBreak.getSerialNumber().equals(aircraft.getRightFrontBrake().getSerialNumber())) {
                frontBreak.setTotalLandings(frontBreak.getTotalLandings() + parseInt(totalLandings.getText()));
                frontBreak.setResource_Reserve_Before_First_Repair(frontBreak.getResource_Reserve_Before_First_Repair() -
                        parseInt(totalLandings.getText()));
                frontBreak.setResource_Reserve_Before_Replacement(frontBreak.getResource_Reserve_Before_Replacement() -
                        parseInt(totalLandings.getText()));
                frontBreak.setIsNeedAttention(setBooleanValueFrontBreak(frontBreak));
            }
        }
        WriteFile.serialization(SaveData.frontBreaksList, FrontBreak.class);

        for (FrontWheel frontWheel : SaveData.frontWheelsList) {
            if (frontWheel.getSerialNumber().equals(aircraft.getLeftFrontWheel().getSerialNumber())
                || frontWheel.getSerialNumber().equals(aircraft.getRightFrontWheel().getSerialNumber())) {
                frontWheel.setTotalLandings(frontWheel.getTotalLandings() + parseInt(totalLandings.getText()));
                frontWheel.setResource_Reserve_Replacement_Wheel(frontWheel.getResource_Reserve_Replacement_Wheel() -
                        parseInt(totalLandings.getText()));
                frontWheel.setIsNeedAttention(setBooleanValueFrontWheel(frontWheel));
            }
        }
        WriteFile.serialization(SaveData.frontWheelsList, FrontWheel.class);

        for (CylinderOfRetractionExtension cylinder : SaveData.cylindersList) {
            if (cylinder.getSerialNumber().equals(aircraft.getLeftMainCylinder().getSerialNumber())
                || cylinder.getSerialNumber().equals(aircraft.getRightMainCylinder().getSerialNumber())
                || cylinder.getSerialNumber().equals(aircraft.getFrontCylinder().getSerialNumber())) {
                cylinder.setTotalLandings(cylinder.getTotalLandings() + parseInt(totalLandings.getText()));
                cylinder.setResource_Reserve_Before_First_Repair(cylinder.getResource_Reserve_Before_First_Repair() -
                        parseInt(totalLandings.getText()));
                cylinder.setResource_Reserve_Before_Second_Repair(cylinder.getResource_Reserve_Before_Second_Repair() -
                        parseInt(totalLandings.getText()));
                cylinder.setResource_Reserve_Before_Replacement(cylinder.getResource_Reserve_Before_Replacement() -
                        parseInt(totalLandings.getText()));
                cylinder.setIsNeedAttention(setBooleanValueCylinder(cylinder));
            }
        }
        WriteFile.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
    }
}
