package utils;

import constants.TextConstants;
import data.Aircraft;
import data.SaveData;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.*;
import io.writer.FilesWriter;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import static java.lang.Integer.parseInt;
import static service.DateResourcesCalculator.calculateDateInDays;
import static service.DateResourcesCalculator.calculateDateInMonth;
import static service.OperationCalculator.*;
import static service.BooleanValueSetter.*;

public class UpdateAircraftComponents {
    /**
     * Метод обновляет наработку и остатки ресурсов всех агрегатов после добавления летной смены в их личных текстовых файлах,
     * тем самымсинхронизируя изменения как в агрегатах установленных на самолете,так и в списке агрегатов и ограниченного ресурса.
     */
    public static void updatingComponents(Aircraft aircraft, TextField flightHours,
                                          TextField flightMinutes, TextField flightAndEarthHours,
                                          TextField flightAndEarthMinutes, TextField totalLandings,
                                          DatePicker lastFlightDate) {
        updatingEngine(aircraft, flightAndEarthHours, flightAndEarthMinutes, totalLandings);
        updatingPlaner(aircraft, flightHours, flightMinutes, totalLandings, lastFlightDate);
        updatingKsa(aircraft, flightAndEarthHours, flightAndEarthMinutes, totalLandings);
        updatingMainBreak(aircraft, totalLandings);
        updatingMainWheel(aircraft, totalLandings);
        updatingFrontBreak(aircraft, totalLandings);
        updatingFrontWheel(aircraft, totalLandings);
        updatingCylinder(aircraft, totalLandings);
    }

    /**
     * Метод обновляет наработку и остатки ресурсов двигателей после добавления летной смены
     * в разделе "СПИСОК АГРЕГАТОВ"
     */
    private static void updatingEngine(Aircraft aircraft, TextField flightAndEarthHours,
                                       TextField flightAndEarthMinutes, TextField totalLandings) {
        for (Engine engine : SaveData.enginesList) {
            if (engine.getSerialNumber().equals(aircraft.getLeftEngine().getSerialNumber())
                    || engine.getSerialNumber().equals(aircraft.getRightEngine().getSerialNumber())) {
                engineAfterFlight(engine, flightAndEarthHours,
                        flightAndEarthMinutes, totalLandings);
            }
        }
        FilesWriter.serialization(SaveData.enginesList, Engine.class);
    }

    /**
     * Метод обновляет наработку и остатки ресурсов планера после добавления летной смены
     * в списке планеров
     */
    private static void updatingPlaner(Aircraft aircraft, TextField flightHours,
                                       TextField flightMinutes, TextField totalLandings,
                                       DatePicker lastFlightDate) {
        for (Planer planer : SaveData.planersList) {
            if (planer.getSerialNumber().equals(aircraft.getPlaner().getSerialNumber())) {
                planerAfterFlight(planer, flightHours, flightMinutes, totalLandings, lastFlightDate);
            }
        }
        FilesWriter.serialization(SaveData.planersList, Planer.class);
    }

    /**
     * Метод обновляет наработку и остатки ресурсов КСА после добавления летной смены
     * в разделе "СПИСОК АГРЕГАТОВ"
     */
    private static void updatingKsa(Aircraft aircraft, TextField flightAndEarthHours,
                                    TextField flightAndEarthMinutes, TextField totalLandings) {
        for (Ksa ksa : SaveData.ksaList) {
            if (ksa.getSerialNumber().equals(aircraft.getKsa().getSerialNumber())) {
                ksaAfterFlight(ksa, flightAndEarthHours, flightAndEarthMinutes, totalLandings);
            }
        }
        FilesWriter.serialization(SaveData.ksaList, Ksa.class);
    }

    /**
     * Метод обновляет наработку и остатки ресурсов основных тормозов после добавления летной смены
     */
    private static void updatingMainBreak(Aircraft aircraft, TextField totalLandings) {
        for (MainBreak mainBreak : SaveData.mainBreaksList) {
            if (mainBreak.getSerialNumber().equals(aircraft.getLeftMainBrake().getSerialNumber())
                    || mainBreak.getSerialNumber().equals(aircraft.getRightMainBrake().getSerialNumber())) {
                mainBreakAfterFlight(mainBreak, totalLandings);
            }
        }
        FilesWriter.serialization(SaveData.mainBreaksList, MainBreak.class);
    }

    /**
     * Метод обновляет наработку и остатки ресурсов основных колес после добавления летной смены
     */
    private static void updatingMainWheel(Aircraft aircraft, TextField totalLandings) {
        for (MainWheel mainWheel : SaveData.mainWheelsList) {
            if (mainWheel.getSerialNumber().equals(aircraft.getLeftMainWheel().getSerialNumber())
                    || mainWheel.getSerialNumber().equals(aircraft.getRightMainWheel().getSerialNumber())) {
                mainWheelAfterFlight(mainWheel, totalLandings);
            }
        }
        FilesWriter.serialization(SaveData.mainWheelsList, MainWheel.class);
    }

    /**
     * Метод обновляет наработку и остатки ресурсов передниъ тормозов после добавления летной смены
     */
    private static void updatingFrontBreak(Aircraft aircraft, TextField totalLandings) {
        for (FrontBreak frontBreak : SaveData.frontBreaksList) {
            if (frontBreak.getSerialNumber().equals(aircraft.getLeftFrontBrake().getSerialNumber())
                    || frontBreak.getSerialNumber().equals(aircraft.getRightFrontBrake().getSerialNumber())) {
                frontBreakAfterFlight(frontBreak, totalLandings);
            }
        }
        FilesWriter.serialization(SaveData.frontBreaksList, FrontBreak.class);
    }

    /**
     * Метод обновляет наработку и остатки ресурсов передних колес после добавления летной смены
     */
    private static void updatingFrontWheel(Aircraft aircraft, TextField totalLandings) {
        for (FrontWheel frontWheel : SaveData.frontWheelsList) {
            if (frontWheel.getSerialNumber().equals(aircraft.getLeftFrontWheel().getSerialNumber())
                    || frontWheel.getSerialNumber().equals(aircraft.getRightFrontWheel().getSerialNumber())) {
                frontWheel.setTotalLandings(frontWheel.getTotalLandings() + parseInt(totalLandings.getText()));
                frontWheel.setResource_Reserve_Replacement_Wheel(frontWheel.getResource_Reserve_Replacement_Wheel() -
                        parseInt(totalLandings.getText()));
                frontWheel.setIsNeedAttention(setBooleanValueFrontWheel(frontWheel));
            }
        }
        FilesWriter.serialization(SaveData.frontWheelsList, FrontWheel.class);
    }

    /**
     * Метод обновляет наработку и остатки ресурсов цилиндров подкоса после добавления летной смены
     */
    private static void updatingCylinder(Aircraft aircraft, TextField totalLandings) {
        for (CylinderOfRetractionExtension cylinder : SaveData.cylindersList) {
            if (cylinder.getSerialNumber().equals(aircraft.getLeftMainCylinder().getSerialNumber())
                    || cylinder.getSerialNumber().equals(aircraft.getRightMainCylinder().getSerialNumber())
                    || cylinder.getSerialNumber().equals(aircraft.getFrontCylinder().getSerialNumber())) {
                cylinderAfterFlight(cylinder, totalLandings);
            }
        }
        FilesWriter.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
    }

    /**
     * Метод обновялет остаток до работ по планеру, которые считаются в днях,
     * каждый день считая разницу между датой из файла и актуальной на сегодняшний день
     */
    public static void updateReserveDays() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            aircraft.getPlaner().setDays_Reserve_Before_30DaysParking(
                    calculateDateInDays(aircraft.getPlaner().getLast_Flight_Date(),
                    aircraft.getPlaner().getDate_Work_After_30days_Parking()));
            aircraft.getPlaner().setDays_Reserve_Before_6months_Operating(
                    calculateDateInMonth(aircraft.getPlaner().getDate_Work_After_6months_Operation()));
        }
        FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
    }

    /**
     * Метод обновляет наработку и остатки ресурсов агрегатов силовой установки в разделе "СПИСКОК АГРЕГАТОВ"
     * после добавления опробования.
     */
    public static void updatingPowerPlantAfterTestStarting(Aircraft aircraft, TextField leftEngineOnGround,
                                                           TextField rightEngineOnGround, TextField startingCountLeft,
                                                           TextField startingCountRight) {
        for (Engine engine : SaveData.enginesList) {
            if (engine.getSerialNumber().equals(aircraft.getLeftEngine().getSerialNumber())) {
                engineAfterTest(engine, leftEngineOnGround, startingCountLeft);
            } else if (engine.getSerialNumber().equals(aircraft.getRightEngine().getSerialNumber())) {
                engineAfterTest(engine, rightEngineOnGround, startingCountRight);
            }
            FilesWriter.serialization(SaveData.enginesList, Engine.class);
        }
        for (Ksa ksa : SaveData.ksaList) {
            if (ksa.getSerialNumber().equals(aircraft.getKsa().getSerialNumber())) {
                ksaAfterTest(ksa,
                        rightEngineOnGround,
                        leftEngineOnGround,
                        startingCountRight,
                        startingCountLeft);
            }
            FilesWriter.serialization(SaveData.ksaList, Ksa.class);
        }
    }

    /**
     * Метод обновляте информацию о том, на каком самолете установлен двигатель
     */
    public static void updateInstalledEngine(Aircraft createdAircraft, Aircraft changedAircraft) {
        Engine leftEngine = createdAircraft.getLeftEngine();
        Engine rightEngine = createdAircraft.getRightEngine();
        Engine leftEngineChanged = changedAircraft.getLeftEngine();
        Engine rightEngineChanged = changedAircraft.getRightEngine();

        if (leftEngine != leftEngineChanged) {
            if (leftEngine != null) {
                leftEngine.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
            if (leftEngineChanged != null) {
                leftEngineChanged.setAircraftNumberInstalled(changedAircraft.getAircraftNumber());
            } else {
                leftEngine.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
        }
        if (rightEngine != rightEngineChanged) {
            if (rightEngine != null) {
                rightEngine.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
            if (rightEngineChanged != null) {
                rightEngineChanged.setAircraftNumberInstalled(changedAircraft.getAircraftNumber());
            } else {
                rightEngine.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
        }
    }

    /**
     * Метод обновляте информацию о том, на каком самолете установлен КСА
     */
    public static void updateInstalledKsa(Aircraft createdAircraft, Aircraft changedAircraft) {
        Ksa ksa = createdAircraft.getKsa();
        Ksa ksaChanged = changedAircraft.getKsa();
        if (ksa != ksaChanged) {
            if (ksa != null) {
                ksa.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
            if (ksaChanged != null) {
                ksaChanged.setAircraftNumberInstalled(changedAircraft.getAircraftNumber());
            } else {
                ksa.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
        }
    }

    /** Метод обновляет стату агрегатов, которые были на нем установлены, после удаления самолета */
    public static void update_Installed_Engine_After_DeleteAircraft(Aircraft aircraft) {
        for (Engine engine : SaveData.enginesList) {
            if (aircraft.getLeftEngine() != null
                    && aircraft.getLeftEngine().getSerialNumber().equals(engine.getSerialNumber())) {
                    engine.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
            if (aircraft.getRightEngine() != null
                && aircraft.getRightEngine().getSerialNumber().equals(engine.getSerialNumber())) {
                engine.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
        }
        FilesWriter.serialization(SaveData.enginesList, Engine.class);
    }

    public static void update_Installed_Ksa_After_DeleteAircraft(Aircraft aircraft) {
        for (Ksa ksa : SaveData.ksaList) {
            if (aircraft.getKsa() != null
                && aircraft.getKsa().getSerialNumber().equals(ksa.getSerialNumber())) {
                ksa.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
        }
    }
}


