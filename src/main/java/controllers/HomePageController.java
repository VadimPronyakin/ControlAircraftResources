package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static utils.Utils.openNewScene;


public class HomePageController {

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

        tableOfAggregates.setOnAction(event -> openNewScene("/fxmlFiles/listOfAggregates.fxml", tableOfAggregates));
        tableOfEngineers.setOnAction(e -> openNewScene("/fxmlFiles/listOfEngineers.fxml", tableOfEngineers));
        tableOfAircraft.setOnAction(e -> openNewScene("/fxmlFiles/listOfAircraft.fxml", tableOfAircraft));
        limitedResource.setOnAction(e -> openNewScene("/fxmlFiles/limitedResource.fxml", limitedResource));
    }
}

