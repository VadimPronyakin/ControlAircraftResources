package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.constants.TextConstants;
import sample.controllers.dialog.CreateKsaDialogController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.Ksa;
import sample.delete.DeleteObject;
import sample.update.UpdateList;

import java.io.IOException;

import static sample.openNewScene.OpenNewScene.showEditDialogDoubleClick;


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
    void initialize() {
        updateTableKsa();
        columnNumberKsa.setCellValueFactory(new PropertyValueFactory<>( "serialNumberKsa"));
        columnInstalledKsa.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        createNewKsa.setOnAction(e -> {
            CreateKsaDialogController controller = showKsaDialog();
            controller.setButtonVisible(createNewKsa.getText());
        });
        changeKsa.setOnAction(e -> {
            CreateKsaDialogController controller = showKsaDialog();
            controller.setKsa(tableKsa.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(changeKsa.getText());
        });
        tableKsa.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateKsaDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/createKsaDialog.fxml");
                controller.setButtonVisible("Двойное нажатие");
                controller.setKsa(tableKsa.getSelectionModel().getSelectedItem());
            }
        });
        deleteKsa.setOnAction(e -> DeleteObject.delete(SaveData.ksaList, tableKsa, Ksa.class));
    }

    public CreateKsaDialogController showKsaDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxmlFiles/dialog/createKsaDialog.fxml"));
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
        UpdateList.updateList(SaveData.ksaList, tableKsa, TextConstants.KSA_TEXT);
        for (Ksa ksa : SaveData.ksaList) {
            for (Aircraft aircraft : SaveData.aircraftList) {
                if (aircraft.getKsa() == null) {
                    System.out.println("Добавь КСА на самолет");
                } else if (ksa.getSerialNumberKsa().equals(aircraft.getKsa().getSerialNumberKsa())) {
                    ksa.setAircraftNumberInstalled(aircraft.getAircraftNumber());
                } else {
                    ksa.setAircraftNumberInstalled("Не установлена на самолет");
                }
            }
        }
    }
}
