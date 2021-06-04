package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static utils.Utils.openNewScene;


public class ListOfLimitedController {

    @FXML
    private Button returnHomePage;

    @FXML
    void initialize() {

        returnHomePage.setOnAction(e -> openNewScene("/fxmlFiles/sample.fxml", returnHomePage));
    }
}

