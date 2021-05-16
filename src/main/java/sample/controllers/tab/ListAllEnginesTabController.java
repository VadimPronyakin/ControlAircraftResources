package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.constants.TextConstants;
import sample.controllers.AddAllAggregatesController;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.enums.TypesOfWorks;
import sample.delete.DeleteObject;
import sample.update.UpdateList;
import sample.write.WriteFile;

import static sample.openNewScene.OpenNewScene.openNewScene;


public class ListAllEnginesTabController {

    @FXML
    private Button createNewEngine;

    @FXML
    private Button changeEngine;

    @FXML
    private Button deleteEngine;

    @FXML
    private TableView<Engine> tableEngine;

    @FXML
    private TableColumn<Engine, Engine> columnNumberEngine;

    @FXML
    private TableColumn<String, String> columnInstalledEngine;

    @FXML
    private ComboBox<TypesOfWorks> listOfWorksEngine;

    @FXML
    private Button makeWorksEngine;

    @FXML
    void initialize() {
        UpdateList.updateList(SaveData.enginesList, tableEngine, Engine.class, TextConstants.ENGINE_TEXT);
        columnNumberEngine.setCellValueFactory(new PropertyValueFactory<>("serialNumberEngine"));
        columnInstalledEngine.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        listOfWorksEngine.getItems().addAll(TypesOfWorks.WORKS_AFTER_10_HOURS,
                TypesOfWorks.WORKS_AFTER_25_HOURS,
                TypesOfWorks.WORKS_AFTER_50_HOURS,
                TypesOfWorks.WORKS_AFTER_100_HOURS,
                TypesOfWorks.WORKS_AFTER_150_HOURS,
                TypesOfWorks.WORKS_AFTER_278_BULLETIN,
                TypesOfWorks.OIL_CHANGE_OPERATIONS);
        createNewEngine.setOnAction(e -> {
            AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", createNewEngine);
            ctrl.setCurrentTab(2);
            ctrl.getEngineTabController().visibleButton(createNewEngine);
            ctrl.visibleText(createNewEngine);
        });
        deleteEngine.setOnAction(e -> DeleteObject.delete(SaveData.enginesList, tableEngine, Engine.class));
        makeWorksEngine.setOnAction(e -> doWorkEngine());
        changeEngine.setOnAction(e -> {
            AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", changeEngine);
            ctrl.getEngineTabController().setEngine(tableEngine.getSelectionModel().getSelectedItem().getSerialNumberEngine());
            ctrl.setCurrentTab(2);
            ctrl.getEngineTabController().visibleButton(changeEngine);
            ctrl.visibleText(changeEngine);
        });
        tableEngine.setRowFactory(tv -> {
            TableRow<Engine> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", changeEngine);
                    ctrl.getEngineTabController().setEngine(tableEngine.getSelectionModel().getSelectedItem().getSerialNumberEngine());
                    ctrl.setCurrentTab(2);
                }
            });
            return row;
        });
    }

    void doWorkEngine() {
        Engine engine = tableEngine.getSelectionModel().getSelectedItem();
        try {
            switch (listOfWorksEngine.getSelectionModel().getSelectedItem()) {
                case WORKS_AFTER_10_HOURS:
                    engine.setResourceReserveBefore_10hours(listOfWorksEngine.getSelectionModel().getSelectedItem().getResource());
                    WriteFile.serialization(SaveData.enginesList, Engine.class);
                    break;
                case WORKS_AFTER_25_HOURS:
                    engine.setResourceReserveBefore_25hours(listOfWorksEngine.getSelectionModel().getSelectedItem().getResource());
                    WriteFile.serialization(SaveData.enginesList, Engine.class);
                    break;
                case WORKS_AFTER_50_HOURS:
                    engine.setResourceReserveBefore_50hours(listOfWorksEngine.getSelectionModel().getSelectedItem().getResource());
                    engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                    engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
                    WriteFile.serialization(SaveData.enginesList, Engine.class);
                    break;
                case WORKS_AFTER_100_HOURS:
                    engine.setResourceReserveBefore_100hours(listOfWorksEngine.getSelectionModel().getSelectedItem().getResource());
                    engine.setResourceReserveBefore_50hours(listOfWorksEngine.getSelectionModel().getSelectedItem().getResource());
                    engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                    engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
                    WriteFile.serialization(SaveData.enginesList, Engine.class);
                    break;
                case WORKS_AFTER_150_HOURS:
                    engine.setResourceReserveBefore_150hours(listOfWorksEngine.getSelectionModel().getSelectedItem().getResource());
                    engine.setResourceReserveBefore_50hours(listOfWorksEngine.getSelectionModel().getSelectedItem().getResource());
                    engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                    engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
                    WriteFile.serialization(SaveData.enginesList, Engine.class);
                    break;
                case WORKS_AFTER_278_BULLETIN:
                    engine.setResourceReserveBefore_278bulletin(listOfWorksEngine.getSelectionModel().getSelectedItem().getResource());
                    WriteFile.serialization(SaveData.enginesList, Engine.class);
                    break;
                case OIL_CHANGE_OPERATIONS:
                    engine.setOilChange(listOfWorksEngine.getSelectionModel().getSelectedItem().getResource());
                    WriteFile.serialization(SaveData.enginesList, Engine.class);
                    break;
            }
        } catch (NullPointerException e) {
            tableEngine.setPlaceholder(new Label(TextConstants.WORKS_TEXT));
        }
    }

}



