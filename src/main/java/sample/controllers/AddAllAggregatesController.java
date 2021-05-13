package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import sample.openNewScene.OpenNewScene;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAllAggregatesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backToAggregatesList;

    @FXML
    private Button backToLimitedList;

    @FXML
    private TabPane tapAggregates;

    @FXML
    private Tab ksaTabButton;

    @FXML
    private Tab engineTabButton;

    @FXML
    private Tab mainBreakTabButton;

    @FXML
    private Tab frontBreakTabButton;

    @FXML
    private Tab mainWheelTabButton;

    @FXML
    private Tab frontWheelTabButton;

    @FXML
    private Tab cylinderTabButton;

    @FXML
    void initialize() {
        OpenNewScene open = new OpenNewScene();
        backToAggregatesList.setOnAction(e -> open.openNewScene("/sample/fxmlFiles/listOfAggregates.fxml", backToAggregatesList));
        backToLimitedList.setOnAction(e -> open.openNewScene("/sample/fxmlFiles/limitedResource.fxml", backToLimitedList));
    }

    @FXML
    public void setCurrentTab(int id) {
        Tab tab;
        switch (id) {
            case 1:
                tab = ksaTabButton;
                break;
            case 2:
                tab = engineTabButton;
                break;
            case 3:
                tab = mainBreakTabButton;
                break;
            case 4:
                tab = frontBreakTabButton;
                break;
            case 5:
                tab = mainWheelTabButton;
                break;
            case 6:
                tab = frontWheelTabButton;
                break;
            case 7:
                tab = cylinderTabButton;
                break;
            default:
                tab = ksaTabButton;
        }
        tapAggregates.getSelectionModel().select(tab);
    }

}

