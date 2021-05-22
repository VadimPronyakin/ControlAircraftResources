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
import sample.controllers.dialog.CreateFrontWheelDialogController;
import sample.data.SaveData;
import sample.data.components.limitedResource.*;
import sample.delete.DeleteObject;
import sample.update.UpdateList;

import java.io.IOException;


public class ListFrontWheelTabController {

    @FXML
    private Button createFrontWheel;

    @FXML
    private Button changeFrontWheel;

    @FXML
    private Button deleteFrontWheel;

    @FXML
    private TableView<FrontWheel> tableFrontWheels;

    @FXML
    private TableColumn<FrontWheel, FrontWheel> columnFrontWheel;

    @FXML
    private TableColumn<String, String> columnInstalledFrontWheel;

    @FXML
    void initialize() {
        updateTableFrontWheels();
        columnFrontWheel.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledFrontWheel.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        createFrontWheel.setOnAction(e -> {
           CreateFrontWheelDialogController controller = showFrontWheelDialog();
           controller.setButtonVisible(createFrontWheel.getText());
        });
        changeFrontWheel.setOnAction(e -> {
           CreateFrontWheelDialogController controller = showFrontWheelDialog();
           controller.setFrontWheel(tableFrontWheels.getSelectionModel().getSelectedItem());
           controller.setButtonVisible(changeFrontWheel.getText());
        });
        tableFrontWheels.setRowFactory(tv -> {
            TableRow<FrontWheel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    CreateFrontWheelDialogController controller = showFrontWheelDialog();
                    controller.setFrontWheel(tableFrontWheels.getSelectionModel().getSelectedItem());
                    controller.setButtonVisible("Двойное нажатие");
                }
            });
            return row;
        });
        deleteFrontWheel.setOnAction(e -> DeleteObject.delete(SaveData.frontWheelsList, tableFrontWheels, FrontWheel.class));
    }
    public CreateFrontWheelDialogController showFrontWheelDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createFrontWheelDialog.fxml"));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateFrontWheelDialogController controller = loader.getController();
            controller.setListFrontWheelTabController(this);
            dialogStage.show();
            return controller;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
    public void updateTableFrontWheels() {
        UpdateList.updateList(SaveData.frontWheelsList,
                tableFrontWheels,
                TextConstants.FRONT_WHEEL_TEXT);
    }
}

