package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.constants.TextConstants;
import sample.controllers.AddAllAggregatesController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.limitedResource.*;
import sample.delete.DeleteObject;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;


public class ListMainWheelsTabController {

    @FXML
    private Button createMainWheel;

    @FXML
    private Button changeMainWheel;

    @FXML
    private Button deleteMainWheel;

    @FXML
    private TableView<MainWheel> tableMainWheels;

    @FXML
    private TableColumn<MainWheel, MainWheel> columnMainWheel;

    @FXML
    private TableColumn<Aircraft, Aircraft> columnInstalledMainWheel;

    @FXML
    void initialize() {
        OpenNewScene open = new OpenNewScene();
        UpdateList.updateList(SaveData.mainWheelsList, tableMainWheels, MainWheel.class, TextConstants.MAIN_BREAK_TEXT);
        columnMainWheel.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledMainWheel.setCellValueFactory(new PropertyValueFactory<>("aircraftNumber"));
        createMainWheel.setOnAction(e -> {
            AddAllAggregatesController ctrl = open.openNewScene("/sample/fxmlFiles/addAggregates.fxml", createMainWheel);
            ctrl.setCurrentTab(5);
        });
        deleteMainWheel.setOnAction(e -> DeleteObject.delete(SaveData.mainWheelsList, tableMainWheels, MainWheel.class));
    }
}

