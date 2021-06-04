package controllers.tab;

import constants.TextConstants;
import controllers.dialog.CreateMainBreakDialogController;
import data.Aircraft;
import data.SaveData;
import data.components.limitedResource.MainBreak;
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
public class ListMainBreaksTabController {

    @FXML
    private TableView<MainBreak> tableMainBreaks;
    @FXML
    private TableColumn<MainBreak, MainBreak> columnMainBreak;
    @FXML
    private TableColumn<String, String> columnInstalledMainBreak;
    @FXML
    private Button createMainBreak;
    @FXML
    private Button changeMainBreak;
    @FXML
    private Button deleteMainBreak;
    @FXML
    private TableColumn<MainBreak, Boolean> columnNotification;

    @FXML
    void initialize() {
        updateInstalledMainBreak();
        updateTableMainBreaks();
        columnMainBreak.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledMainBreak.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        columnNotification.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsNeedAttention()) {
        });
        columnNotification.setCellFactory(col -> {
            TableCell<MainBreak, Boolean> cell = new TableCell<>();
            cell.itemProperty().addListener((obs, old, newVal) -> {
                if (newVal != null) {
                    Node centreBox = createPriorityGraphic(newVal);
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(centreBox));
                }
            });
            return cell;
        });
        createMainBreak.setOnAction(e -> {
            CreateMainBreakDialogController controller = showDialogWindow("/fxmlFiles/dialog/createMainBreakDialog.fxml");
            controller.setListMainBreaksTabController(this);
            controller.setButtonVisible(true, false,
                    false, false,
                    false, false);
            log.info("Перешел на страницу создания основного тормоза");
        });
        changeMainBreak.setOnAction(e -> {
            CreateMainBreakDialogController controller = showDialogWindow("/fxmlFiles/dialog/createMainBreakDialog.fxml");
            controller.setListMainBreaksTabController(this);
            controller.setMainBreak(tableMainBreaks.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(false, true,
                    false, false,
                    false, false);
            log.info("Перешел на страницу редактирования основного тормоза");
        });
        tableMainBreaks.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateMainBreakDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createMainBreakDialog.fxml");
                controller.setButtonVisible(false, false,
                        false, false,
                        false, false);
                controller.setMainBreak(tableMainBreaks.getSelectionModel().getSelectedItem());
            }
        });
        deleteMainBreak.setOnAction(e -> {
            checkingMatches();
            log.info("Удалил основной тормоз");
        });
    }

    /** Метод обновляет запись остатусе основного тормоза,установлено ли оно на самолет или нет №*/
    private void updateInstalledMainBreak() {
        for (MainBreak mainBreak : SaveData.mainBreaksList) {
            boolean isInstalled = true;
            for (Aircraft aircraft : SaveData.aircraftList) {
                if (mainBreak.getSerialNumber().equals(aircraft.getLeftMainBrake().getSerialNumber())
                        || mainBreak.getSerialNumber().equals(aircraft.getRightMainBrake().getSerialNumber())) {
                    isInstalled = false;
                    mainBreak.setAircraftNumberInstalled(aircraft.getAircraftNumber());
                }
            }
            if (isInstalled) {
                mainBreak.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
        }
        FilesWriter.serialization(SaveData.mainBreaksList, MainBreak.class);
    }
    /**
     * Метод обновляет значение поля, в котором хранится номер самолета, на котором установлен данный посновной тормоз.
     */
    public void updateTableMainBreaks() {
        updateList(SaveData.mainBreaksList,
                tableMainBreaks,
                TextConstants.MAIN_BREAK_TEXT);
    }

    /**
     * Метод проверяет на совпадение основные тормоза из ограниченного ресурса и основные тормоза, установленные на самолеты.
     * Если совпадение найдено, открывается всплывающее окно с предупреждением,
     * если совпадения нет, выбранный  основной тормоз удаляется из списка ограниченного ресурса,
     * изменения сериализуются в файл с основными тормозами
     */
    private void checkingMatches() {
        String sideNUmber = "";
        boolean isMatches = true;
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftMainBrake().getSerialNumber().equals(
                    tableMainBreaks.getSelectionModel().getSelectedItem().getSerialNumber())
                    || aircraft.getRightMainBrake().getSerialNumber().equals(
                    tableMainBreaks.getSelectionModel().getSelectedItem().getSerialNumber())) {
                isMatches = false;
                sideNUmber = aircraft.getAircraftNumber();
            }
        }
        if (isMatches) {
            delete(SaveData.mainBreaksList, tableMainBreaks, MainBreak.class);
        } else {
            openInformationWindow(TextConstants.MAIN_BREAK_INSTALLED + sideNUmber +
                    TextConstants.MAIN_BREAK_DELETE);
        }
    }
}

