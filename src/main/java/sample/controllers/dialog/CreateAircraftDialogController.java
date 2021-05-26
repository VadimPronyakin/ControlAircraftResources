package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import sample.Main;
import sample.controllers.ListOfAircraftController;
import sample.data.Aircraft;
import sample.data.Engineer;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;
import sample.write.WriteFile;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    @FXML
    private Button updateMainLeftCylinderList;

    @FXML
    private Button updateFrontLeftBreakList;

    @FXML
    private Button updateMainRightBreakList;

    @FXML
    private Button updateFrontLeftWheelList;

    @FXML
    private Button updateFrontCylinderList;

    @FXML
    private Button updateMainRightCylinderList;

    @FXML
    private Button updateFrontRightBreakList;

    @FXML
    private Button updateMainLeftBreakList;

    @FXML
    private Button updateFrontRightWheelList;

    @FXML
    private Button updateMainRightWheelList;

    @FXML
    private Button updateKsaList;

    @FXML
    private Button updateRightEngineList;

    @FXML
    private Button updateLeftEngineList;

    @FXML
    private Button updateListOfPlaners;

    @FXML
    private Button updateMainLeftWheelList;


    @Setter
    private ListOfAircraftController listOfAircraftController;

    @FXML
    void initialize() {
        updateLeftEngineList.setOnAction(e -> updateChoiceBoxes(1));
        updateRightEngineList.setOnAction(e -> updateChoiceBoxes(2));
        updateKsaList.setOnAction(e -> updateChoiceBoxes(3));
        updateMainLeftWheelList.setOnAction(e -> updateChoiceBoxes(4));
        updateMainRightWheelList.setOnAction(e -> updateChoiceBoxes(5));
        updateFrontLeftWheelList.setOnAction(e -> updateChoiceBoxes(6));
        updateFrontRightWheelList.setOnAction(e -> updateChoiceBoxes(7));
        updateMainLeftBreakList.setOnAction(e -> updateChoiceBoxes(8));
        updateMainRightBreakList.setOnAction(e -> updateChoiceBoxes(9));
        updateFrontLeftBreakList.setOnAction(e -> updateChoiceBoxes(10));
        updateFrontRightBreakList.setOnAction(e -> updateChoiceBoxes(11));
        updateMainLeftCylinderList.setOnAction(e -> updateChoiceBoxes(12));
        updateMainRightCylinderList.setOnAction(e -> updateChoiceBoxes(13));
        updateFrontCylinderList.setOnAction(e -> updateChoiceBoxes(14));
        updateListOfPlaners.setOnAction(e -> updateChoiceBoxes(15));

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
        addKsa.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createKsaDialog.fxml"));
        addLeftEngine.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createEngineDialog.fxml"));
        addRightEngine.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createEngineDialog.fxml"));
        addFrontCylinderList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createCylinderDialog.fxml"));
        addMainLeftCylinderList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createCylinderDialog.fxml"));
        addMainRightCylinderList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createCylinderDialog.fxml"));
        addFrontLeftBreakList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createFrontBreakDialog.fxml"));
        addFrontRightBreakList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createFrontBreakDialog.fxml"));
        addMainLeftBreakList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createMainBreakDialog.fxml"));
        addMainRightBreakList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createMainBreakDialog.fxml"));
        addFrontLeftWheelList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createFrontWheelDialog.fxml"));
        addFrontRightWheelList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createFrontWheelDialog.fxml"));
        addMainLeftWheelList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createMainWheelDialog.fxml"));
        addMainRightWheelList.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createMainWheelDialog.fxml"));
        createPlaner.setOnAction(e -> showDialog("/sample/fxmlFiles/dialog/createPlanerDialog.fxml"));
    }

    private void addAircraft() {
        Aircraft aircraft = Aircraft.builder()
                .aircraftNumber("б/н " + sideNumber.getText())
                .iak(listOfEngineers.getSelectionModel().getSelectedItem())
                .ksa(ksaList.getSelectionModel().getSelectedItem())
                .leftEngine(leftEngineList.getSelectionModel().getSelectedItem())
                .rightEngine(rightEngineList.getSelectionModel().getSelectedItem())
                .frontCylinder(frontCylinderList.getSelectionModel().getSelectedItem())
                .rightMainCylinder(mainRightCylinderList.getSelectionModel().getSelectedItem())
                .leftMainCylinder(mainLeftCylinderList.getSelectionModel().getSelectedItem())
                .leftMainBrake(mainLeftBreakList.getSelectionModel().getSelectedItem())
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

    public void showDialog(String url) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(url));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void updateChoiceBoxes(int id) {
        ChoiceBox box = null;
        List list = null;
        switch (id) {
            case 1:
               box = leftEngineList;
               list = SaveData.enginesList;
                break;
            case 2:
                box = rightEngineList;
                list = SaveData.enginesList;
                break;
            case 3:
                box = ksaList;
                list = SaveData.ksaList;
                break;
            case 4:
                box = mainLeftWheelList;
                list = SaveData.mainWheelsList;
                break;
            case 5:
                box = mainRightWheelList;
                list = SaveData.mainWheelsList;
                break;
            case 6:
                box = frontLeftWheelList;
                list = SaveData.frontWheelsList;
                break;
            case 7:
                box = frontRightWheelList;
                list = SaveData.frontWheelsList;
                break;
            case 8:
                box = mainLeftBreakList;
                list = SaveData.mainBreaksList;
                break;
            case 9:
                box = mainRightBreakList;
                list = SaveData.mainBreaksList;
                break;
            case 10:
                box = frontLeftBreakList;
                list = SaveData.frontBreaksList;
                break;
            case 11:
                box = frontRightBreakList;
                list = SaveData.frontBreaksList;
                break;
            case 12:
                box = mainLeftCylinderList;
                list = SaveData.cylindersList;
                break;
            case 13:
                box = mainRightCylinderList;
                list = SaveData.cylindersList;
                break;
            case 14:
                box = frontCylinderList;
                list = SaveData.cylindersList;
                break;
            case 15:
                box = listOfPlaners;
                list = SaveData.planersList;
                break;
        }
        box.getItems().clear();
        for (int i = 0; i < list.size(); i++) {
            box.getItems().addAll(list.get(i));
        }
    }


}
