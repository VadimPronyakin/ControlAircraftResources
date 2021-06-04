package controllers.tab;

import constants.TextConstants;
import controllers.dialog.CreateFrontWheelDialogController;
import data.Aircraft;
import data.SaveData;
import data.components.limitedResource.FrontWheel;
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
    private TableColumn<FrontWheel, Boolean> columnNotification;

    @FXML
    void initialize() {
        updateInstalledFrontWheel();
        updateTableFrontWheels();
        columnFrontWheel.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledFrontWheel.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        columnNotification.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsNeedAttention()) {
        });
        columnNotification.setCellFactory(col -> {
            TableCell<FrontWheel, Boolean> cell = new TableCell<>();
            cell.itemProperty().addListener((obs, old, newVal) -> {
                if (newVal != null) {
                    Node centreBox = createPriorityGraphic(newVal);
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(centreBox));
                }
            });
            return cell;
        });
        createFrontWheel.setOnAction(e -> {
            CreateFrontWheelDialogController controller = showDialogWindow("/fxmlFiles/dialog/createFrontWheelDialog.fxml");
            controller.setListFrontWheelTabController(this);
            controller.setButtonVisible(true, false, false);
            log.info("Перешел на страницу создания переднего колеса");
        });
        changeFrontWheel.setOnAction(e -> {
            CreateFrontWheelDialogController controller = showDialogWindow("/fxmlFiles/dialog/createFrontWheelDialog.fxml");
            controller.setListFrontWheelTabController(this);
            controller.setFrontWheel(tableFrontWheels.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(false, true, false);
            log.info("Перешел на страницу создания переднего колеса");
        });
        tableFrontWheels.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontWheelDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createFrontWheelDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setFrontWheel(tableFrontWheels.getSelectionModel().getSelectedItem());
            }
        });
        deleteFrontWheel.setOnAction(e -> {
            checkingMatches();
            log.info("Удалил переднее колесо");
        });
    }

    /** Метод обновляет запись остатусе переднего колеса,установлено ли оно на самолет или нет №*/
    private void updateInstalledFrontWheel() {
        for (FrontWheel frontWheel : SaveData.frontWheelsList) {
            boolean isInstalled = true;
            for (Aircraft aircraft : SaveData.aircraftList) {
                if (frontWheel.getSerialNumber().equals(aircraft.getLeftFrontWheel().getSerialNumber())
                        || frontWheel.getSerialNumber().equals(aircraft.getRightFrontWheel().getSerialNumber())) {
                    isInstalled = false;
                    frontWheel.setAircraftNumberInstalled(aircraft.getAircraftNumber());
                }
            }
            if (isInstalled) {
                frontWheel.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
        }
        FilesWriter.serialization(SaveData.frontWheelsList, FrontWheel.class);
    }
    /**
     * Метод обновляет значение поля, в котором хранится номер самолета, на котором установлено данное переднее колесо.
     */
    public void updateTableFrontWheels() {
        updateList(SaveData.frontWheelsList,
                tableFrontWheels,
                TextConstants.FRONT_WHEEL_TEXT);
    }

    /**
     * Метод проверяет на совпадение передние колса из ограниченного ресурса и передние колеса, установленные на самолеты.
     * Если совпадение найдено, открывается всплывающее окно с предупреждением,
     * если совпадения нет, выбранное  переднее колесо удаляется из списка ограниченного ресурса,
     * изменения сериализуются в файл с передними колесами
     */
    private void checkingMatches() {
        String sideNUmber = "";
        boolean isMatches = true;
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftFrontWheel().getSerialNumber().equals(
                    tableFrontWheels.getSelectionModel().getSelectedItem().getSerialNumber())
                    || aircraft.getRightFrontWheel().getSerialNumber().equals(
                    tableFrontWheels.getSelectionModel().getSelectedItem().getSerialNumber())) {
                isMatches = false;
                sideNUmber = aircraft.getAircraftNumber();
            }
        }
        if (isMatches) {
            delete(SaveData.frontWheelsList, tableFrontWheels, FrontWheel.class);
        } else {
            openInformationWindow(TextConstants.FRONT_WHEEL_INSTALLED + sideNUmber +
                    TextConstants.FRONT_WHEEL_DELETE);
        }
    }
}

