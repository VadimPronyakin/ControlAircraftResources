package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import sample.calculating.CalculatingOperation;
import sample.controllers.ListOfAircraftController;
import sample.data.Aircraft;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.MainBreak;
import sample.update.UpdateAircraftComponents;
import sample.update.UpdateNotification;

import static sample.notification.Notification.notifiesAircraft;
import static sample.openNewScene.OpenNewScene.showEditDialogDoubleClick;

public class PersonalAircraftDialogController {

    @FXML
    private TextField sideNumber;
    @FXML
    private TextField engineerForAircraft;
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
    private TextField flightAndEarthHours;
    @FXML
    private TextField totalLandings;
    @FXML
    private Button addFlightDay;
    @FXML
    private TextField flightMinutes;
    @FXML
    private TextField flightAndEarthMinutes;
    private Aircraft aircraft;

    @Setter
    private ListOfAircraftController listOfAircraftController;

    @FXML
    void initialize() {
        addFlightDay.setOnAction(e -> {
            CalculatingOperation.calculatingResourcesAfterFlight(aircraft, flightHours,
                    flightMinutes, flightAndEarthHours,
                    flightAndEarthMinutes, totalLandings);
            UpdateAircraftComponents.updatingComponents(aircraft, flightHours,
                    flightMinutes, flightAndEarthHours,
                    flightAndEarthMinutes, totalLandings);
            listOfAircraftController.updateTableAircraft();
            Stage stage = (Stage) addFlightDay.getScene().getWindow();
            stage.close();
        });
        leftEngine.setOnMouseClicked(e -> {
            if (aircraft.getLeftEngine() != null) {
                if (e.getClickCount() == 2) {
                    CreateEngineDialogController controller = showEditDialogDoubleClick(e,
                            "/fxmlFiles/dialog/createEngineDialog.fxml");
                    controller.setButtonVisible("Выполнить работ");
                    controller.setEngine(aircraft.getLeftEngine());
                    controller.setPersonalAircraftDialogController(this);
                }
            }
        });
        rightEngine.setOnMouseClicked(e -> {
            if (aircraft.getRightEngine() != null) {
                if (e.getClickCount() == 2) {
                    CreateEngineDialogController controller = showEditDialogDoubleClick(e,
                            "/fxmlFiles/dialog/createEngineDialog.fxml");
                    controller.setButtonVisible("Выполнить работ");
                    controller.setEngine(aircraft.getRightEngine());
                    controller.setPersonalAircraftDialogController(this);
                }
            }
        });
        ksaOnAircraft.setOnMouseClicked(e -> {
            if (aircraft.getKsa() != null) {
                if (e.getClickCount() == 2) {
                    CreateKsaDialogController controller = showEditDialogDoubleClick(e,
                            "/fxmlFiles/dialog/createKsaDialog.fxml");
                    controller.setButtonVisible("Выполнить работ");
                    controller.setKsa(aircraft.getKsa());
                    controller.setPersonalAircraftDialogController(this);
                }
            }
        });
        rightMainWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainWheelDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createMainWheelDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setMainWheel(aircraft.getRightMainWheel());

            }
        });
        leftMainWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainWheelDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createMainWheelDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setMainWheel(aircraft.getLeftMainWheel());
            }
        });
        leftFrontWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontWheelDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createFrontWheelDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setFrontWheel(aircraft.getLeftFrontWheel());
            }
        });
        rightFrontWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontWheelDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createFrontWheelDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setFrontWheel(aircraft.getRightFrontWheel());
            }
        });
        rightMainBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainBreakDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createMainBreakDialog.fxml");
                controller.setButtonVisible("Выполнить работ");
                controller.setMainBreak(aircraft.getRightMainBrake());
                controller.setPersonalAircraftDialogController(this);
            }
        });
        leftMainBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainBreakDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createMainBreakDialog.fxml");
                controller.setButtonVisible("Выполнить работ");
                controller.setMainBreak(aircraft.getLeftMainBrake());
                controller.setPersonalAircraftDialogController(this);
            }
        });
        leftFrontBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontBreakDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createFrontBreakDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setFrontBreak(aircraft.getLeftFrontBrake());
            }
        });
        rightFrontBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontBreakDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createFrontBreakDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setFrontBreak(aircraft.getRightFrontBrake());
            }
        });
        leftCylinder.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateCylinderDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createCylinderDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setCylinder(aircraft.getLeftMainCylinder());
                controller.setPersonalAircraftDialogController(this);
            }
        });
        rightCylinder.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateCylinderDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createCylinderDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setCylinder(aircraft.getRightMainCylinder());
                controller.setPersonalAircraftDialogController(this);
            }
        });
        frontCylinder.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateCylinderDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createCylinderDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setCylinder(aircraft.getFrontCylinder());
                controller.setPersonalAircraftDialogController(this);
            }
        });
        planer.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreatePlanerDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createPlanerDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setPlaner(aircraft.getPlaner());
                controller.setPersonalAircraftDialogController(this);
            }
        });
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        sideNumber.setText(aircraft.getAircraftNumber());
        engineerForAircraft.setText(aircraft.getFullNameEngineer());
        if (aircraft.getLeftEngine() != null) {
            leftEngine.textProperty().setValue(String.valueOf(aircraft.getLeftEngine()));
        } else {
            leftEngine.setText("Двигатель не установлен на самолет");
        }
        if (aircraft.getRightEngine() != null) {
            rightEngine.textProperty().setValue(String.valueOf(aircraft.getRightEngine()));
        } else {
            rightEngine.setText("Двигатель не установлен на самолет");
        }
        if (aircraft.getKsa() != null) {
            ksaOnAircraft.textProperty().setValue(String.valueOf(aircraft.getKsa()));
        } else {
            ksaOnAircraft.setText("КСА не установлена на самолете");
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
}

