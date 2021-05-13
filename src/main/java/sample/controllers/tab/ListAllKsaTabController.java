package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.constants.TextConstants;
import sample.controllers.AddAllAggregatesController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.enums.TypesOfWorks;
import sample.delete.DeleteObject;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;
import sample.write.WriteFile;


public class ListAllKsaTabController {

    @FXML
    AddAllAggregatesController addAllAggregates;

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
    private TableColumn<Aircraft, Aircraft> columnInstalledKsa;

    @FXML
    private ComboBox<TypesOfWorks> listOfWorksKsa;

    @FXML
    private Button makeWorksKsa;

    @FXML
    void initialize() {
        UpdateList.updateList(SaveData.ksaList, tableKsa, Ksa.class, TextConstants.KSA_TEXT);
        OpenNewScene open = new OpenNewScene();
        columnNumberKsa.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledKsa.setCellValueFactory(new PropertyValueFactory<>("aircraftNumber"));
        listOfWorksKsa.getItems().addAll(TypesOfWorks.WORKS_AFTER_25_HOURS, TypesOfWorks.OIL_CHANGE_OPERATIONS);
        createNewKsa.setOnAction(e -> {
            AddAllAggregatesController ctrl = open.openNewScene("/sample/fxmlFiles/addAggregates.fxml", createNewKsa);
            ctrl.setCurrentTab(1);
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
