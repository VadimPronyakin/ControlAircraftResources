package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static sample.openNewScene.OpenNewScene.openNewScene;

public class HomePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button limitedResource;

    @FXML
    private Button tableOfAircraft;

    @FXML
    private Button tableOfAggregates;

    @FXML
    private Button tableOfEngineers;

    @FXML
    void initialize() {

        tableOfAggregates.setOnAction(event -> openNewScene("/sample/fxmlFiles/listOfAggregates.fxml", tableOfAggregates));
        tableOfEngineers.setOnAction(e -> openNewScene("/sample/fxmlFiles/listOfEngineers.fxml", tableOfEngineers));
        tableOfAircraft.setOnAction(e -> openNewScene("/sample/fxmlFiles/listOfAircraft.fxml", tableOfAircraft));
        limitedResource.setOnAction(e -> openNewScene("/sample/fxmlFiles/limitedResource.fxml", limitedResource));
    }
}

