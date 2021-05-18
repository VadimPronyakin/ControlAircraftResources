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
import sample.controllers.dialog.CreateFrontWheelDialogController;
import sample.controllers.dialog.CreateMainBreakDialogController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.limitedResource.*;
import sample.data.enums.TypesOfWorks;
import sample.delete.DeleteObject;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;
import sample.write.WriteFile;

import java.io.IOException;

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

        updateTableMainBreaks();
        columnMainBreak.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledMainBreak.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        listOfWorksMainBreak.getItems().addAll(TypesOfWorks.REPLACEMENT_ROTATING_DISKS,
                TypesOfWorks.REPLACEMENT_NON_ROTATING_DISKS,
                TypesOfWorks.REPLACEMENT_PRESSURE_DISKS,
                TypesOfWorks.REPLACEMENT_REFERENCE_DISKS);
        createMainBreak.setOnAction(e -> {
           CreateMainBreakDialogController controller = showMainBreakDialog();
           controller.visibleButton(createMainBreak);
        });
        changeMainBreak.setOnAction(e -> {
          CreateMainBreakDialogController controller = showMainBreakDialog();
          controller.setMainBreak(tableMainBreaks.getSelectionModel().getSelectedItem());
          controller.visibleButton(changeMainBreak);
        });
        tableMainBreaks.setRowFactory(tv -> {
            TableRow<MainBreak> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    CreateMainBreakDialogController controller = showMainBreakDialog();
                    controller.setMainBreak(tableMainBreaks.getSelectionModel().getSelectedItem());
                }
            });
            return row;
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
    public CreateMainBreakDialogController showMainBreakDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createMainBreakDialog.fxml"));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateMainBreakDialogController controller = loader.getController();
            controller.setListMainBreaksTabController(this);
            dialogStage.show();
            return controller;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
    public void updateTableMainBreaks() {
        UpdateList.updateList(SaveData.mainBreaksList,
                tableMainBreaks,
                MainBreak.class,
                TextConstants.MAIN_BREAK_TEXT);
    }
}

