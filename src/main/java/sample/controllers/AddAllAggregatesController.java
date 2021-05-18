package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import lombok.Getter;
import sample.controllers.tab.*;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.openNewScene.OpenNewScene.openNewScene;

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
    private Text addAggregatesText;

    @FXML
    private Text changeAggregatesText;


    @FXML
    @Getter
    private KsaTabController ksaTabController;

    @FXML
    @Getter
    private FrontBreakTabController frontBreakTabController;

    @FXML
    @Getter
    private FrontWheelTabController frontWheelTabController;

    @FXML
    @Getter
    private MainBreakTabController mainBreakTabController;

    @FXML
    @Getter
    private MainWheelTabController mainWheelTabController;

    @FXML
    @Getter
    private CylinderTabController cylinderTabController;



    @FXML
    void initialize() {
        backToAggregatesList.setOnAction(e -> openNewScene("/sample/fxmlFiles/listOfAggregates.fxml", backToAggregatesList));
        backToLimitedList.setOnAction(e -> openNewScene("/sample/fxmlFiles/limitedResource.fxml", backToLimitedList));
    }

    @FXML
    public void setCurrentTab(int id) {
        Tab tab;
        switch (id) {
            case 1:
                tab = ksaTabButton;
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

    public void visibleText(Button text) {
        if (text.getText().equals("Добавить запись")) {
            addAggregatesText.setVisible(true);
        } else if (text.getText().equals("Изменить запись")) {
            changeAggregatesText.setVisible(true);
        } else if (text.getText().equals("+")) {
            addAggregatesText.setVisible(true);
        }
    }

}

