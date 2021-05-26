package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.data.Aircraft;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;

import java.util.ArrayList;
import java.util.List;

import static sample.notification.NotificationAircraft.notifiesAircraft;
import static sample.openNewScene.OpenNewScene.showEditDialog;

public class PersonalAircraftDialogController {

    @FXML
    private TextField sideNumber;
    @FXML
    private TextField engineerForAircraft;
//    @FXML
//    private ListView<Engine> leftEngine;
//    @FXML
//    private ListView<Engine> rightEngine;
//    @FXML
//    private ListView<Ksa> KSA;
//    @FXML
//    private ListView<Planer> planer;
//    @FXML
//    private ListView<MainWheel> leftMainWheel;
//    @FXML
//    private ListView<MainWheel> rightMainWheel;
//    @FXML
//    private ListView<FrontWheel> leftFrontWheel;
//    @FXML
//    private ListView<FrontWheel> rightFrontWheel;
//    @FXML
//    private ListView<MainBreak> leftMainBreak;
//    @FXML
//    private ListView<MainBreak> rightMainBreak;
//    @FXML
//    private ListView<FrontBreak> leftFrontBreak;
//    @FXML
//    private ListView<FrontBreak> rightFrontBreak;
//    @FXML
//    private ListView<CylinderOfRetractionExtension> leftCylinder;
//    @FXML
//    private ListView<CylinderOfRetractionExtension> rightCylinder;
//    @FXML
//    private ListView<CylinderOfRetractionExtension> frontCylinder;
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

    List<Text> textOfAlarm = new ArrayList<>();

    private Aircraft aircraft;

    @FXML
    void initialize() {
        leftEngine.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateEngineDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createEngineDialog.fxml");
                controller.setButtonVisible("Выполнить работ");
                controller.setEngine(aircraft.getLeftEngine());
            }
        });
        rightEngine.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateEngineDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createEngineDialog.fxml");
                controller.setButtonVisible("Выполнить работ");
                controller.setEngine(aircraft.getRightEngine());
            }
        });
        ksaOnAircraft.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateKsaDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createKsaDialog.fxml");
                controller.setButtonVisible("Выполнить работ");
                controller.setKsa(aircraft.getKsa());
            }
        });
        rightMainWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainWheelDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createMainWheelDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setMainWheel(aircraft.getRightMainWheel());
            }
        });
        leftMainWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainWheelDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createMainWheelDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setMainWheel(aircraft.getLeftMainWheel());
            }
        });
        leftFrontWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontWheelDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createFrontWheelDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setFrontWheel(aircraft.getLeftFrontWheel());
            }
        });
        rightFrontWheel.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontWheelDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createFrontWheelDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setFrontWheel(aircraft.getRightFrontWheel());
            }
        });
        rightMainBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainBreakDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createMainBreakDialog.fxml");
                controller.setButtonVisible("Выполнить работ");
                controller.setMainBreak(aircraft.getRightMainBrake());
            }
        });
        leftMainBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainBreakDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createMainBreakDialog.fxml");
                controller.setButtonVisible("Выполнить работ");
                controller.setMainBreak(aircraft.getLeftMainBrake());
            }
        });
        leftFrontBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontBreakDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createFrontBreakDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setFrontBreak(aircraft.getLeftFrontBrake());
            }
        });
        rightFrontBreak.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontBreakDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createFrontBreakDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setFrontBreak(aircraft.getRightFrontBrake());
            }
        });
        leftCylinder.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateCylinderDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createCylinderDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setCylinder(aircraft.getLeftMainCylinder());
            }
        });
        rightCylinder.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateCylinderDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createCylinderDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setCylinder(aircraft.getRightMainCylinder());
            }
        });
        frontCylinder.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateCylinderDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createCylinderDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setCylinder(aircraft.getFrontCylinder());
            }
        });
        planer.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreatePlanerDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createPlanerDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setPlaner(aircraft.getPlaner());
            }
        });
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        sideNumber.setText(aircraft.getAircraftNumber());
        engineerForAircraft.setText(aircraft.getFullNameEngineer());
        leftEngine.textProperty().setValue(String.valueOf(aircraft.getLeftEngine()));
        rightEngine.textProperty().setValue(String.valueOf(aircraft.getRightEngine()));
        ksaOnAircraft.textProperty().setValue(String.valueOf(aircraft.getKsa()));
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
        textOfAlarm.add(alarmLeftEngine);
        textOfAlarm.add(alarmRightEngine);
        textOfAlarm.add(alarmKsa);
        textOfAlarm.add(alarmPlaner);
        textOfAlarm.add(alarm_Left_Main_Wheel);
        textOfAlarm.add(alarm_Right_Main_Wheel);
        textOfAlarm.add(alarm_Left_Front_Wheel);
        textOfAlarm.add(alarm_Right_Front_Wheel);
        textOfAlarm.add(alarm_Left_Main_Break);
        textOfAlarm.add(alarm_Right_Main_Break);
        textOfAlarm.add(alarm_Left_Front_Break);
        textOfAlarm.add(alarm_Right_Front_Break);
        textOfAlarm.add(alarmLeftCylinder);
        textOfAlarm.add(alarmRightCylinder);
        textOfAlarm.add(alarmFrontCylinder);
        notifiesAircraft(aircraft, textOfAlarm);
    }
}
//        labelTest.textProperty().setValue(String.valueOf(aircraft.getLeftEngine()));
