package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.constants.TextConstants;
import sample.controllers.AddAllAggregatesController;
import sample.controllers.dialog.CreateCylinderDialogController;
import sample.controllers.dialog.CreateKsaDialogController;
import sample.data.SaveData;
import sample.data.components.Ksa;
import sample.data.components.limitedResource.CylinderOfRetractionExtension;
import sample.data.enums.TypesOfWorks;
import sample.delete.DeleteObject;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;
import sample.write.WriteFile;

import java.io.IOException;

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
        updateTableKsa();
        columnNumberKsa.setCellValueFactory(new PropertyValueFactory<>("serialNumberKsa"));
        columnInstalledKsa.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        listOfWorksKsa.getItems().addAll(TypesOfWorks.WORKS_AFTER_25_HOURS, TypesOfWorks.OIL_CHANGE_OPERATIONS);
        createNewKsa.setOnAction(e -> {
           CreateKsaDialogController controller = showKsaDialog();
           controller.visibleButton(createNewKsa);
        });
        changeKsa.setOnAction(e -> {
            CreateKsaDialogController controller = showKsaDialog();
            controller.setKsa(tableKsa.getSelectionModel().getSelectedItem());
            controller.visibleButton(changeKsa);
        });
        tableKsa.setRowFactory(tv -> {
            TableRow<Ksa> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    CreateKsaDialogController controller = showKsaDialog();
                    controller.setKsa(tableKsa.getSelectionModel().getSelectedItem());
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
    public CreateKsaDialogController showKsaDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createKsaDialog.fxml"));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateKsaDialogController controller = loader.getController();
            controller.setListAllKsaTabController(this);
            dialogStage.show();
            return controller;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    return null;
    }
    public void updateTableKsa() {
        UpdateList.updateList(SaveData.ksaList,
                tableKsa,
                Ksa.class,
                TextConstants.KSA_TEXT);
    }
}
