package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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

import static sample.openNewScene.OpenNewScene.openNewScene;


public class ListFrontWheelTabController {

    @FXML
    private Button createFrontWheel;

    @FXML
    private Button changeFrontWheel;

    @FXML
    private Button deleteFrontWheel;

    @FXML
    private TableView<FrontWheel> tableFrontWheels;

    @FXML
    private TableColumn<FrontWheel, FrontWheel> columnFrontWheel;

    @FXML
    private TableColumn<String, String> columnInstalledFrontWheel;

    @FXML
    void initialize() {

        UpdateList.updateList(SaveData.frontWheelsList, tableFrontWheels, FrontWheel.class, TextConstants.MAIN_BREAK_TEXT);
        columnFrontWheel.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledFrontWheel.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        createFrontWheel.setOnAction(e -> {
           AddAllAggregatesController ctrl = OpenNewScene.openNewScene("/sample/fxmlFiles/addAggregates.fxml", createFrontWheel);
           ctrl.setCurrentTab(6);
           ctrl.visibleText(createFrontWheel);
           ctrl.getFrontWheelTabController().visibleButton(createFrontWheel);
        });
        changeFrontWheel.setOnAction(e -> {
            AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", changeFrontWheel);
            ctrl.getFrontWheelTabController().setFrontWheel(tableFrontWheels.getSelectionModel().getSelectedItem().getSerialNumber());
            ctrl.setCurrentTab(6);
            ctrl.visibleText(changeFrontWheel);
            ctrl.getFrontWheelTabController().visibleButton(changeFrontWheel);
        });
        tableFrontWheels.setRowFactory(tv -> {
            TableRow<FrontWheel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", changeFrontWheel);
                    ctrl.getFrontWheelTabController().setFrontWheel(tableFrontWheels.getSelectionModel().getSelectedItem().getSerialNumber());
                    ctrl.setCurrentTab(6);
                }
            });
            return row;
        });
        deleteFrontWheel.setOnAction(e -> DeleteObject.delete(SaveData.frontWheelsList, tableFrontWheels, FrontWheel.class));
    }
}

