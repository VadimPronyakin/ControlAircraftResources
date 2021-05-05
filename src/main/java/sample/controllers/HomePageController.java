package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.openNewScene.OpenNewScene;

public class HomePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button tableOfAircraft;

    @FXML
    private Button tableOfAggregates;

    @FXML
    private Button tableOfEngineers;

    @FXML
    void initialize() {
        OpenNewScene open = new OpenNewScene();
        tableOfAggregates.setOnAction(event -> {
            open.openNewScene("/sample/fxmlFiles/listOfAggregates.fxml", tableOfAggregates);

        });
        tableOfEngineers.setOnAction(e -> {
            open.openNewScene("/sample/fxmlFiles/listOfEngineers.fxml", tableOfEngineers);
        });
        tableOfAircraft.setOnAction(e -> {
            open.openNewScene("/sample/fxmlFiles/listOfAircraft.fxml", tableOfAircraft);
        });
    }
}

