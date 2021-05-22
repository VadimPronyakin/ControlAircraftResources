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
import sample.controllers.dialog.CreateFrontBreakDialogController;
import sample.data.SaveData;
import sample.data.components.limitedResource.FrontBreak;
import sample.data.enums.TypesOfWorks;
import sample.delete.DeleteObject;
import sample.update.UpdateList;
import sample.write.WriteFile;

import java.io.IOException;


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
        updateTableFrontBreak();
        columnFrontBreak.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledFrontBreak.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        listOfWorksFrontBreak.getItems().addAll(TypesOfWorks.FIRST_REPAIR_FRONT_BREAK);
        createFrontBreak.setOnAction(e -> {
            CreateFrontBreakDialogController controller = showFrontBreakDialog();
            controller.setButtonVisible(createFrontBreak.getText());
        });
        changeFrontBreak.setOnAction(e -> {
           CreateFrontBreakDialogController controller = showFrontBreakDialog();
           controller.setFrontBreak(tableFrontBreaks.getSelectionModel().getSelectedItem());
           controller.setButtonVisible(changeFrontBreak.getText());
        });
        tableFrontBreaks.setRowFactory(tv -> {
            TableRow<FrontBreak> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    CreateFrontBreakDialogController controller = showFrontBreakDialog();
                    controller.setFrontBreak(tableFrontBreaks.getSelectionModel().getSelectedItem());
                    controller.setButtonVisible("Двойное нажатие");
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
    public CreateFrontBreakDialogController showFrontBreakDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createFrontBreakDialog.fxml"));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateFrontBreakDialogController controller = loader.getController();
            controller.setListFrontBreakTabController(this);
            dialogStage.show();
            return controller;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
    public void updateTableFrontBreak() {
        UpdateList.updateList(SaveData.frontBreaksList,
                tableFrontBreaks,
                TextConstants.FRONT_BREAK_TEXT);
    }
}

