package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import sample.openNewScene.OpenNewScene;


public class ListOfLimitedController {

    @FXML
    private Button returnHomePage;

    @FXML
    private Tab listMainBreakTabButton;

    @FXML
    private Tab listFrontBreakTabButton;

    @FXML
    private Tab listMainWheelsTabButton;

    @FXML
    private Tab listFrontWheelsTabButton;

    @FXML
    private Tab listCylindersTabButton;

    @FXML
    void initialize() {
        OpenNewScene open = new OpenNewScene();
        returnHomePage.setOnAction(e -> open.openNewScene("/sample/fxmlFiles/sample.fxml", returnHomePage));
    }
}

