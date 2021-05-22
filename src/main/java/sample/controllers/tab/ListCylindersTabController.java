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
import sample.controllers.dialog.CreateCylinderDialogController;
import sample.data.SaveData;
import sample.data.components.Ksa;
import sample.data.components.limitedResource.CylinderOfRetractionExtension;
import sample.data.enums.TypesOfWorks;
import sample.delete.DeleteObject;
import sample.update.UpdateList;
import sample.write.WriteFile;

import java.io.IOException;


public class ListCylindersTabController {

    @FXML
    private TableView<CylinderOfRetractionExtension> tableCylinders;

    @FXML
    private TableColumn<CylinderOfRetractionExtension, CylinderOfRetractionExtension> columnNumberCylinder;

    @FXML
    private TableColumn<String, String> columnInstalledCylinder;

    @FXML
    private Button createNewCylinder;

    @FXML
    private Button changeCylinder;

    @FXML
    private Button deleteCylinder;

    @FXML
    private Button makeWorkCylinder;

    @FXML
    private ComboBox<TypesOfWorks> listOfWorksCylinder;


    @FXML
    void initialize() {
        updateTableCylinders();
        columnNumberCylinder.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledCylinder.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        listOfWorksCylinder.getItems().addAll(TypesOfWorks.FIRST_REPAIR_CYLINDER, TypesOfWorks.SECOND_REPAIR_CYLINDER);
        createNewCylinder.setOnAction(e -> {
            CreateCylinderDialogController controller = showCylinderDialog();
            controller.setButtonVisible(createNewCylinder.getText());
        });
        changeCylinder.setOnAction(e -> {
            CreateCylinderDialogController controller = showCylinderDialog();
            controller.setCylinder(tableCylinders.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(changeCylinder.getText());
        });
        tableCylinders.setRowFactory(tv -> {
            TableRow<CylinderOfRetractionExtension> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    CreateCylinderDialogController controller = showCylinderDialog();
                    controller.setCylinder(tableCylinders.getSelectionModel().getSelectedItem());
                    controller.setButtonVisible("Двойное нажатие");
                }
            });
            return row;
        });
        deleteCylinder.setOnAction(e -> DeleteObject.delete(SaveData.cylindersList,
                tableCylinders,
                CylinderOfRetractionExtension.class));
        makeWorkCylinder.setOnAction(e -> doWorksCylinder());
    }

    void doWorksCylinder() {
        CylinderOfRetractionExtension cylinder = tableCylinders.getSelectionModel().getSelectedItem();
        try {
            switch (listOfWorksCylinder.getSelectionModel().getSelectedItem()) {

                case FIRST_REPAIR_CYLINDER:
                    cylinder.setResource_Reserve_Before_First_Repair(0);
                    cylinder.setResource_Reserve_Before_Second_Repair(800);
                    WriteFile.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
                    break;
                case SECOND_REPAIR_CYLINDER:
                    cylinder.setResource_Reserve_Before_Second_Repair(0);
                    WriteFile.serialization(SaveData.ksaList, Ksa.class);
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public CreateCylinderDialogController showCylinderDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createCylinderDialog.fxml"));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateCylinderDialogController controller = loader.getController();
            controller.setListCylindersTabController(this);
            dialogStage.show();
            return controller;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
   return null;
    }
    public void updateTableCylinders() {
        UpdateList.updateList(SaveData.cylindersList,
                tableCylinders,
                TextConstants.CYLINDER_TEXT);
    }
}

