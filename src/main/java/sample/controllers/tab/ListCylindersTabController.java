package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.constants.TextConstants;
import sample.controllers.AddAllAggregatesController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.Ksa;
import sample.data.components.limitedResource.CylinderOfRetractionExtension;
import sample.data.enums.TypesOfWorks;
import sample.delete.DeleteObject;
import sample.update.UpdateList;
import sample.write.WriteFile;

import static sample.openNewScene.OpenNewScene.openNewScene;


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

        UpdateList.updateList(SaveData.cylindersList,
                tableCylinders,
                CylinderOfRetractionExtension.class,
                TextConstants.MAIN_BREAK_TEXT);
        columnNumberCylinder.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledCylinder.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        listOfWorksCylinder.getItems().addAll(TypesOfWorks.FIRST_REPAIR_CYLINDER, TypesOfWorks.SECOND_REPAIR_CYLINDER);
        createNewCylinder.setOnAction(e -> {
            AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", createNewCylinder);
            ctrl.setCurrentTab(7);
        });
        changeCylinder.setOnAction(e -> {
            AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", changeCylinder);
            ctrl.getCylinderTabController().setCylinder(tableCylinders.getSelectionModel().getSelectedItem().getSerialNumber());
            ctrl.setCurrentTab(7);
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
}

