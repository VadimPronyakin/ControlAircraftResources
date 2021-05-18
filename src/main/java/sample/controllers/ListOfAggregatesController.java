package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;
import sample.controllers.tab.ListAllEnginesTabController;
import sample.controllers.tab.ListAllKsaTabController;
import sample.openNewScene.OpenNewScene;


public class ListOfAggregatesController {

    @FXML
    private Button returnHomePage;

    @FXML
    @Getter
    private ListAllEnginesTabController listAllEnginesTabController;

    @FXML
    @Getter
    private ListAllKsaTabController listAllKsaTabController;

    @FXML
    void initialize() {

        returnHomePage.setOnAction(e -> OpenNewScene.openNewScene("/sample/fxmlFiles/sample.fxml", returnHomePage));
    }
}