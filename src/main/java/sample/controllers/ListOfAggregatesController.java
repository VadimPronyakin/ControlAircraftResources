package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import sample.openNewScene.OpenNewScene;

public class ListOfAggregatesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnHomePage;

    @FXML
    private Tab listAllEngines;

    @FXML
    private Button createNewEngineer;

    @FXML
    private Button changeEngineer;

    @FXML
    private Button deleteEngineer;

    @FXML
    private Tab listAllKsa;

    @FXML
    private Button createNewKsa;

    @FXML
    private Button changeKsa;

    @FXML
    private Button deleteKsa;

    @FXML
    private Tab listOfLimitedResource;

    @FXML
    private Button createNewLimitedObject;

    @FXML
    private Button changeLimitedObject;

    @FXML
    private Button deleteLimitedObject;

    @FXML
    void initialize() {
        OpenNewScene open = new OpenNewScene();
        returnHomePage.setOnAction(event -> {
            open.openNewScene("/sample/fxmlFiles/sample.fxml", returnHomePage);
        });
    }
}

