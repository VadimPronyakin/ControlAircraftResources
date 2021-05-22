package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import sample.Main;
import sample.controllers.ListOfAircraftController;
import sample.data.Aircraft;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sample.notification.NotificationAircraft.notifies;

public class PersonalAircraftDialogController {

    @FXML
    private TextField sideNumber;

    @FXML
    private TextField engineerForAircraft;

    @FXML
    private ListView<Engine> leftEngine;

    @FXML
    private ListView<Engine> rightEngine;

    @FXML
    private ListView<Ksa> KSA;

    @FXML
    private ListView<Planer> planer;

    @FXML
    private ListView<MainWheel> leftMainWheel;

    @FXML
    private ListView<MainWheel> rightMainWheel;

    @FXML
    private ListView<FrontWheel> leftFrontWheel;

    @FXML
    private ListView<FrontWheel> rightFrontWheel;

    @FXML
    private ListView<MainBreak> leftMainBreak;

    @FXML
    private ListView<MainBreak> rightMainBreak;

    @FXML
    private ListView<FrontBreak> leftFrontBreak;

    @FXML
    private ListView<FrontBreak> rightFrontBreak;

    @FXML
    private ListView<CylinderOfRetractionExtension> leftCylinder;

    @FXML
    private ListView<CylinderOfRetractionExtension> rightCylinder;

    @FXML
    private ListView<CylinderOfRetractionExtension> frontCylinder;

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

    @Setter
    private ListOfAircraftController listOfAircraftController;

    private Aircraft aircraft;

    List<Text> textOfAlarm = new ArrayList<>();


    @FXML
    void initialize() {
        leftEngine.setOnMouseClicked(this::showLeftEngineEditDialog);
        rightEngine.setOnMouseClicked(this::showRightEngineEditDialog);
        KSA.setOnMouseClicked(this::showKsaEditDialog);

    }

    public CreateEngineDialogController showLeftEngineEditDialog(javafx.scene.input.MouseEvent e) {
        if (e.getClickCount() == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createEngineDialog.fxml"));
                Pane page = loader.load();
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                CreateEngineDialogController controller = loader.getController();
                controller.setPersonalAircraftDialogController(this);
                controller.setEngine(aircraft.getLeftEngine());
                controller.setButtonVisible("Выполнить работы");
                dialogStage.show();
                return controller;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        sideNumber.setText(aircraft.getAircraftNumber());
        engineerForAircraft.setText(aircraft.getFullNameEngineer());
        leftEngine.getItems().add(aircraft.getLeftEngine());
        rightEngine.getItems().add(aircraft.getRightEngine());
        KSA.getItems().add(aircraft.getKsa());
        planer.getItems().add(aircraft.getPlaner());
        leftMainWheel.getItems().add(aircraft.getLeftMainWheel());
        rightMainWheel.getItems().add(aircraft.getRightMainWheel());
        leftFrontWheel.getItems().add(aircraft.getLeftFrontWheel());
        rightFrontWheel.getItems().add(aircraft.getRightFrontWheel());
        leftMainBreak.getItems().add(aircraft.getLeftMainBrake());
        rightMainBreak.getItems().add(aircraft.getRightMainBrake());
        leftFrontBreak.getItems().add(aircraft.getLeftFrontBrake());
        rightFrontBreak.getItems().add(aircraft.getRightFrontBrake());
        leftCylinder.getItems().add(aircraft.getLeftMainCylinder());
        rightCylinder.getItems().add(aircraft.getRightMainCylinder());
        frontCylinder.getItems().add(aircraft.getFrontCylinder());
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
        notifies(aircraft, textOfAlarm);
    }

//    public CreateEngineDialogController showEngineDialog() {
//        try {
//            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createEngineDialog.fxml"));
//            Pane page = loader.load();
//            Stage dialogStage = new Stage();
//            dialogStage.initModality(Modality.APPLICATION_MODAL);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//            CreateEngineDialogController controller = loader.getController();
//            controller.setPersonalAircraftDialogController(this);
//            dialogStage.show();
//            return controller;
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//        return null;
//    }


    public CreateEngineDialogController showRightEngineEditDialog(javafx.scene.input.MouseEvent e) {
        if (e.getClickCount() == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createEngineDialog.fxml"));
                Pane page = loader.load();
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                CreateEngineDialogController controller = loader.getController();
                controller.setPersonalAircraftDialogController(this);
                controller.setEngine(aircraft.getRightEngine());
                controller.setButtonVisible("Выполнить работы");
                dialogStage.show();
                return controller;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public CreateKsaDialogController showKsaEditDialog(javafx.scene.input.MouseEvent e) {
        if (e.getClickCount() == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createKsaDialog.fxml"));
                Pane page = loader.load();
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                CreateKsaDialogController controller = loader.getController();
                controller.setPersonalAircraftDialogController(this);
                controller.setKsa(aircraft.getKsa());
                controller.setButtonVisible("Выполнить работы");
                dialogStage.show();
                return controller;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}

