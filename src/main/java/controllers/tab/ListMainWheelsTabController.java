package controllers.tab;

import constants.TextConstants;
import controllers.dialog.CreateMainWheelDialogController;
import data.Aircraft;
import data.SaveData;
import data.components.limitedResource.MainWheel;
import io.writer.FilesWriter;
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
    private TableColumn<MainWheel, Boolean> columnNotification;

    @FXML
    void initialize() {
        updateInstalledMainWheel();
        updateTableMainWheels();
        columnMainWheel.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledMainWheel.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        columnNotification.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsNeedAttention()) {
        });
        columnNotification.setCellFactory(col -> {
            TableCell<MainWheel, Boolean> cell = new TableCell<>();
            cell.itemProperty().addListener((obs, old, newVal) -> {
                if (newVal != null) {
                    Node centreBox = createPriorityGraphic(newVal);
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(centreBox));
                }
            });
            return cell;
        });
        createMainWheel.setOnAction(e -> {
            CreateMainWheelDialogController controller = showDialogWindow("/fxmlFiles/dialog/createMainWheelDialog.fxml");
            controller.setListMainWheelsTabController(this);
            controller.setButtonVisible(true, false, false);
            log.info("Перешел на страницу создания основного колеса");
        });
        changeMainWheel.setOnAction(e -> {
            CreateMainWheelDialogController controller = showDialogWindow("/fxmlFiles/dialog/createMainWheelDialog.fxml");
            controller.setListMainWheelsTabController(this);
            controller.setMainWheel(tableMainWheels.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(false, true, false);
            log.info("Перешел на страницу редактирования основного колеса");
        });
        tableMainWheels.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainWheelDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createMainWheelDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setMainWheel(tableMainWheels.getSelectionModel().getSelectedItem());
            }
        });
        deleteMainWheel.setOnAction(e -> {
            checkingMatches();
            log.info("Удалил основное колесо");
        });
    }

    /** Метод обновляет запись остатусе основного колеса,установлено ли оно на самолет или нет  №*/
    private void updateInstalledMainWheel() {
        for (MainWheel mainWheel : SaveData.mainWheelsList) {
            boolean isInstalled = true;
            for (Aircraft aircraft : SaveData.aircraftList) {
                if (mainWheel.getSerialNumber().equals(aircraft.getLeftMainWheel().getSerialNumber())
                        || mainWheel.getSerialNumber().equals(aircraft.getRightMainWheel().getSerialNumber())) {
                    isInstalled = false;
                    mainWheel.setAircraftNumberInstalled(aircraft.getAircraftNumber());
                }
            }
            if (isInstalled) {
                mainWheel.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
        }
        FilesWriter.serialization(SaveData.mainWheelsList, MainWheel.class);
    }
    /**
     * Метод обновляет значение поля, в котором хранится номер самолета, на котором установлено данное основное колесо.
     */
    public void updateTableMainWheels() {
        updateList(SaveData.mainWheelsList,
                tableMainWheels,
                TextConstants.MAIN_WHEEL_TEXT);
    }

    /**
     * Метод проверяет на совпадение основные колеса из ограниченного ресурса и основные колеса, установленные на самолеты.
     * Если совпадение найдено, открывается всплывающее окно с предупреждением,
     * если совпадения нет, выбранное  основное колесо удаляется из списка ограниченного ресурса,
     * изменения сериализуются в файл с основными колесами
     */
    private void checkingMatches() {
        String sideNUmber = "";
        boolean isMatches = true;
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftMainWheel().getSerialNumber().equals(
                    tableMainWheels.getSelectionModel().getSelectedItem().getSerialNumber())
                    || aircraft.getRightMainWheel().getSerialNumber().equals(
                    tableMainWheels.getSelectionModel().getSelectedItem().getSerialNumber())) {
                isMatches = false;
                sideNUmber = aircraft.getAircraftNumber();
            }
        }
        if (isMatches) {
            delete(SaveData.mainWheelsList, tableMainWheels, MainWheel.class);
        } else {
            openInformationWindow(TextConstants.MAIN_WHEEL_INSTALLED + sideNUmber +
                    TextConstants.MAIN_WHEEL_DELETE);
        }
    }
}

