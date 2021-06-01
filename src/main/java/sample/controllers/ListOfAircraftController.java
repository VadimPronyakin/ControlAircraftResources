package sample.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.constants.TextConstants;
import sample.controllers.dialog.CreateAircraftDialogController;
import sample.controllers.dialog.CreateEngineDialogController;
import sample.controllers.dialog.PersonalAircraftDialogController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.delete.DeleteObject;
import sample.update.UpdateList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.openNewScene.OpenNewScene.openNewScene;
import static sample.openNewScene.OpenNewScene.showEditDialog;
import static sample.update.UpdateList.updateList;


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
           CreateAircraftDialogController controller = showAircraftDialog();
           controller.setButtonVisible("Добавить самолет");
        });
        deleteAircraft.setOnAction(e -> DeleteObject.delete(SaveData.aircraftList, tableAircraft, Aircraft.class));
        tableAircraft.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                PersonalAircraftDialogController controller = showEditDialog(e,
                        "/fxmlFiles/dialog/personalAircraftDialog.fxml");
                controller.setAircraft(tableAircraft.getSelectionModel().getSelectedItem());
                controller.setListOfAircraftController(this);

            }
        });
        changeAircraft.setOnAction(e -> {
            CreateAircraftDialogController controller = showAircraftDialog();
            controller.setAircraft(tableAircraft.getSelectionModel().getSelectedItem());
            controller.setButtonVisible("Изменить запись");
        });

    }
    private Node createPriorityGraphic(Boolean isPriority){
        HBox graphicContainer = new HBox();
        graphicContainer.setAlignment(Pos.CENTER);
        if(isPriority){
            ImageView imageView = new ImageView(new Image(Main.class.getResourceAsStream("/images/1.png")));
            imageView.setFitHeight(25);
            imageView.setPreserveRatio(true);
            graphicContainer.getChildren().add(imageView);
        }
        return graphicContainer;
    }

    public CreateAircraftDialogController showAircraftDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxmlFiles/dialog/createAircraftDialog.fxml"));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateAircraftDialogController controller = loader.getController();
            controller.setListOfAircraftController(this);
            dialogStage.show();
            return controller;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    return null;
    }
    public void updateTableAircraft() {
        UpdateList.updateNotificationOnAircraft();
        updateList(SaveData.aircraftList, tableAircraft, TextConstants.AIRCRAFT_TEXT);
    }
}


