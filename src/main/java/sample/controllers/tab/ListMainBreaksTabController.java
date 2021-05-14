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
import sample.data.components.limitedResource.*;
import sample.data.enums.TypesOfWorks;
import sample.delete.DeleteObject;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;
import sample.write.WriteFile;

import static sample.openNewScene.OpenNewScene.openNewScene;


public class ListMainBreaksTabController {

    @FXML
    private TableView<MainBreak> tableMainBreaks;

    @FXML
    private TableColumn<MainBreak, MainBreak> columnMainBreak;

    @FXML
    private TableColumn<String, String> columnInstalledMainBreak;

    @FXML
    private Button createMainBreak;

    @FXML
    private Button changeMainBreak;

    @FXML
    private Button deleteMainBreak;

    @FXML
    private Button makeWorkMainBreak;

    @FXML
    private ComboBox<TypesOfWorks> listOfWorksMainBreak;

    @FXML
    void initialize() {

        UpdateList.updateList(SaveData.mainBreaksList, tableMainBreaks, MainBreak.class, TextConstants.MAIN_BREAK_TEXT);
        columnMainBreak.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledMainBreak.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        listOfWorksMainBreak.getItems().addAll(TypesOfWorks.REPLACEMENT_ROTATING_DISKS,
                TypesOfWorks.REPLACEMENT_NON_ROTATING_DISKS,
                TypesOfWorks.REPLACEMENT_PRESSURE_DISKS,
                TypesOfWorks.REPLACEMENT_REFERENCE_DISKS);
        createMainBreak.setOnAction(e -> {
            AddAllAggregatesController ctrl = OpenNewScene.openNewScene("/sample/fxmlFiles/addAggregates.fxml", createMainBreak);
            ctrl.setCurrentTab(3);
        });
        changeMainBreak.setOnAction(e -> {
            AddAllAggregatesController ctrl = openNewScene("/sample/fxmlFiles/addAggregates.fxml", changeMainBreak);
            ctrl.getMainBreakTabController().setMainBreak(tableMainBreaks.getSelectionModel().getSelectedItem().getSerialNumber());
            ctrl.setCurrentTab(3);
        });
        deleteMainBreak.setOnAction(e -> DeleteObject.delete(SaveData.mainBreaksList, tableMainBreaks, MainBreak.class));
        makeWorkMainBreak.setOnAction(e -> doWorksMainBreak());
    }

    void doWorksMainBreak() {
        MainBreak mainBreak = tableMainBreaks.getSelectionModel().getSelectedItem();
        try {
            switch (listOfWorksMainBreak.getSelectionModel().getSelectedItem()) {

                case REPLACEMENT_ROTATING_DISKS:
                    mainBreak.setResource_Reserve_Replacement_RotatingDisks(listOfWorksMainBreak
                            .getSelectionModel()
                            .getSelectedItem()
                            .getResource());
                    WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);
                    break;
                case REPLACEMENT_NON_ROTATING_DISKS:
                    mainBreak.setResource_Reserve_Replacement_NonRotatingDisks(listOfWorksMainBreak
                            .getSelectionModel()
                            .getSelectedItem()
                            .getResource());
                    WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);
                    break;
                case REPLACEMENT_PRESSURE_DISKS:
                    mainBreak.setResource_Reserve_Replacement_PressureDisk(listOfWorksMainBreak.getSelectionModel().getSelectedItem().getResource());
                    break;
                case REPLACEMENT_REFERENCE_DISKS:
                    mainBreak.setResource_Reserve_Replacement_ReferenceDisk(listOfWorksMainBreak.getSelectionModel().getSelectedItem().getResource());
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

