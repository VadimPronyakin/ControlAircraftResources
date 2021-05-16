package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.constants.TextConstants;
import sample.controllers.AddAllAggregatesController;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.limitedResource.FrontBreak;
import sample.data.enums.TypesOfWorks;
import sample.delete.DeleteObject;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;
import sample.write.WriteFile;

import static sample.openNewScene.OpenNewScene.openNewScene;


public class ListFrontBreakTabController {

    @FXML
    private Button createFrontBreak;

    @FXML
    private Button changeFrontBreak;

    @FXML
    private Button deleteFrontBreak;

    @FXML
    private TableView<FrontBreak> tableFrontBreaks;

    @FXML
    private TableColumn<FrontBreak, FrontBreak> columnFrontBreak;

    @FXML
    private TableColumn<String, String> columnInstalledFrontBreak;

    @FXML
    private Button makeWorkFrontBreak;

    @FXML
    private ComboBox<TypesOfWorks> listOfWorksFrontBreak;

    @FXML
    void initialize() {
        UpdateList.updateList(SaveData.frontBreaksList, tableFrontBreaks, FrontBreak.class, TextConstants.MAIN_BREAK_TEXT);
        columnFrontBreak.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledFrontBreak.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        listOfWorksFrontBreak.getItems().addAll(TypesOfWorks.FIRST_REPAIR_FRONT_BREAK);
        createFrontBreak.setOnAction(e -> {
            AddAllAggregatesController ctrl = OpenNewScene.openNewScene("/sample/fxmlFiles/addAggregates.fxml", createFrontBreak);
            ctrl.setCurrentTab(4);
            ctrl.visibleText(createFrontBreak);
            ctrl.getFrontBreakTabController().visibleButton(createFrontBreak);
        });
        changeFrontBreak.setOnAction(e -> {
            AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", changeFrontBreak);
            ctrl.getFrontBreakTabController().setFrontBreak(tableFrontBreaks.getSelectionModel().getSelectedItem().getSerialNumber());
            ctrl.setCurrentTab(4);
            ctrl.visibleText(changeFrontBreak);
            ctrl.getFrontBreakTabController().visibleButton(changeFrontBreak);
        });
        tableFrontBreaks.setRowFactory(tv -> {
            TableRow<FrontBreak> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", changeFrontBreak);
                    ctrl.getFrontBreakTabController().setFrontBreak(tableFrontBreaks.getSelectionModel().getSelectedItem().getSerialNumber());
                    ctrl.setCurrentTab(4);
                }
            });
            return row;
        });
        deleteFrontBreak.setOnAction(e -> DeleteObject.delete(SaveData.frontBreaksList, tableFrontBreaks, FrontBreak.class));
        makeWorkFrontBreak.setOnAction(e -> doWorksFrontBreak());
    }

    void doWorksFrontBreak() {
        try {
            FrontBreak frontBreak = tableFrontBreaks.getSelectionModel().getSelectedItem();
            frontBreak.setResource_Reserve_Before_First_Repair(listOfWorksFrontBreak.getSelectionModel().getSelectedItem().getResource());
            WriteFile.serialization(SaveData.frontBreaksList, FrontBreak.class);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

