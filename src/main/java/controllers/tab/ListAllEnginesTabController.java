package controllers.tab;

import constants.TextConstants;
import controllers.dialog.CreateEngineDialogController;
import data.Aircraft;
import data.SaveData;
import data.components.Engine;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;

import static utils.Utils.*;

@Slf4j
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
    private TableColumn<Engine, Boolean> columnNotification;

    @FXML
    void initialize() {
        updateTableEngine();
        columnNumberEngine.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledEngine.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        columnNotification.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsNeedAttention()) {
        });
        columnNotification.setCellFactory(col -> {
            TableCell<Engine, Boolean> cell = new TableCell<>();
            cell.itemProperty().addListener((obs, old, newVal) -> {
                if (newVal != null) {
                    Node centreBox = createPriorityGraphic(newVal);
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(centreBox));
                }
            });
            return cell;
        });
        createNewEngine.setOnAction(e -> {
            CreateEngineDialogController controller = showDialogWindow("/fxmlFiles/dialog/createEngineDialog.fxml");
            controller.setListAllEnginesTabController(this);
            controller.setButtonVisible(false, true,
                    false, false,
                    false, false);
            log.info("Перешел на страницу создания двигателя");

        });
        deleteEngine.setOnAction(e ->  {
            checkingMatches();
            log.info("Удалил двигатель");
        });
        changeEngine.setOnAction(e -> {
            CreateEngineDialogController controller = showDialogWindow("/fxmlFiles/dialog/createEngineDialog.fxml");
            controller.setListAllEnginesTabController(this);
            controller.setEngine(tableEngine.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(false, false,
                    true, false,
                    false, false);
            log.info("Перешел на страницу изменения двигателя");
        });
        tableEngine.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateEngineDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createEngineDialog.fxml");
                controller.setButtonVisible(false, false,
                        false, false,
                        false, false);
                controller.setEngine(tableEngine.getSelectionModel().getSelectedItem());
                log.info("Перешел на страницу информации о двигателе");
            }
        });
    }

    /**
     * Метод проверяет на совпадение двигатели из списка агрегатов и двигатели, установленные на самолеты.
     * Если совпадение найдено, открывается всплывающее окно с предупреждением,
     * если совпадения нет, выбранный двигатель удаляется из списка агрегатов, изменения сериализуются в файл с двигателями
     */
    private void checkingMatches() {
        String sideNUmber = "";
        boolean isMatches = true;
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftEngine() != null) {
                if (aircraft.getLeftEngine().getSerialNumber().equals(
                        tableEngine.getSelectionModel().getSelectedItem().getSerialNumber())) {
                    isMatches = false;
                    sideNUmber = aircraft.getAircraftNumber();
                }
            }
            if (aircraft.getRightEngine() != null) {
                if (aircraft.getRightEngine().getSerialNumber().equals(
                        tableEngine.getSelectionModel().getSelectedItem().getSerialNumber())) {
                    isMatches = false;
                    sideNUmber = aircraft.getAircraftNumber();
                }
            }
        }
        if (isMatches) {
            delete(SaveData.enginesList, tableEngine, Engine.class);
        } else {
            openInformationWindow(TextConstants.ENGINE_INSTALLED + sideNUmber +
                    TextConstants.ENGINE_DELETE);
            log.info("Пользователь пытался удалить двигатель, который установлен на самолете");
        }
    }
    public void updateTableEngine() {
        updateList(SaveData.enginesList, tableEngine, TextConstants.ENGINE_TEXT);
    }
}




