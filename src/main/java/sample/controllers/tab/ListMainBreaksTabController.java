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
import sample.controllers.dialog.CreateMainBreakDialogController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.limitedResource.MainBreak;
import sample.delete.DeleteObject;
import sample.update.UpdateList;

import java.io.IOException;


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
    void initialize() {

        updateTableMainBreaks();
        columnMainBreak.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledMainBreak.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        createMainBreak.setOnAction(e -> {
            CreateMainBreakDialogController controller = showMainBreakDialog();
            controller.setButtonVisible(createMainBreak.getText());
        });
        changeMainBreak.setOnAction(e -> {
            CreateMainBreakDialogController controller = showMainBreakDialog();
            controller.setMainBreak(tableMainBreaks.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(changeMainBreak.getText());
        });
        tableMainBreaks.setRowFactory(tv -> {
            TableRow<MainBreak> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    CreateMainBreakDialogController controller = showMainBreakDialog();
                    controller.setMainBreak(tableMainBreaks.getSelectionModel().getSelectedItem());
                    controller.setButtonVisible("Двойное нажатие");
                }
            });
            return row;
        });
        deleteMainBreak.setOnAction(e -> DeleteObject.delete(SaveData.mainBreaksList, tableMainBreaks, MainBreak.class));
    }

    public CreateMainBreakDialogController showMainBreakDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxmlFiles/dialog/createMainBreakDialog.fxml"));
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
                TextConstants.MAIN_BREAK_TEXT);
        for (MainBreak mainBreak : SaveData.mainBreaksList) {
            for (Aircraft aircraft : SaveData.aircraftList) {
                if (mainBreak.getSerialNumber().equals(aircraft.getLeftMainBrake().getSerialNumber())
                        || mainBreak.getSerialNumber().equals(aircraft.getRightMainBrake().getSerialNumber())) {
                    mainBreak.setAircraftNumberInstalled(aircraft.getAircraftNumber());
                } else {
                    mainBreak.setAircraftNumberInstalled("Не установлен на самолет");
                }
            }
        }
    }
}

