package sample.calculating;

import javafx.scene.control.TextField;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.write.WriteFile;

import static java.lang.Integer.parseInt;
import static sample.setBoolean.SetBooleanValue.*;

public class CalculatingOperation {
    public static void calculatingResourcesAfterFlight(Aircraft aircraft, TextField flightHours,
                                            TextField flightMinutes, TextField flightAndEarthHours,
                                            TextField flightAndEarthMinutes, TextField totalLandings) {
        aircraft.getPlaner().setTotal_Operating_Time(aircraft.getPlaner().getTotal_Operating_Time() +
                (parseInt(flightHours.getText()) * 60 + parseInt(flightMinutes.getText())));
        aircraft.getPlaner().setTotal_Landing_Count(aircraft.getPlaner().getTotal_Landing_Count() +
                parseInt(totalLandings.getText()));
        aircraft.getPlaner().setResource_Reserve_Before_100hours(aircraft.getPlaner().getResource_Reserve_Before_100hours() -
                (parseInt(flightHours.getText()) * 60 + parseInt(flightMinutes.getText())));
        aircraft.getPlaner().setResource_Reserve_Before_200hours(aircraft.getPlaner().getResource_Reserve_Before_200hours() -
                (parseInt(flightHours.getText()) * 60 + parseInt(flightMinutes.getText())));
       aircraft.getPlaner().setIsNeedAttention(setBooleanValuePlaner(aircraft.getPlaner()));
       //ЛЕВЫЙ ДВИГАТЕЛЬ - НАЧАЛО
        aircraft.getLeftEngine().setTotalOperatingTime(aircraft.getLeftEngine().getTotalOperatingTime() +
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        //ПОПРОАВИТЬ ПОСЛЕ УТОЧНЕНИЯ
        aircraft.getLeftEngine().setTotalStartingEngineCount(aircraft.getLeftEngine().getTotalStartingEngineCount() +
                parseInt(totalLandings.getText()));
        aircraft.getLeftEngine().setResourceReserveBefore_10hours(aircraft.getLeftEngine().getResourceReserveBefore_10hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getLeftEngine().setResourceReserveBefore_25hours(aircraft.getLeftEngine().getResourceReserveBefore_25hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getLeftEngine().setResourceReserveBefore_50hours(aircraft.getLeftEngine().getResourceReserveBefore_50hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getLeftEngine().setResourceReserveBefore_100hours(aircraft.getLeftEngine().getResourceReserveBefore_100hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getLeftEngine().setResourceReserveBefore_150hours(aircraft.getLeftEngine().getResourceReserveBefore_150hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getLeftEngine().setResourceReserveBefore_278bulletin(aircraft.getLeftEngine().getResourceReserveBefore_278bulletin() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getLeftEngine().setOilChange(aircraft.getLeftEngine().getOilChange() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getLeftEngine().setIsNeedAttention(setBooleanValueEngine(aircraft.getLeftEngine()));
        //ПРАВЫЙ ДВИГАТЕЛЬ - НАЧАЛО
        aircraft.getRightEngine().setTotalOperatingTime(aircraft.getRightEngine().getTotalOperatingTime() +
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
       //ПОПРАВИТЬ ПОСЛЕ УТОЧНЕНИЯ
        aircraft.getRightEngine().setTotalStartingEngineCount(aircraft.getRightEngine().getTotalStartingEngineCount() +
                parseInt(totalLandings.getText()));
        aircraft.getRightEngine().setResourceReserveBefore_10hours(aircraft.getRightEngine().getResourceReserveBefore_10hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getRightEngine().setResourceReserveBefore_25hours(aircraft.getRightEngine().getResourceReserveBefore_25hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getRightEngine().setResourceReserveBefore_50hours(aircraft.getRightEngine().getResourceReserveBefore_50hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getRightEngine().setResourceReserveBefore_100hours(aircraft.getRightEngine().getResourceReserveBefore_100hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getRightEngine().setResourceReserveBefore_150hours(aircraft.getRightEngine().getResourceReserveBefore_150hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getRightEngine().setResourceReserveBefore_278bulletin(aircraft.getRightEngine().getResourceReserveBefore_278bulletin() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getRightEngine().setOilChange(aircraft.getRightEngine().getOilChange() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getRightEngine().setIsNeedAttention(setBooleanValueEngine(aircraft.getRightEngine()));
       //КСА - НАЧАЛО
        aircraft.getKsa().setTotal_Operating_Time(aircraft.getKsa().getTotal_Operating_Time() +
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
       //ПОПРАВИТЬ ПОСЛЕ УТОЧНЕНИЯ
        aircraft.getKsa().setTotal_Starting_Ksa_Count(aircraft.getKsa().getTotal_Operating_Time() +
                parseInt(totalLandings.getText()));
        aircraft.getKsa().setResource_Reserve_Before_25hours(aircraft.getKsa().getResource_Reserve_Before_25hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getKsa().setOilChange(aircraft.getKsa().getOilChange() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        aircraft.getKsa().setIsNeedAttention(setBooleanValueKsa(aircraft.getKsa()));
       //ЛЕВЫЙ ОСНОВНОЙ ТОРМОЗ - НАЧАЛО
        aircraft.getLeftMainBrake().setTotalLandings(aircraft.getLeftMainBrake().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainBrake().setResource_Reserve_Replacement_Break(aircraft.getLeftMainBrake().getResource_Reserve_Replacement_Break() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainBrake().setResource_Reserve_Replacement_RotatingDisks(aircraft.getLeftMainBrake().getResource_Reserve_Replacement_RotatingDisks() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainBrake().setResource_Reserve_Replacement_NonRotatingDisks(aircraft.getLeftMainBrake().getResource_Reserve_Replacement_NonRotatingDisks() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainBrake().setResource_Reserve_Replacement_PressureDisk(aircraft.getLeftMainBrake().getResource_Reserve_Replacement_PressureDisk() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainBrake().setResource_Reserve_Replacement_ReferenceDisk(aircraft.getLeftMainBrake().getResource_Reserve_Replacement_ReferenceDisk() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainBrake().setIsNeedAttention(setBooleanValueMainBreak(aircraft.getLeftMainBrake()));
        //ПРАВЫЙ ОСНОВНОЙ ТОРМОЗ - НАЧАЛО
        aircraft.getRightMainBrake().setTotalLandings(aircraft.getRightMainBrake().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getRightMainBrake().setResource_Reserve_Replacement_Break(aircraft.getRightMainBrake().getResource_Reserve_Replacement_Break() -
                parseInt(totalLandings.getText()));
        aircraft.getRightMainBrake().setResource_Reserve_Replacement_RotatingDisks(aircraft.getRightMainBrake().getResource_Reserve_Replacement_RotatingDisks() -
                parseInt(totalLandings.getText()));
        aircraft.getRightMainBrake().setResource_Reserve_Replacement_NonRotatingDisks(aircraft.getRightMainBrake().getResource_Reserve_Replacement_NonRotatingDisks() -
                parseInt(totalLandings.getText()));
        aircraft.getRightMainBrake().setResource_Reserve_Replacement_PressureDisk(aircraft.getRightMainBrake().getResource_Reserve_Replacement_PressureDisk() -
                parseInt(totalLandings.getText()));
        aircraft.getRightMainBrake().setResource_Reserve_Replacement_ReferenceDisk(aircraft.getRightMainBrake().getResource_Reserve_Replacement_ReferenceDisk() -
                parseInt(totalLandings.getText()));
        aircraft.getRightMainBrake().setIsNeedAttention(setBooleanValueMainBreak(aircraft.getRightMainBrake()));
        //ЛЕВОЕ ОСНОВНОЕ КОЛЕСО - НАЧАЛО
        aircraft.getLeftMainWheel().setTotalLandings(aircraft.getLeftMainWheel().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainWheel().setResource_Reserve_Replacement_Wheel(aircraft.getLeftMainWheel().getResource_Reserve_Replacement_Wheel() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainWheel().setIsNeedAttention(setBooleanValueMainWheel(aircraft.getLeftMainWheel()));
        //ПРАВОЕ ОСНОВНОЕ КОЛЕСО - НАЧАЛО
        aircraft.getRightMainWheel().setTotalLandings(aircraft.getRightMainWheel().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getRightMainWheel().setResource_Reserve_Replacement_Wheel(aircraft.getRightMainWheel().getResource_Reserve_Replacement_Wheel() -
                parseInt(totalLandings.getText()));
        aircraft.getRightMainWheel().setIsNeedAttention(setBooleanValueMainWheel(aircraft.getRightMainWheel()));
        //ЛЕВЫЙ ПЕРЕДНИЙ ТОРМОЗ - НАЧАЛО
        aircraft.getLeftFrontBrake().setTotalLandings(aircraft.getLeftFrontBrake().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getLeftFrontBrake().setResource_Reserve_Before_First_Repair(aircraft.getLeftFrontBrake().getResource_Reserve_Before_First_Repair() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftFrontBrake().setResource_Reserve_Before_Replacement(aircraft.getLeftFrontBrake().getResource_Reserve_Before_Replacement() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftFrontBrake().setIsNeedAttention(setBooleanValueFrontBreak(aircraft.getLeftFrontBrake()));
        //ПРАВЫЙ ПЕРЕДНИЙ ТОРМОЗ - НАЧАЛО
        aircraft.getRightFrontBrake().setTotalLandings(aircraft.getRightFrontBrake().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getRightFrontBrake().setResource_Reserve_Before_First_Repair(aircraft.getRightFrontBrake().getResource_Reserve_Before_First_Repair() -
                parseInt(totalLandings.getText()));
        aircraft.getRightFrontBrake().setResource_Reserve_Before_Replacement(aircraft.getRightFrontBrake().getResource_Reserve_Before_Replacement() -
                parseInt(totalLandings.getText()));
        aircraft.getRightFrontBrake().setIsNeedAttention(setBooleanValueFrontBreak(aircraft.getRightFrontBrake()));
        //ЛЕВОЕ ПЕРЕДЕНЕЕ КОЛЕСО - НАЧАЛО
        aircraft.getLeftFrontWheel().setTotalLandings(aircraft.getLeftFrontWheel().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getLeftFrontWheel().setResource_Reserve_Replacement_Wheel(aircraft.getLeftFrontWheel().getResource_Reserve_Replacement_Wheel() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftFrontWheel().setIsNeedAttention(setBooleanValueFrontWheel(aircraft.getLeftFrontWheel()));
        //ПЕРЕДНЕЕ ПРАВОЕ КОЛЕСО - НАЧАЛО
        aircraft.getRightFrontWheel().setTotalLandings(aircraft.getRightFrontWheel().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getRightFrontWheel().setResource_Reserve_Replacement_Wheel(aircraft.getRightFrontWheel().getResource_Reserve_Replacement_Wheel() -
                parseInt(totalLandings.getText()));
        aircraft.getRightFrontWheel().setIsNeedAttention(setBooleanValueFrontWheel(aircraft.getRightFrontWheel()));
        //ЦИЛИНДР ПОДКОСА ЛЕВОЙ ОСНОВНОЙ СТОЙКИ ШАССИ - НАЧАЛО
        aircraft.getLeftMainCylinder().setTotalLandings(aircraft.getLeftMainCylinder().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainCylinder().setResource_Reserve_Before_First_Repair(aircraft.getLeftMainCylinder().getResource_Reserve_Before_First_Repair() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainCylinder().setResource_Reserve_Before_Second_Repair(aircraft.getLeftMainCylinder().getResource_Reserve_Before_Second_Repair() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainCylinder().setResource_Reserve_Before_Replacement(aircraft.getLeftMainCylinder().getResource_Reserve_Before_Replacement() -
                parseInt(totalLandings.getText()));
        aircraft.getLeftMainCylinder().setIsNeedAttention(setBooleanValueCylinder(aircraft.getLeftMainCylinder()));
        //ЦИЛИНДР ПОДКОСА ПРАВОЙ ОСНОВНОЙ СТОЙКИ ШАССИ - НАЧАЛО
        aircraft.getRightMainCylinder().setTotalLandings(aircraft.getRightMainCylinder().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getRightMainCylinder().setResource_Reserve_Before_First_Repair(aircraft.getRightMainCylinder().getResource_Reserve_Before_First_Repair() -
                parseInt(totalLandings.getText()));
        aircraft.getRightMainCylinder().setResource_Reserve_Before_Second_Repair(aircraft.getRightMainCylinder().getResource_Reserve_Before_Second_Repair() -
                parseInt(totalLandings.getText()));
        aircraft.getRightMainCylinder().setResource_Reserve_Before_Replacement(aircraft.getRightMainCylinder().getResource_Reserve_Before_Replacement() -
                parseInt(totalLandings.getText()));
        aircraft.getRightMainCylinder().setIsNeedAttention(setBooleanValueCylinder(aircraft.getRightMainCylinder()));
        //ЦИЛИНД ПОДКОСА ПЕРЕДНЕЙ СТОЙКИ ШАССИ - НАЧАЛО
        aircraft.getFrontCylinder().setTotalLandings(aircraft.getFrontCylinder().getTotalLandings() +
                parseInt(totalLandings.getText()));
        aircraft.getFrontCylinder().setResource_Reserve_Before_First_Repair(aircraft.getFrontCylinder().getResource_Reserve_Before_First_Repair() -
                parseInt(totalLandings.getText()));
        aircraft.getFrontCylinder().setResource_Reserve_Before_Second_Repair(aircraft.getFrontCylinder().getResource_Reserve_Before_Second_Repair() -
                parseInt(totalLandings.getText()));
        aircraft.getFrontCylinder().setResource_Reserve_Before_Replacement(aircraft.getFrontCylinder().getResource_Reserve_Before_Replacement() -
                parseInt(totalLandings.getText()));
        aircraft.getFrontCylinder().setIsNeedAttention(setBooleanValueCylinder(aircraft.getFrontCylinder()));
        WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
    }
}
