package service;

import data.Aircraft;
import data.SaveData;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.*;
import io.writer.FilesWriter;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import utils.Utils;

import java.time.ZoneId;
import java.util.Date;

import static java.lang.Integer.parseInt;
import static service.BooleanValueSetter.*;

@Slf4j
public class OperationCalculator {
    /**
     * Метод пересчитывает наработку и остаток ресурса у всего самолета целиком после добавления данных за летную смену.
     * На основании пересчитанной наработки и остатков в самолет сэтится новое значение boolean отвечающее за индикацию самолета
     * (isNeedAttention), после происходит сериализация обновленных данных
     */
    public static void calculatingResourcesAfterFlight(Aircraft aircraft, TextField flightHours,
                                                       TextField flightMinutes, TextField flightAndGroundHours,
                                                       TextField flightAndGroundMinutes, TextField totalLandings,
                                                       DatePicker lastFlightDate) {
        planerAfterFlight(aircraft.getPlaner(), flightHours, flightMinutes, totalLandings, lastFlightDate);
        engineAfterFlight(aircraft.getLeftEngine(), flightAndGroundHours, flightAndGroundMinutes, totalLandings);
        engineAfterFlight(aircraft.getRightEngine(), flightAndGroundHours, flightAndGroundMinutes, totalLandings);
        ksaAfterFlight(aircraft.getKsa(), flightAndGroundHours, flightAndGroundMinutes, totalLandings);
        mainBreakAfterFlight(aircraft.getLeftMainBrake(), totalLandings);
        mainBreakAfterFlight(aircraft.getRightMainBrake(), totalLandings);
        mainWheelAfterFlight(aircraft.getLeftMainWheel(), totalLandings);
        mainWheelAfterFlight(aircraft.getRightMainWheel(), totalLandings);
        frontBreakAfterFlight(aircraft.getLeftFrontBrake(), totalLandings);
        frontBreakAfterFlight(aircraft.getRightFrontBrake(), totalLandings);
        frontWheelAfterFlight(aircraft.getLeftFrontWheel(), totalLandings);
        frontWheelAfterFlight(aircraft.getRightFrontWheel(), totalLandings);
        cylinderAfterFlight(aircraft.getLeftMainCylinder(), totalLandings);
        cylinderAfterFlight(aircraft.getRightMainCylinder(), totalLandings);
        cylinderAfterFlight(aircraft.getFrontCylinder(), totalLandings);
        aircraft.setIsNeedAttention(setBooleanValueAircraft(aircraft));
        FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
        log.info("Пользователь добавил наработку за летную смену");
    }

    /**
     * Метод пересчитывает наработку и остаток ресурсов силовой установки после добавления опробования
     */
    public static void calculatingResourcesAfterTestStart(Aircraft aircraft, TextField leftEngineOnGround,
                                                          TextField rightEngineOnGround, TextField startingCountLeft,
                                                          TextField startingCountRight) {
        engineAfterTest(aircraft.getLeftEngine(), leftEngineOnGround, startingCountLeft);
        engineAfterTest(aircraft.getRightEngine(), rightEngineOnGround, startingCountRight);
        ksaAfterTest(aircraft.getKsa(), rightEngineOnGround, leftEngineOnGround, startingCountRight, startingCountLeft);
        aircraft.setIsNeedAttention(setBooleanValueAircraft(aircraft));
        FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
    }

    /**
     * Метод пересчитывает наработку и остаток ресурса у планера после добавления данных за летную смену.
     * На основании пересчитанной наработки и остатков в планер  сэтится новое значение boolean, отвечающее за индикацию
     * (isNeedAttention)
     */
    public static void planerAfterFlight(Planer planer, TextField flightHours,
                                         TextField flightMinutes, TextField totalLandings,
                                         DatePicker lastFlightDate) {
        planer.setTotal_Operating_Time(planer.getTotal_Operating_Time() +
                (parseInt(flightHours.getText()) * 60 + parseInt(flightMinutes.getText())));
        planer.setTotal_Landing_Count(planer.getTotal_Landing_Count() +
                parseInt(totalLandings.getText()));
        planer.setResource_Reserve_Before_100hours(planer.getResource_Reserve_Before_100hours() -
                (parseInt(flightHours.getText()) * 60 + parseInt(flightMinutes.getText())));
        planer.setResource_Reserve_Before_200hours(planer.getResource_Reserve_Before_200hours() -
                (parseInt(flightHours.getText()) * 60 + parseInt(flightMinutes.getText())));
        planer.setIsNeedAttention(setBooleanValuePlaner(planer));
        planer.setLast_Flight_Date(Date.from(lastFlightDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * Метод пересчитывает наработку и остаток ресурса у двигателя после добавления данных за летную смену.
     * На основании пересчитанной наработки и остатков в двигатель  сэтится новое значение boolean,
     * отвечающее за индикацию  (isNeedAttention)
     */
    public static void engineAfterFlight(Engine engine, TextField flightAndEarthHours,
                                         TextField flightAndEarthMinutes, TextField totalLandings) {
        engine.setTotalOperatingTime(engine.getTotalOperatingTime() +
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
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

    /**
     * Метод пересчитывает наработку и остаток ресурса у КСА после добавления данных за летную смену.
     * На основании пересчитанной наработки и остатков в КСА  сэтится новое значение boolean,
     * отвечающее за индикацию (isNeedAttention)
     */
    public static void ksaAfterFlight(Ksa ksa, TextField flightAndEarthHours,
                                      TextField flightAndEarthMinutes, TextField totalLandings) {
        ksa.setTotal_Operating_Time(ksa.getTotal_Operating_Time() +
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        ksa.setTotal_Starting_Ksa_Count(ksa.getTotal_Starting_Ksa_Count() +
                parseInt(totalLandings.getText()));
        ksa.setResource_Reserve_Before_25hours(ksa.getResource_Reserve_Before_25hours() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        ksa.setOilChange(ksa.getOilChange() -
                (parseInt(flightAndEarthHours.getText()) * 60 + parseInt(flightAndEarthMinutes.getText())));
        ksa.setIsNeedAttention(setBooleanValueKsa(ksa));
    }

    /**
     * Метод пересчитывает наработку и остаток ресурса у основного тормоза после добавления данных за летную смену.
     * На основании пересчитанной наработки и остатков в основной тормоз  сэтится новое значение boolean,
     * отвечающее за индикацию  (isNeedAttention)
     */
    public static void mainBreakAfterFlight(MainBreak mainBreak, TextField totalLandings) {
        mainBreak.setTotalLandings(mainBreak.getTotalLandings() +
                parseInt(totalLandings.getText()));
        mainBreak.setResource_Reserve_Replacement_Break(
                mainBreak.getResource_Reserve_Replacement_Break() -
                        parseInt(totalLandings.getText()));
        mainBreak.setResource_Reserve_Replacement_RotatingDisks(
                mainBreak.getResource_Reserve_Replacement_RotatingDisks() -
                        parseInt(totalLandings.getText()));
        mainBreak.setResource_Reserve_Replacement_NonRotatingDisks(
                mainBreak.getResource_Reserve_Replacement_NonRotatingDisks() -
                        parseInt(totalLandings.getText()));
        if (mainBreak.getResource_Reserve_Replacement_PressureDisk() != 0) {
            mainBreak.setResource_Reserve_Replacement_PressureDisk(
                    mainBreak.getResource_Reserve_Replacement_PressureDisk() -
                            parseInt(totalLandings.getText()));
        }
        if (mainBreak.getResource_Reserve_Replacement_ReferenceDisk() != 0) {
            mainBreak.setResource_Reserve_Replacement_ReferenceDisk(
                    mainBreak.getResource_Reserve_Replacement_ReferenceDisk() -
                            parseInt(totalLandings.getText()));
        }
        mainBreak.setIsNeedAttention(setBooleanValueMainBreak(mainBreak));
    }

    /**
     * Метод пересчитывает наработку и остаток ресурса у основного колеса после добавления данных за летную смену.
     * На основании пересчитанной наработки и остатков в основное колесо  сэтится новое значение boolean,
     * отвечающее за индикацию  (isNeedAttention)
     */
    public static void mainWheelAfterFlight(MainWheel mainWheel, TextField totalLandings) {
        mainWheel.setTotalLandings(mainWheel.getTotalLandings() +
                parseInt(totalLandings.getText()));
        mainWheel.setResource_Reserve_Replacement_Wheel(
                mainWheel.getResource_Reserve_Replacement_Wheel() -
                        parseInt(totalLandings.getText()));
        mainWheel.setIsNeedAttention(setBooleanValueMainWheel(mainWheel));
    }

    /**
     * Метод пересчитывает наработку и остаток ресурса у переднего тормоза после добавления данных за летную смену.
     * На основании пересчитанной наработки и остатков в передний тормоз  сэтится новое значение boolean,
     * отвечающее за индикацию  (isNeedAttention)
     */
    public static void frontBreakAfterFlight(FrontBreak frontBreak, TextField totalLandings) {
        frontBreak.setTotalLandings(frontBreak.getTotalLandings() +
                parseInt(totalLandings.getText()));
        if (frontBreak.getResource_Reserve_Before_First_Repair() != 0) {
            frontBreak.setResource_Reserve_Before_First_Repair(
                    frontBreak.getResource_Reserve_Before_First_Repair() -
                            parseInt(totalLandings.getText()));
        }
        frontBreak.setResource_Reserve_Before_Replacement(
                frontBreak.getResource_Reserve_Before_Replacement() -
                        parseInt(totalLandings.getText()));
        frontBreak.setIsNeedAttention(setBooleanValueFrontBreak(frontBreak));
    }

    /**
     * Метод пересчитывает наработку и остаток ресурса у переднего колеса после добавления данных за летную смену.
     * На основании пересчитанной наработки и остатков в переднее колесо  сэтится новое значение boolean,
     * отвечающее за индикацию  (isNeedAttention)
     */
    public static void frontWheelAfterFlight(FrontWheel frontWheel, TextField totalLandings) {
        frontWheel.setTotalLandings(frontWheel.getTotalLandings() +
                parseInt(totalLandings.getText()));
        frontWheel.setResource_Reserve_Replacement_Wheel(
                frontWheel.getResource_Reserve_Replacement_Wheel() -
                        parseInt(totalLandings.getText()));
        frontWheel.setIsNeedAttention(setBooleanValueFrontWheel(frontWheel));
    }

    /**
     * Метод пересчитывает наработку и остаток ресурса у цилиндра подкоса после добавления данных за летную смену.
     * На основании пересчитанной наработки и остатков в цилиндр подкоса  сэтится новое значение boolean,
     * отвечающее за индикацию  (isNeedAttention)
     */
    public static void cylinderAfterFlight(CylinderOfRetractionExtension cylinder, TextField totalLandings) {
        cylinder.setTotalLandings(
                cylinder.getTotalLandings() +
                        parseInt(totalLandings.getText()));
        if (cylinder.getResource_Reserve_Before_First_Repair() != 0) {
            cylinder.setResource_Reserve_Before_First_Repair(
                    cylinder.getResource_Reserve_Before_First_Repair() -
                            parseInt(totalLandings.getText()));
        }
        if (cylinder.getResource_Reserve_Before_Second_Repair() != 0) {
            cylinder.setResource_Reserve_Before_Second_Repair(
                    cylinder.getResource_Reserve_Before_Second_Repair() -
                            parseInt(totalLandings.getText()));
        }
        cylinder.setResource_Reserve_Before_Replacement(
                cylinder.getResource_Reserve_Before_Replacement() -
                        parseInt(totalLandings.getText()));
        cylinder.setIsNeedAttention(setBooleanValueCylinder(cylinder));
    }

    /**
     * Метод пересчитывает наработку и остаток ресурсов  двигателя после добавления опробования.
     * На основании пересчитанной наработки и остатков в двигатель  сэтится новое значение boolean,
     * отвечающее за индикацию  (isNeedAttention)
     */
    public static void engineAfterTest(Engine engine,
                                       TextField engineOnGround,
                                       TextField startingCount) {
        engine.setTotalOperatingTime(engine.getTotalOperatingTime() +
                parseInt(engineOnGround.getText()));
        engine.setTotalStartingEngineCount(engine.getTotalStartingEngineCount() +
                parseInt(startingCount.getText()));
        engine.setResourceReserveBefore_10hours(engine.getResourceReserveBefore_10hours() -
                parseInt(engineOnGround.getText()));
        engine.setResourceReserveBefore_25hours(engine.getResourceReserveBefore_25hours() -
                parseInt(engineOnGround.getText()));
        engine.setResourceReserveBefore_50hours(engine.getResourceReserveBefore_50hours() -
                parseInt(engineOnGround.getText()));
        engine.setResourceReserveBefore_100hours(engine.getResourceReserveBefore_100hours() -
                parseInt(engineOnGround.getText()));
        engine.setResourceReserveBefore_150hours(engine.getResourceReserveBefore_150hours() -
                parseInt(engineOnGround.getText()));
        engine.setResourceReserveBefore_278bulletin(engine.getResourceReserveBefore_278bulletin() -
                parseInt(engineOnGround.getText()));
        engine.setOilChange(engine.getOilChange() -
                parseInt(engineOnGround.getText()));
        engine.setIsNeedAttention(setBooleanValueEngine(engine));
    }

    /**
     * Метод пересчитывает наработку и остаток ресурсов КСА после добавления опробования в зависимости от того,
     * последовательным был запуск или раздельным.
     * На основании пересчитанной наработки и остатков в КСА  сэтится новое значение boolean,
     * отвечающее за индикацию  (isNeedAttention)
     */
    public static void ksaAfterTest(Ksa ksa, TextField rightEngineOnGround,
                                    TextField leftEngineOnGround, TextField startingCountRight,
                                    TextField startingCountLeft) {
        if (parseInt(rightEngineOnGround.getText()) == 0 && parseInt(startingCountRight.getText()) == 0) {
            ksa.setTotal_Operating_Time(ksa.getTotal_Operating_Time() +
                    parseInt(leftEngineOnGround.getText()));
            ksa.setResource_Reserve_Before_25hours(ksa.getResource_Reserve_Before_25hours() -
                    parseInt(leftEngineOnGround.getText()));
            ksa.setOilChange(ksa.getOilChange() - parseInt(leftEngineOnGround.getText()));
            ksa.setStarting_Left_Count(ksa.getStarting_Left_Count() +
                    parseInt(startingCountLeft.getText()));
        } else if (parseInt(leftEngineOnGround.getText()) == 0 && parseInt(startingCountLeft.getText()) == 0) {
            ksa.setTotal_Operating_Time(ksa.getTotal_Operating_Time() +
                    parseInt(rightEngineOnGround.getText()));
            ksa.setResource_Reserve_Before_25hours(ksa.getResource_Reserve_Before_25hours() -
                    parseInt(rightEngineOnGround.getText()));
            ksa.setOilChange(ksa.getOilChange() - parseInt(rightEngineOnGround.getText()));
            ksa.setStarting_Right_Count(ksa.getStarting_Right_Count() +
                    parseInt(startingCountRight.getText()));
        } else if (parseInt(leftEngineOnGround.getText()) == parseInt(rightEngineOnGround.getText())
                && parseInt(startingCountLeft.getText()) == parseInt(startingCountRight.getText())) {
            ksa.setTotal_Operating_Time(ksa.getTotal_Operating_Time() +
                    parseInt(leftEngineOnGround.getText()));
            ksa.setResource_Reserve_Before_25hours(ksa.getResource_Reserve_Before_25hours() -
                    parseInt(leftEngineOnGround.getText()));
            ksa.setOilChange(ksa.getOilChange() - parseInt(leftEngineOnGround.getText()));
            ksa.setTotal_Starting_Ksa_Count(ksa.getTotal_Starting_Ksa_Count() +
                    parseInt(startingCountRight.getText()));
        }
        ksa.setIsNeedAttention(setBooleanValueKsa(ksa));
    }
}
