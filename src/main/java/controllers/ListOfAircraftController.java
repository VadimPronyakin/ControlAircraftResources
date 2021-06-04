package controllers;

import constants.TextConstants;
import controllers.dialog.CreateAircraftDialogController;
import controllers.dialog.PersonalAircraftDialogController;
import data.Aircraft;
import data.SaveData;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static utils.UpdateAircraftComponents.*;
import static utils.UpdateNotification.updateNotificationOnAircraft;
import static utils.Utils.*;


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
    private TableColumn<Aircraft, Boolean> columnNotification;

    @FXML
    private TableColumn<Aircraft, Aircraft> columnNumberAircraft;

    @FXML
    private TableColumn<String, String> columnEngineerAk;

    @FXML
    void initialize() {
        updateReserveDays();
        updateTableAircraft();
        columnNumberAircraft.setCellValueFactory(new PropertyValueFactory<>("aircraftNumber"));
        columnEngineerAk.setCellValueFactory(new PropertyValueFactory<>("fullNameEngineer"));
        columnNotification.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsNeedAttention()) {
        });
        columnNotification.setCellFactory(col -> {
            TableCell<Aircraft, Boolean> cell = new TableCell<>();
            cell.itemProperty().addListener((obs, old, newVal) -> {
                if (newVal != null) {
                    Node centreBox = createPriorityGraphic(newVal);
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(centreBox));
                }
            });
            return cell;
        });
        returnHomePage.setOnAction(event -> openNewScene("/fxmlFiles/sample.fxml", returnHomePage));
        createNewAircraft.setOnAction(e -> {
            CreateAircraftDialogController controller = showDialogWindow("/fxmlFiles/dialog/createAircraftDialog.fxml");
            controller.setListOfAircraftController(this);
            controller.setButtonVisible(true, false);
        });
        deleteAircraft.setOnAction(e -> {
            update_Installed_Engine_After_DeleteAircraft(tableAircraft.getSelectionModel().getSelectedItem());
            update_Installed_Ksa_After_DeleteAircraft(tableAircraft.getSelectionModel().getSelectedItem());
            deletePlaner(tableAircraft.getSelectionModel().getSelectedItem());
            delete(SaveData.aircraftList, tableAircraft, Aircraft.class);
                });
        tableAircraft.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                java.util.Map<Stage, PersonalAircraftDialogController> map = showEditDialogDoubleClickWithStage(e,
                        "/fxmlFiles/dialog/personalAircraftDialog.fxml");
                PersonalAircraftDialogController controller = null;
                Stage stage = null;
                for (java.util.Map.Entry entry : map.entrySet()) {
                    controller = (PersonalAircraftDialogController) entry.getValue();
                    stage = (Stage) entry.getKey();
                }
                controller.setAircraft(tableAircraft.getSelectionModel().getSelectedItem());
                controller.setListOfAircraftController(this);
                controller.setCurrentStage(stage);

            }
        });
        changeAircraft.setOnAction(e -> {
            CreateAircraftDialogController controller = showDialogWindow("/fxmlFiles/dialog/createAircraftDialog.fxml");
            controller.setListOfAircraftController(this);
            controller.setAircraft(tableAircraft.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(false, true);
        });

    }

    /**
     * Метод обновялет список самолетов в TableView
     */
    public void updateTableAircraft() {
        updateNotificationOnAircraft();
        updateList(SaveData.aircraftList, tableAircraft, TextConstants.AIRCRAFT_TEXT);
    }
}


