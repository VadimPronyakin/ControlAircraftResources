package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.constants.TextConstants;
import sample.controllers.dialog.CreateEngineDialogController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.delete.DeleteObject;
import sample.update.UpdateList;

import java.io.IOException;

import static sample.openNewScene.OpenNewScene.showEditDialog;


public class ListAllEnginesTabController {

    @FXML
    private Button createNewEngine;
    @FXML
    private Button changeEngine;
    @FXML
    private Button deleteEngine;
    @FXML
    private TableView<Engine> tableEngine;
    @FXML
    private TableColumn<Engine, Engine> columnNumberEngine;
    @FXML
    private TableColumn<String, String> columnInstalledEngine;

    @FXML
    void initialize() {
        updateTableEngines();
        columnNumberEngine.setCellValueFactory(new PropertyValueFactory<>("serialNumberEngine"));
        columnInstalledEngine.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        createNewEngine.setOnAction(e -> {
            CreateEngineDialogController controller = showEngineDialog();
            controller.setButtonVisible(createNewEngine.getText());

        });
        deleteEngine.setOnAction(e -> DeleteObject.delete(SaveData.enginesList, tableEngine, Engine.class));
        changeEngine.setOnAction(e -> {
            CreateEngineDialogController controller = showEngineDialog();
            controller.setEngine(tableEngine.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(changeEngine.getText());
        });
        tableEngine.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateEngineDialogController controller = showEditDialog(e,
                        "/sample/fxmlFiles/dialog/createEngineDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setEngine(tableEngine.getSelectionModel().getSelectedItem());
            }
        });
    }

    public CreateEngineDialogController showEngineDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createEngineDialog.fxml"));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateEngineDialogController controller = loader.getController();
            controller.setListAllEnginesTabController(this);
            dialogStage.show();
            return controller;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void updateTableEngines() {
        UpdateList.updateList(SaveData.enginesList, tableEngine, TextConstants.ENGINE_TEXT);
        for (Engine engine : SaveData.enginesList) {
            for (Aircraft aircraft : SaveData.aircraftList) {
                if (aircraft.getLeftEngine() == null
                    || aircraft.getRightEngine() == null) {
                    System.out.println("На самолете нет двигателя");
                } else if (engine.getSerialNumberEngine().equals(aircraft.getLeftEngine().getSerialNumberEngine())
                        || engine.getSerialNumberEngine().equals(aircraft.getRightEngine().getSerialNumberEngine())) {
                    engine.setAircraftNumberInstalled(aircraft.getAircraftNumber());
                } else {
                    engine.setAircraftNumberInstalled("Не установлен на самолет");
                }
            }
        }
    }
}



