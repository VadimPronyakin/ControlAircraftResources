package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.openNewScene.OpenNewScene;


public class ListOfAggregatesController {

    @FXML
    private Button returnHomePage;

    @FXML
    void initialize() {
        OpenNewScene open = new OpenNewScene();
        returnHomePage.setOnAction(e -> open.openNewScene("/sample/fxmlFiles/sample.fxml", returnHomePage));
    }
}