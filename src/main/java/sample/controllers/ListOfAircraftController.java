package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.constants.TextConstants;
import sample.data.Aircraft;
import sample.data.Engineer;
import sample.data.SaveData;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;


public class ListOfAircraftController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnHomePage;

    @FXML
    private Button createNewAircraft;

    @FXML
    private Button changeAircraft;

    @FXML
    private Button deleteAircraft;

    @FXML
    private TableView<Aircraft> tableAircraft;

    @FXML
    private TableColumn<Aircraft, Aircraft> columnNumberAircraft;

    @FXML
    private TableColumn<Engineer, Engineer> columnEngineerAk;

    @FXML
    void initialize() {
        UpdateList.updateList(SaveData.aircraftList, tableAircraft, Aircraft.class, TextConstants.AIRCRAFT_TEXT);
        OpenNewScene open = new OpenNewScene();
        returnHomePage.setOnAction(event -> open.openNewScene("/sample/fxmlFiles/sample.fxml", returnHomePage));


    }
}

