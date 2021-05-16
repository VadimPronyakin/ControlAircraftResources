package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.constants.TextConstants;
import sample.controllers.AddAllAggregatesController;
import sample.data.SaveData;
import sample.data.components.Ksa;
import sample.data.enums.TypesOfWorks;
import sample.delete.DeleteObject;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;
import sample.write.WriteFile;

import static sample.openNewScene.OpenNewScene.openNewScene;


public class ListAllKsaTabController {


    @FXML
    private Button createNewKsa;

    @FXML
    private Button changeKsa;

    @FXML
    private Button deleteKsa;

    @FXML
    private TableView<Ksa> tableKsa;

    @FXML
    private TableColumn<Ksa, Ksa> columnNumberKsa;

    @FXML
    private TableColumn<String, String> columnInstalledKsa;

    @FXML
    private ComboBox<TypesOfWorks> listOfWorksKsa;

    @FXML
    private Button makeWorksKsa;

    @FXML
    void initialize() {
        UpdateList.updateList(SaveData.ksaList, tableKsa, Ksa.class, TextConstants.KSA_TEXT);
        columnNumberKsa.setCellValueFactory(new PropertyValueFactory<>("serialNumberKsa"));
        columnInstalledKsa.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        listOfWorksKsa.getItems().addAll(TypesOfWorks.WORKS_AFTER_25_HOURS, TypesOfWorks.OIL_CHANGE_OPERATIONS);
        createNewKsa.setOnAction(e -> {
            AddAllAggregatesController ctrl = OpenNewScene.openNewScene("/sample/fxmlFiles/addAggregates.fxml", createNewKsa);
            ctrl.setCurrentTab(1);
            ctrl.getKsaTabController().visibleButton(createNewKsa);
            ctrl.visibleText(createNewKsa);
        });
        changeKsa.setOnAction(e -> {
            AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", changeKsa);
            ctrl.getKsaTabController().setKsa(tableKsa.getSelectionModel().getSelectedItem().getSerialNumberKsa());
            ctrl.setCurrentTab(1);
            ctrl.visibleText(changeKsa);
            ctrl.getKsaTabController().visibleButton(changeKsa);
        });
        tableKsa.setRowFactory(tv -> {
            TableRow<Ksa> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", changeKsa);
                    ctrl.getKsaTabController().setKsa(tableKsa.getSelectionModel().getSelectedItem().getSerialNumberKsa());
                    ctrl.setCurrentTab(1);
                }
            });
            return row;
        });
        deleteKsa.setOnAction(e -> DeleteObject.delete(SaveData.ksaList, tableKsa, Ksa.class));
        makeWorksKsa.setOnAction(e -> doWorkKsa());
    }

    void doWorkKsa() {
        Ksa ksa = tableKsa.getSelectionModel().getSelectedItem();
        try {
            switch (listOfWorksKsa.getSelectionModel().getSelectedItem()) {

                case WORKS_AFTER_25_HOURS:
                    ksa.setResource_Reserve_Before_25hours(listOfWorksKsa.getSelectionModel().getSelectedItem().getResource());
                    WriteFile.serialization(SaveData.ksaList, Ksa.class);
                    break;
                case OIL_CHANGE_OPERATIONS:
                    ksa.setOilChange(listOfWorksKsa.getSelectionModel().getSelectedItem().getResource());
                    WriteFile.serialization(SaveData.ksaList, Ksa.class);
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
