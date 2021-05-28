package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.constants.TextConstants;
import sample.controllers.dialog.CreateMainWheelDialogController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.limitedResource.MainWheel;
import sample.delete.DeleteObject;
import sample.update.UpdateList;

import java.io.IOException;


public class ListMainWheelsTabController {

    @FXML
    private Button createMainWheel;

    @FXML
    private Button changeMainWheel;

    @FXML
    private Button deleteMainWheel;

    @FXML
    private TableView<MainWheel> tableMainWheels;

    @FXML
    private TableColumn<MainWheel, MainWheel> columnMainWheel;

    @FXML
    private TableColumn<String, String> columnInstalledMainWheel;

    @FXML
    void initialize() {
        updateTableMainWheels();
        columnMainWheel.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledMainWheel.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        createMainWheel.setOnAction(e -> {
            CreateMainWheelDialogController controller = showMainWheelDialog();
            controller.setButtonVisible(createMainWheel.getText());
        });
        changeMainWheel.setOnAction(e -> {
            CreateMainWheelDialogController controller = showMainWheelDialog();
            controller.setMainWheel(tableMainWheels.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(changeMainWheel.getText());
        });
        tableMainWheels.setRowFactory(tv -> {
            TableRow<MainWheel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    CreateMainWheelDialogController controller = showMainWheelDialog();
                    controller.setMainWheel(tableMainWheels.getSelectionModel().getSelectedItem());
                    controller.setButtonVisible("Двойное нажатие");
                }
            });
            return row;
        });
        deleteMainWheel.setOnAction(e -> DeleteObject.delete(SaveData.mainWheelsList, tableMainWheels, MainWheel.class));
    }

    public CreateMainWheelDialogController showMainWheelDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createMainWheelDialog.fxml"));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateMainWheelDialogController controller = loader.getController();
            controller.setListMainWheelsTabController(this);
            dialogStage.show();
            return controller;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void updateTableMainWheels() {
        UpdateList.updateList(SaveData.mainWheelsList,
                tableMainWheels,
                TextConstants.MAIN_WHEEL_TEXT);
        for (MainWheel mainWheel : SaveData.mainWheelsList) {
            for (Aircraft aircraft : SaveData.aircraftList) {
                if (mainWheel.getSerialNumber().equals(aircraft.getLeftMainWheel().getSerialNumber())
                        || mainWheel.getSerialNumber().equals(aircraft.getRightMainWheel().getSerialNumber())) {
                    mainWheel.setAircraftNumberInstalled(aircraft.getAircraftNumber());
                } else {
                    mainWheel.setAircraftNumberInstalled("Не установлено на самолет");
                }
            }
        }
    }
}

