package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.openNewScene.OpenNewScene;


public class ListOfLimitedController {

    @FXML
    private Button returnHomePage;

    @FXML
    void initialize() {

        returnHomePage.setOnAction(e -> OpenNewScene.openNewScene("/sample/fxmlFiles/sample.fxml", returnHomePage));
    }
}

