package sample.controllers.dialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import sample.constants.TextConstants;
import sample.controllers.AddAllAggregatesController;
import sample.controllers.ListOfAircraftController;
import sample.controllers.tab.FrontWheelTabController;
import sample.data.Aircraft;
import sample.data.Engineer;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;
import sample.write.WriteFile;

import static sample.openNewScene.OpenNewScene.openNewScene;

public class CreateAircraftDialogController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField sideNumber;

    @FXML
    private ChoiceBox<Engineer> listOfEngineers;

    @FXML
    private ChoiceBox<Engine> leftEngineList;

    @FXML
    private ChoiceBox<Engine> rightEngineList;

    @FXML
    private ChoiceBox<MainWheel> mainLeftWheelList;

    @FXML
    private ChoiceBox<CylinderOfRetractionExtension> mainLeftCylinderList;

    @FXML
    private ChoiceBox<FrontWheel> frontLeftWheelList;

    @FXML
    private ChoiceBox<MainBreak> mainRightBreakList;

    @FXML
    private ChoiceBox<FrontBreak> frontLeftBreakList;

    @FXML
    private ChoiceBox<Ksa> ksaList;

    @FXML
    private Button addMainLeftWheelList;

    @FXML
    private Button addFrontLeftWheelList;

    @FXML
    private Button addMainRightBreakList;

    @FXML
    private Button addFrontLeftBreakList;

    @FXML
    private Button addMainLeftCylinderList;

    @FXML
    private ChoiceBox<MainWheel> mainRightWheelList;

    @FXML
    private ChoiceBox<CylinderOfRetractionExtension> mainRightCylinderList;

    @FXML
    private ChoiceBox<FrontWheel> frontRightWheelList;

    @FXML
    private ChoiceBox<MainBreak> mainLeftBreakList;

    @FXML
    private ChoiceBox<FrontBreak> frontRightBreakList;

    @FXML
    private Button addMainRightWheelList;

    @FXML
    private Button addFrontRightWheelList;

    @FXML
    private Button addMainLeftBreakList;

    @FXML
    private Button addFrontRightBreakList;

    @FXML
    private Button addMainRightCylinderList;

    @FXML
    private Button createAircraft;

    @FXML
    private Button aircraftInformation;

    @FXML
    private ChoiceBox<CylinderOfRetractionExtension> frontCylinderList;

    @FXML
    private Button addFrontCylinderList;

    @FXML
    private Button addKsa;

    @FXML
    private Button addRightEngine;

    @FXML
    private Button addLeftEngine;

    @FXML
    private ChoiceBox<Planer> listOfPlaners;

    @FXML
    private Button createPlaner;

    @Setter
    private ListOfAircraftController listOfAircraftController;

    @FXML
    void initialize() {
        listOfEngineers.getItems().addAll(SaveData.engineersList);
        leftEngineList.getItems().addAll(SaveData.enginesList);
        rightEngineList.getItems().addAll(SaveData.enginesList);
        mainLeftWheelList.getItems().addAll(SaveData.mainWheelsList);
        mainLeftCylinderList.getItems().addAll(SaveData.cylindersList);
        frontLeftWheelList.getItems().addAll(SaveData.frontWheelsList);
        mainRightBreakList.getItems().addAll(SaveData.mainBreaksList);
        frontLeftBreakList.getItems().addAll(SaveData.frontBreaksList);
        ksaList.getItems().addAll(SaveData.ksaList);
        mainRightWheelList.getItems().addAll(SaveData.mainWheelsList);
        mainRightCylinderList.getItems().addAll(SaveData.cylindersList);
        frontRightWheelList.getItems().addAll(SaveData.frontWheelsList);
        mainLeftBreakList.getItems().addAll(SaveData.mainBreaksList);
        frontRightBreakList.getItems().addAll(SaveData.frontBreaksList);
        frontCylinderList.getItems().addAll(SaveData.cylindersList);
        listOfPlaners.getItems().addAll(SaveData.planersList);
        createAircraft.setOnAction(e -> {
            addAircraft();
            Stage stage = (Stage) createAircraft.getScene().getWindow();
            stage.close();
        });

        addMainLeftWheelList.setOnAction(e -> {
            AddAllAggregatesController ctrl = OpenNewScene.openNewScene("/sample/fxmlFiles/tab/mainWheelTab.fxml", addMainLeftWheelList);
            ctrl.getMainWheelTabController().visibleButton(addMainLeftWheelList);
        });
    }

    private void addAircraft() {
        Aircraft aircraft = Aircraft.builder()
                .aircraftNumber(Integer.parseInt(sideNumber.getText()))
                .iak(listOfEngineers.getSelectionModel().getSelectedItem())
                .ksa(ksaList.getSelectionModel().getSelectedItem())
                .leftEngine(leftEngineList.getSelectionModel().getSelectedItem())
                .rightEngine(rightEngineList.getSelectionModel().getSelectedItem())
                .frontCylinder(frontCylinderList.getSelectionModel().getSelectedItem())
                .rightMainCylinder(mainRightCylinderList.getSelectionModel().getSelectedItem())
                .leftMainCylinder(mainLeftCylinderList.getSelectionModel().getSelectedItem())
                .leftMainBrake(mainRightBreakList.getSelectionModel().getSelectedItem())
                .rightMainBrake(mainRightBreakList.getSelectionModel().getSelectedItem())
                .leftFrontBrake(frontLeftBreakList.getSelectionModel().getSelectedItem())
                .rightFrontBrake(frontRightBreakList.getSelectionModel().getSelectedItem())
                .leftMainWheel(mainLeftWheelList.getSelectionModel().getSelectedItem())
                .rightMainWheel(mainRightWheelList.getSelectionModel().getSelectedItem())
                .leftFrontWheel(frontLeftWheelList.getSelectionModel().getSelectedItem())
                .rightFrontWheel(frontRightWheelList.getSelectionModel().getSelectedItem())
                .planer(listOfPlaners.getSelectionModel().getSelectedItem())
                .fullNameEngineer(listOfEngineers.getSelectionModel().getSelectedItem().getFullName())
                .build();
        SaveData.aircraftList.add(aircraft);
        WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
        listOfAircraftController.updateTableAircraft();
    }
}
