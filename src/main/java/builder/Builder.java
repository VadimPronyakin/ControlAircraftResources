package builder;

import constants.TextConstants;
import data.Aircraft;
import data.Engineer;
import data.OperatingHistory;
import data.SaveData;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.*;
import data.enums.Rank;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;
import service.BooleanValueSetter;

import java.time.ZoneId;
import java.util.Date;

import static io.writer.FilesWriter.serialization;
import static java.lang.Integer.parseInt;
import static service.BooleanValueSetter.*;
import static service.DateResourcesCalculator.calculateDateInDays;
import static service.DateResourcesCalculator.calculateDateInMonth;
import static utils.UpdateAircraftComponents.*;

@Slf4j
public class Builder {

    /**
     * Метод заполняет поля для создания или изменения самолета
     */
    public static void completionAircraft(Aircraft aircraft,
                                          TextField aircraftNumber,
                                          TextField orderNumber,
                                          DatePicker orderDate,
                                          ComboBox[] box) {
        aircraft.setOrderDate(Date.from(orderDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        aircraft.setOrderNumber(parseInt(orderNumber.getText()));
        aircraft.setPlaner((Planer) box[4].getSelectionModel().getSelectedItem());
        aircraft.setLeftMainBrake((MainBreak) box[5].getSelectionModel().getSelectedItem());
        aircraft.setRightMainBrake((MainBreak) box[6].getSelectionModel().getSelectedItem());
        aircraft.setLeftFrontBrake((FrontBreak) box[9].getSelectionModel().getSelectedItem());
        aircraft.setRightFrontBrake((FrontBreak) box[10].getSelectionModel().getSelectedItem());
        aircraft.setLeftMainWheel((MainWheel) box[7].getSelectionModel().getSelectedItem());
        aircraft.setRightMainWheel((MainWheel) box[8].getSelectionModel().getSelectedItem());
        aircraft.setLeftFrontWheel((FrontWheel) box[11].getSelectionModel().getSelectedItem());
        aircraft.setRightFrontWheel((FrontWheel) box[12].getSelectionModel().getSelectedItem());
        aircraft.setAircraftNumber(aircraftNumber.getText());
        aircraft.setIak((Engineer) box[0].getSelectionModel().getSelectedItem());
        aircraft.setFullNameEngineer(aircraft.getIak().getFullName());
        aircraft.setRightMainCylinder((CylinderOfRetractionExtension) box[14].getSelectionModel().getSelectedItem());
        aircraft.setLeftMainCylinder((CylinderOfRetractionExtension) box[13].getSelectionModel().getSelectedItem());
        aircraft.setFrontCylinder((CylinderOfRetractionExtension) box[15].getSelectionModel().getSelectedItem());
        Aircraft createdAircraft = aircraft.toBuilder().build();
        if (box[1].getSelectionModel().getSelectedItem() != null) {
            aircraft.setLeftEngine((Engine) box[1].getSelectionModel().getSelectedItem());
        } else {
            aircraft.setLeftEngine(null);
        }
        if (box[2].getSelectionModel().getSelectedItem() != null) {
            aircraft.setRightEngine((Engine) box[2].getSelectionModel().getSelectedItem());
        } else {
            aircraft.setRightEngine(null);
        }
        if (box[3].getSelectionModel().getSelectedItem() != null) {
            aircraft.setKsa((Ksa) box[3].getSelectionModel().getSelectedItem());
        } else {
            aircraft.setKsa(null);
        }
        updateInstalledEngine(createdAircraft, aircraft);
        serialization(SaveData.enginesList, Engine.class);
        updateInstalledKsa(createdAircraft, aircraft);
        serialization(SaveData.ksaList, Ksa.class);
    }

    /**
     * Метод создает новый двигатель
     */
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
                .serialNumber(fields[17].getText())
                .aircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT)
                .build();
        engine.setIsNeedAttention(setBooleanValueEngine(engine));
        log.info("Создали новый двигатель {} \n" +
                        "с общей наработкой {} \n" +
                        "с общим количеством запусков {} \n" +
                        "остаток ресорса до 10-ти часовых работ {} \n" +
                        "остаток ресорса до 25-ти часовых работ {} \n" +
                        "остаток ресорса до 50-ти часовых работ {} \n" +
                        "остаток ресорса до 100-ти часовых работ {} \n" +
                        "остаток ресорса до 150-ти часовых работ {} \n" +
                        "остаток ресорса до замены масла {} \n" +
                        "остаток ресорса до 278-го бюллетеня {}", engine,
                engine.getTotalOperatingTime(),
                engine.getTotalStartingEngineCount(),
                engine.getResourceReserveBefore_10hours(),
                engine.getResourceReserveBefore_25hours(),
                engine.getResourceReserveBefore_50hours(),
                engine.getResourceReserveBefore_100hours(),
                engine.getResourceReserveBefore_150hours(),
                engine.getOilChange(),
                engine.getResourceReserveBefore_278bulletin());
        SaveData.enginesList.add(engine);
        serialization(SaveData.enginesList, Engine.class);
    }


    /**
     * Метод создает новый передний тормоз
     */
    public static void createFrontBreak(TextField[] fields) {
        FrontBreak frontBreak = FrontBreak.builder()
                .totalLandings(parseInt(fields[0].getText()))
                .resource_Reserve_Before_First_Repair(parseInt(fields[1].getText()))
                .resource_Reserve_Before_Replacement(parseInt(fields[2].getText()))
                .serialNumber(fields[3].getText())
                .aircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT)
                .build();
        frontBreak.setIsNeedAttention(setBooleanValueFrontBreak(frontBreak));
        SaveData.frontBreaksList.add(frontBreak);
        serialization(SaveData.frontBreaksList, FrontBreak.class);
    }

    /**
     * Метод создает новое переднее колесо
     */
    public static void createFrontWheel(TextField field1, TextField field2, TextField field3) {
        FrontWheel frontWheel = FrontWheel.builder()
                .totalLandings(parseInt(field1.getText()))
                .resource_Reserve_Replacement_Wheel(parseInt(field2.getText()))
                .serialNumber(field3.getText())
                .aircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT)
                .build();
        frontWheel.setIsNeedAttention(setBooleanValueFrontWheel(frontWheel));
        SaveData.frontWheelsList.add(frontWheel);
        serialization(SaveData.frontWheelsList, FrontWheel.class);
    }

    /**
     * Метод создает новую КСА
     */
    public static void createKsa(TextField[] fields) {
        Ksa ksa = Ksa.builder()
                .total_Operating_Time((parseInt(fields[0].getText()) * 60) +
                        parseInt(fields[3].getText()))
                .starting_Left_Count(parseInt(fields[1].getText()))
                .starting_Right_Count(parseInt(fields[2].getText()))
                .total_Starting_Ksa_Count(parseInt(fields[4].getText()))
                .resource_Reserve_Before_25hours((parseInt(fields[6].getText()) * 60) +
                        parseInt(fields[5].getText()))
                .oilChange((parseInt(fields[8].getText()) * 60) +
                        parseInt(fields[7].getText()))
                .serialNumber(fields[9].getText())
                .aircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT)
                .build();
        ksa.setIsNeedAttention(BooleanValueSetter.setBooleanValueKsa(ksa));
        SaveData.ksaList.add(ksa);
        serialization(SaveData.ksaList, Ksa.class);
    }

    /**
     * Метод создает новый основной тормоз
     */
    public static void createMainBreak(TextField[] fields) {
        MainBreak mainBreak = MainBreak.builder()
                .totalLandings(parseInt(fields[0].getText()))
                .resource_Reserve_Replacement_Break(parseInt(fields[1].getText()))
                .resource_Reserve_Replacement_RotatingDisks(parseInt(fields[2].getText()))
                .resource_Reserve_Replacement_NonRotatingDisks(parseInt(fields[3].getText()))
                .resource_Reserve_Replacement_PressureDisk(parseInt(fields[4].getText()))
                .resource_Reserve_Replacement_ReferenceDisk(parseInt(fields[5].getText()))
                .serialNumber(fields[6].getText())
                .aircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT)
                .build();
        mainBreak.setIsNeedAttention(setBooleanValueMainBreak(mainBreak));
        SaveData.mainBreaksList.add(mainBreak);
        serialization(SaveData.mainBreaksList, MainBreak.class);
    }

    /**
     * Метод создает новое основное колесо
     */
    public static void createMainWheel(TextField field1, TextField field2, TextField field3) {
        MainWheel mainWheel = MainWheel.builder()
                .totalLandings(parseInt(field1.getText()))
                .resource_Reserve_Replacement_Wheel(parseInt(field2.getText()))
                .serialNumber(field3.getText())
                .aircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT)
                .build();
        mainWheel.setIsNeedAttention(setBooleanValueMainWheel(mainWheel));
        SaveData.mainWheelsList.add(mainWheel);
        serialization(SaveData.mainWheelsList, MainWheel.class);
    }

    /**
     * Метод создает новый планер
     */
    public static void createPlaner(TextField[] fields,
                                    DatePicker last_Flight_Date_Planer,
                                    DatePicker work_After_6months_Operation,
                                    DatePicker work_After_30Days_Parking) {
        Planer planer = Planer.builder()
                .serialNumber(fields[0].getText())
                .total_Landing_Count(parseInt(fields[1].getText()))
                .last_Flight_Date(Date.from(last_Flight_Date_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .date_Work_After_6months_Operation(Date.from(work_After_6months_Operation.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .date_Work_After_30days_Parking(Date.from(work_After_30Days_Parking.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .total_Operating_Time((parseInt(fields[2].getText()) * 60) +
                        parseInt(fields[3].getText()))
                .resource_Reserve_Before_100hours((parseInt(fields[4].getText()) * 60) +
                        parseInt(fields[5].getText()))
                .resource_Reserve_Before_200hours((parseInt(fields[6].getText()) * 60) +
                        parseInt(fields[7].getText()))
                .build();
        planer.setDays_Reserve_Before_30DaysParking(calculateDateInDays(Date.from(last_Flight_Date_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(work_After_30Days_Parking.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
        planer.setDays_Reserve_Before_6months_Operating(calculateDateInMonth(Date.from(work_After_6months_Operation.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
        planer.setIsNeedAttention(setBooleanValuePlaner(planer));
        SaveData.planersList.add(planer);
        serialization(SaveData.planersList, Planer.class);
    }

    /**
     * Методот создает новый цилиндр подкоса
     */
    public static void createCylinder(TextField[] fields) {
        CylinderOfRetractionExtension cylinder = CylinderOfRetractionExtension.builder()
                .totalLandings(parseInt(fields[0].getText()))
                .resource_Reserve_Before_First_Repair(parseInt(fields[1].getText()))
                .resource_Reserve_Before_Second_Repair(parseInt(fields[2].getText()))
                .resource_Reserve_Before_Replacement(parseInt(fields[3].getText()))
                .serialNumber(fields[4].getText())
                .aircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT)
                .build();
        cylinder.setIsNeedAttention(BooleanValueSetter.setBooleanValueCylinder(cylinder));
        SaveData.cylindersList.add(cylinder);
        serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
    }

    /**
     * Метод создает нового инженера(ак)
     */
    public static void createEngineer(ComboBox<Rank> listOfRanks, ChoiceBox<String> listOfLinks,
                                      TextField inputEngineerName, TextField inputNtzName,
                                      TableView<Engineer> tableEngineers) {
        Engineer engineer = Engineer.builder()
                .rank(listOfRanks.getValue())
                .fullName(inputEngineerName.getText())
                .link(listOfLinks.getSelectionModel().getSelectedItem())
                .ntzFullName(inputNtzName.getText())
                .build();
        SaveData.engineersList.add(engineer);
        tableEngineers.getItems().add(engineer);
        serialization(SaveData.engineersList, Engineer.class);
    }

    /**
     * Метод добавляет объект в List operatingHistory самолета, при добавлении летной смены в карточке самолета
     */
    public static void createOFlightOperating(Aircraft aircraft, TextField flightHours,
                                              TextField flightMinutes, TextField flightAndGroundHours,
                                              TextField flightAndGroundMinutes, TextField totalLandings,
                                              DatePicker lastFlightDate) {
        if (aircraft.getOperationHistory().size() == 5) {
            aircraft.getOperationHistory().remove(4);
        }
        OperatingHistory history = new OperatingHistory();
        history.setDateOperating(Date.from(lastFlightDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        history.setInformation(TextConstants.FLIGHT_DAY + TextConstants.OPERATING_FLY
                + flightHours.getText() + "ч. "
                + flightMinutes.getText() + "мин; "
                + TextConstants.OPERATING_FLY_GROUND
                + flightAndGroundHours.getText() + "ч. "
                + flightAndGroundMinutes.getText() + "мин; "
                + TextConstants.QUANTITY_LANDINGS
                + totalLandings.getText());
        aircraft.getOperationHistory().add(0, history);
    }

    /**
     * Метод добавляет объект в List operatingHistory самолета, при добавлении опробования двигателей в карточке самолета
     */
    public static void createTestOperating(Aircraft aircraft, TextField leftEngineOnGround,
                                           TextField rightEngineOnGround, TextField startingCountLeft,
                                           TextField startingCountRight, DatePicker dateTesting) {
        if (aircraft.getOperationHistory().size() == 5) {
            aircraft.getOperationHistory().remove(4);
        }
        OperatingHistory history = new OperatingHistory();
        history.setDateOperating(Date.from(dateTesting.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        history.setInformation(TextConstants.TEST_START + TextConstants.LEFT_OPERATING
                + leftEngineOnGround.getText() + "мин; "
                + TextConstants.QUANTITY_STARTING_LEFT
                + startingCountLeft.getText()
                + TextConstants.RIGHT_OPERATING
                + rightEngineOnGround.getText() + "мин; "
                + TextConstants.QUANTITY_STARTING_RIGHT
                + startingCountRight.getText());
        aircraft.getOperationHistory().add(0, history);
        log.info("Пользователь добавил наработку за газовку");
    }
}
