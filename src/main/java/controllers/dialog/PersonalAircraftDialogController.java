package controllers.dialog;

import constants.TextConstants;
import controllers.ListOfAircraftController;
import data.Aircraft;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.MainBreak;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import utils.UpdateNotification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import static builder.Builder.createOFlightOperating;
import static builder.Builder.createTestOperating;
import static service.Notification.notifiesAircraft;
import static service.OperationCalculator.calculatingResourcesAfterFlight;
import static service.OperationCalculator.calculatingResourcesAfterTestStart;
import static utils.UpdateAircraftComponents.*;
import static utils.Utils.openInformationWindow;
import static utils.Utils.showDialogWindow;

@Slf4j
public class PersonalAircraftDialogController {

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @FXML
    private Label sideNumber;
    @FXML
    private Label engineerForAircraft;
    @FXML
    private Text alarmLeftEngine;
    @FXML
    private Text alarmRightEngine;
    @FXML
    private Text alarmKsa;
    @FXML
    private Text alarmPlaner;
    @FXML
    private Text alarm_Left_Main_Wheel;
    @FXML
    private Text alarm_Right_Main_Wheel;
    @FXML
    private Text alarm_Left_Front_Wheel;
    @FXML
    private Text alarm_Right_Front_Wheel;
    @FXML
    private Text alarm_Left_Main_Break;
    @FXML
    private Text alarm_Right_Main_Break;
    @FXML
    private Text alarm_Left_Front_Break;
    @FXML
    private Text alarm_Right_Front_Break;
    @FXML
    private Text alarmLeftCylinder;
    @FXML
    private Text alarmRightCylinder;
    @FXML
    private Text alarmFrontCylinder;
    @FXML
    private Label leftEngine;
    @FXML
    private Label rightEngine;
    @FXML
    private Label ksaOnAircraft;
    @FXML
    private Label planer;
    @FXML
    private Label leftMainWheel;
    @FXML
    private Label rightMainWheel;
    @FXML
    private Label leftFrontWheel;
    @FXML
    private Label rightFrontWheel;
    @FXML
    private Label leftMainBreak;
    @FXML
    private Label rightMainBreak;
    @FXML
    private Label leftFrontBreak;
    @FXML
    private Label rightFrontBreak;
    @FXML
    private Label leftCylinder;
    @FXML
    private Label rightCylinder;
    @FXML
    private Label frontCylinder;
    @FXML
    private TextField flightHours;
    @FXML
    private TextField flightAndGroundHours;
    @FXML
    private TextField totalLandings;
    @FXML
    private Label orderNumber;
    @FXML
    private Button addFlightDay;
    @FXML
    private TextField flightMinutes;
    @FXML
    private TextField flightAndGroundMinutes;
    @FXML
    private TextField leftEngineOnGround;
    @FXML
    private TextField startingCountLeft;
    @FXML
    private TextField rightEngineOnGround;
    @FXML
    private TextField startingCountRight;
    @FXML
    private Button addTestingEngine;
    @FXML
    private DatePicker lastTestDate;
    @FXML
    private DatePicker lastFlightDate;
    @FXML
    private Label orderDate;
    @FXML
    private Label operatingInfo;
    @Setter
    private Stage currentStage;
    @Setter
    private ListOfAircraftController listOfAircraftController;
    private Aircraft aircraft;

    @FXML
    void initialize() {
        addFlightDay.setOnAction(e -> {
            if (aircraft.getLeftEngine() != null && aircraft.getRightEngine() != null && aircraft.getKsa() != null) {
                try {
                    calculatingResourcesAfterFlight(aircraft, flightHours,
                            flightMinutes, flightAndGroundHours,
                            flightAndGroundMinutes, totalLandings,
                            lastFlightDate);
                    updateReserveDays();
                    createOFlightOperating(aircraft, flightHours,
                            flightMinutes, flightAndGroundHours,
                            flightAndGroundMinutes, totalLandings,
                            lastFlightDate);
                    listOfAircraftController.updateTableAircraft();
                    updatingComponents(aircraft, flightHours,
                            flightMinutes, flightAndGroundHours,
                            flightAndGroundMinutes, totalLandings,
                            lastFlightDate);
                    Stage stage = (Stage) addFlightDay.getScene().getWindow();
                    stage.close();
                } catch (Exception exception) {
                    openInformationWindow(TextConstants.FILLING_OPERATION_TIME);
                    log.error("Пользователь остал какое либо поле пустым при заполнении данных о газовке");
                }
            } else {
                openInformationWindow(TextConstants.FLIGHT_WITHOUT_POWER_PLANT);
            }
        });
        addTestingEngine.setOnAction(e -> {
            if (aircraft.getLeftEngine() != null && aircraft.getRightEngine() != null && aircraft.getKsa() != null) {
                try {
                    createTestOperating(aircraft, leftEngineOnGround,
                            rightEngineOnGround, startingCountLeft,
                            startingCountRight, lastTestDate);
                    listOfAircraftController.updateTableAircraft();
                    calculatingResourcesAfterTestStart(aircraft, leftEngineOnGround,
                            rightEngineOnGround, startingCountLeft,
                            startingCountRight);
                    updatingPowerPlantAfterTestStarting(aircraft, leftEngineOnGround,
                            rightEngineOnGround, startingCountLeft,
                            startingCountRight);
                    Stage stage = (Stage) addFlightDay.getScene().getWindow();
                    stage.close();
                } catch (Exception exception) {
                    openInformationWindow(TextConstants.FILLING_OPERATION_TIME);
                    log.error("Пользователь остал какое либо поле пустым при заполнении данных о газовке");
                }
            } else {
                openInformationWindow(TextConstants.TEST_WITHOUT_POWER_PLANT);
            }
        });
        leftEngine.setOnMouseClicked(e -> {
            if (aircraft.getLeftEngine() != null) {
                if (e.getClickCount() == 2) {
                    CreateEngineDialogController controller = showDialogWindow(
                            "/fxmlFiles/dialog/createEngineDialog.fxml");
                    controller.setButtonVisible(false, false,
                            false, true,
                            true, true);
                    controller.setEngine(aircraft.getLeftEngine());
                    controller.setPersonalAircraftDialogController(this);
                    updateAfterClose();
                    log.info("Пользователь открыл диалоговое окно с информацией о левом двигателе в карточке ВС");
                }
            }
        });
        rightEngine.setOnMouseClicked(e -> {
            if (aircraft.getRightEngine() != null) {
                if (e.getClickCount() == 2) {
                    CreateEngineDialogController controller = showDialogWindow(
                            "/fxmlFiles/dialog/createEngineDialog.fxml");
                    controller.setButtonVisible(false, false,
                            false, true,
                            true, true);
                    controller.setEngine(aircraft.getRightEngine());
                    controller.setPersonalAircraftDialogController(this);
                    updateAfterClose();
                    log.info("Пользователь открыл диалоговое окно с информацией о правом двигателе в карточке ВС");
                }
            }
        });
        ksaOnAircraft.setOnMouseClicked(e -> {
            if (aircraft.getKsa() != null) {
                if (e.getClickCount() == 2) {
                    CreateKsaDialogController controller = showDialogWindow(
                            "/fxmlFiles/dialog/createKsaDialog.fxml");
                    controller.setButtonVisible(false, false,
                            false, true,
                            true, true);
                    controller.setKsa(aircraft.getKsa());
                    controller.setPersonalAircraftDialogController(this);
                    updateAfterClose();
                    log.info("Пользователь открыл диалоговое окно с информацией о КСА в карточке ВС");
                }
            }
        });
        rightMainWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainWheelDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createMainWheelDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setMainWheel(aircraft.getRightMainWheel());
                log.info("Пользователь открыл диалоговое окно с информацией о правом основном колесе в карточке ВС");

            }
        });
        leftMainWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainWheelDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createMainWheelDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setMainWheel(aircraft.getLeftMainWheel());
                log.info("Пользователь открыл диалоговое окно с информацией о левом основном колесе в карточке ВС");
            }
        });
        leftFrontWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontWheelDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createFrontWheelDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setFrontWheel(aircraft.getLeftFrontWheel());
                log.info("Пользователь открыл диалоговое окно с информацией о левом переднем колесе в карточке ВС");
            }
        });
        rightFrontWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontWheelDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createFrontWheelDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setFrontWheel(aircraft.getRightFrontWheel());
                log.info("Пользователь открыл диалоговое окно с информацией о правом переднем колесе в карточке ВС");
            }
        });
        rightMainBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainBreakDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createMainBreakDialog.fxml");
                controller.setButtonVisible(false, false,
                        false, true,
                        true, true);
                controller.setMainBreak(aircraft.getRightMainBrake());
                controller.setPersonalAircraftDialogController(this);
                updateAfterClose();
                log.info("Пользователь открыл диалоговое окно с информацией о правом основном тормозе в карточке ВС");
            }
        });
        leftMainBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainBreakDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createMainBreakDialog.fxml");
                controller.setButtonVisible(false, false,
                        false, true,
                        true, true);
                controller.setMainBreak(aircraft.getLeftMainBrake());
                controller.setPersonalAircraftDialogController(this);
                updateAfterClose();
                log.info("Пользователь открыл диалоговое окно с информацией о левом основном тормозе в карточке ВС");
            }
        });
        leftFrontBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontBreakDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createFrontBreakDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setFrontBreak(aircraft.getLeftFrontBrake());
                log.info("Пользователь открыл диалоговое окно с информацией о левом переднем тормозе в карточке ВС");
            }
        });
        rightFrontBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontBreakDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createFrontBreakDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setFrontBreak(aircraft.getRightFrontBrake());
                log.info("Пользователь открыл диалоговое окно с информацией о правом переднем тормозе в карточке ВС");
            }
        });
        leftCylinder.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateCylinderDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createCylinderDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setCylinder(aircraft.getLeftMainCylinder());
                log.info("Пользователь открыл диалоговое окно с информацией о левом цилиндре подкоса в карточке ВС");
            }
        });
        rightCylinder.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateCylinderDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createCylinderDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setCylinder(aircraft.getRightMainCylinder());
                log.info("Пользователь открыл диалоговое окно с информацией о правом цилиндре подкоса в карточке ВС");
            }
        });
        frontCylinder.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateCylinderDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createCylinderDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setCylinder(aircraft.getFrontCylinder());
                log.info("Пользователь открыл диалоговое окно с информацией о переднем цилиндре подкоса в карточке ВС");
            }
        });
        planer.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreatePlanerDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createPlanerDialog.fxml");
                controller.setButtonVisible(false, true,
                        true, true,
                        true, true,
                        true);
                controller.setPlaner(aircraft.getPlaner());
                controller.setPersonalAircraftDialogController(this);
                updateAfterClose();
                log.info("Пользователь открыл диалоговое окно с информацией о планере в карточке ВС");
            }
        });
    }

    @FXML
    private void updateAfterClose() {
        currentStage.setOnCloseRequest(event -> {
            listOfAircraftController.updateTableAircraft();
        });
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        sideNumber.setText(aircraft.getAircraftNumber());
        orderNumber.textProperty().setValue(String.valueOf(aircraft.getOrderNumber()));
        engineerForAircraft.setText(aircraft.getFullNameEngineer());
        if (aircraft.getLeftEngine() != null) {
            leftEngine.textProperty().setValue(String.valueOf(aircraft.getLeftEngine()));
        } else {
            leftEngine.setText(TextConstants.ENGINE_NOT_INSTALLED);
        }
        if (aircraft.getRightEngine() != null) {
            rightEngine.textProperty().setValue(String.valueOf(aircraft.getRightEngine()));
        } else {
            rightEngine.setText(TextConstants.ENGINE_NOT_INSTALLED);
        }
        if (aircraft.getKsa() != null) {
            ksaOnAircraft.textProperty().setValue(String.valueOf(aircraft.getKsa()));
        } else {
            ksaOnAircraft.setText(TextConstants.KSA_NOT_INSTALLED);
        }
        planer.textProperty().setValue(String.valueOf(aircraft.getPlaner()));
        leftMainWheel.textProperty().setValue(String.valueOf(aircraft.getLeftMainWheel()));
        rightMainWheel.textProperty().setValue(String.valueOf(aircraft.getRightMainWheel()));
        leftFrontWheel.textProperty().setValue(String.valueOf(aircraft.getLeftFrontWheel()));
        rightFrontWheel.textProperty().setValue(String.valueOf(aircraft.getRightFrontWheel()));
        leftMainBreak.textProperty().setValue(String.valueOf(aircraft.getLeftMainBrake()));
        rightMainBreak.textProperty().setValue(String.valueOf(aircraft.getRightMainBrake()));
        leftFrontBreak.textProperty().setValue(String.valueOf(aircraft.getLeftFrontBrake()));
        rightFrontBreak.textProperty().setValue(String.valueOf(aircraft.getRightFrontBrake()));
        leftCylinder.textProperty().setValue(String.valueOf(aircraft.getLeftMainCylinder()));
        rightCylinder.textProperty().setValue(String.valueOf(aircraft.getRightMainCylinder()));
        frontCylinder.textProperty().setValue(String.valueOf(aircraft.getFrontCylinder()));
        orderDate.textProperty().setValue(dateFormat.format(aircraft.getOrderDate()));
        fillsOperating();
        Text[] textOfAlarm = new Text[]{alarmLeftEngine, alarmRightEngine,
                alarmKsa, alarmPlaner,
                alarm_Left_Main_Wheel, alarm_Right_Main_Wheel,
                alarm_Left_Front_Wheel, alarm_Right_Front_Wheel,
                alarm_Left_Main_Break, alarm_Right_Main_Break,
                alarm_Left_Front_Break, alarm_Right_Front_Break,
                alarmLeftCylinder, alarmRightCylinder,
                alarmFrontCylinder};
        notifiesAircraft(aircraft, textOfAlarm);
    }

    /**
     * Метод обновляет видимость надписей - сигнализаторов , которые находятся в карточке самолета напротив двигателей
     * в зависимости от того, как меняется значение переменной boolean isNeedAttention
     */
    public void updateNotificationEngine(Engine engine) {
        UpdateNotification.updateNotificationEngine(engine, aircraft,
                alarmLeftEngine, alarmRightEngine);
    }

    /**
     * Метод обновляет видимость надписи - сигнализатора , которая находится в карточке самолета напротив КСА
     * в зависимости от того, как меняется значение переменной boolean isNeedAttention
     */
    public void updateNotificationKsa(Ksa ksa) {
        UpdateNotification.updateNotificationKsa(ksa, aircraft, alarmKsa);
    }

    /**
     * Метод обновляет видимость надписи - сигнализатора , которая находится в карточке самолета напротив планера
     * в зависимости от того, как меняется значение переменной boolean isNeedAttention
     */
    public void updateNotificationPlaner(Planer planer) {
        UpdateNotification.updateNotificationPlaner(planer, aircraft, alarmPlaner);
    }

    /**
     * Метод обновляет видимость надписей - сигнализаторов , которые находятся в карточке самолета напротив основных тормозов
     * в зависимости от того, как меняется значение переменной boolean isNeedAttention
     */
    public void updateNotificationMainBreak(MainBreak mainBreak) {
        UpdateNotification.updateNotificationMainBreak(mainBreak, aircraft, alarm_Left_Main_Break, alarm_Right_Main_Break);
    }

    private void fillsOperating() {
        if (aircraft.getOperationHistory().size() != 0) {
            String history = aircraft.getOperationHistory().stream().map(h -> dateFormat.format(h.getDateOperating())
                    + h.getInformation()).collect(Collectors.joining("\n"));
            operatingInfo.textProperty().setValue(history);
            operatingInfo.setLineSpacing(7.0);
        } else {
            operatingInfo.setAlignment(Pos.CENTER);
            operatingInfo.textProperty().setValue(TextConstants.NON_OPERATING_IN_HISTORY);
        }
    }
}

