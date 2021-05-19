package sample.controllers;

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
import sample.controllers.dialog.CreateAircraftDialogController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.delete.DeleteObject;
import sample.update.UpdateList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.openNewScene.OpenNewScene.openNewScene;


public class ListOfAircraftController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnHomePage;

    @FXML
    private Button createNewAircraft;

    @FXML
    private Button changeAircraft;

    @FXML
    private Button deleteAircraft;

    @FXML
    private TableView<Aircraft> tableAircraft;

    @FXML
    private TableColumn<Aircraft, Aircraft> columnNumberAircraft;

    @FXML
    private TableColumn<String, String> columnEngineerAk;

    @FXML
    void initialize() {
        updateTableAircraft();
        columnNumberAircraft.setCellValueFactory(new PropertyValueFactory<>("aircraftNumber"));
        columnEngineerAk.setCellValueFactory(new PropertyValueFactory<>("fullNameEngineer"));
        returnHomePage.setOnAction(event -> openNewScene("/sample/fxmlFiles/sample.fxml", returnHomePage));
        createNewAircraft.setOnAction(e -> showAircraftDialog());
        deleteAircraft.setOnAction(e -> DeleteObject.delete(SaveData.aircraftList, tableAircraft, Aircraft.class));
    }

    public void showAircraftDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmlFiles/dialog/createAircraftDialog.fxml"));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateAircraftDialogController controller = loader.getController();
            controller.setListOfAircraftController(this);
            dialogStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void updateTableAircraft() {
        UpdateList.updateList(SaveData.aircraftList, tableAircraft, Aircraft.class, TextConstants.AIRCRAFT_TEXT);

    }
}


