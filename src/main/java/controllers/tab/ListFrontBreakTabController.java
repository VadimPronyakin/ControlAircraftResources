package controllers.tab;

import constants.TextConstants;
import controllers.dialog.CreateFrontBreakDialogController;
import data.Aircraft;
import data.SaveData;
import data.components.limitedResource.FrontBreak;
import data.enums.TypesOfWorks;
import io.writer.FilesWriter;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;

import static service.AircraftWorks.doWorksFrontBreak;
import static utils.Utils.*;

@Slf4j
public class ListFrontBreakTabController {

    @FXML
    private Button createFrontBreak;
    @FXML
    private Button changeFrontBreak;
    @FXML
    private Button deleteFrontBreak;
    @FXML
    private TableView<FrontBreak> tableFrontBreaks;
    @FXML
    private TableColumn<FrontBreak, FrontBreak> columnFrontBreak;
    @FXML
    private TableColumn<String, String> columnInstalledFrontBreak;
    @FXML
    private Button makeWorkFrontBreak;
    @FXML
    private ComboBox<TypesOfWorks> listOfWorksFrontBreak;
    @FXML
    private TableColumn<FrontBreak, Boolean> columnNotification;

    @FXML
    void initialize() {
        updateInstalledFrontBreak();
        updateTableFrontBreak();
        columnFrontBreak.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledFrontBreak.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        columnNotification.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsNeedAttention()) {
        });
        columnNotification.setCellFactory(col -> {
            TableCell<FrontBreak, Boolean> cell = new TableCell<>();
            cell.itemProperty().addListener((obs, old, newVal) -> {
                if (newVal != null) {
                    Node centreBox = createPriorityGraphic(newVal);
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(centreBox));
                }
            });
            return cell;
        });
        listOfWorksFrontBreak.getItems().addAll(TypesOfWorks.FIRST_REPAIR_FRONT_BREAK);
        createFrontBreak.setOnAction(e -> {
            CreateFrontBreakDialogController controller = showDialogWindow("/fxmlFiles/dialog/createFrontBreakDialog.fxml");
            controller.setListFrontBreakTabController(this);
            controller.setButtonVisible(true, false, false);
            log.info("Перешел на страницу создания переднего тормоза");
        });
        changeFrontBreak.setOnAction(e -> {
            CreateFrontBreakDialogController controller = showDialogWindow("/fxmlFiles/dialog/createFrontBreakDialog.fxml");
            controller.setListFrontBreakTabController(this);
            controller.setFrontBreak(tableFrontBreaks.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(false, true, false);
            log.info("Перешел на страницу изменения переднего тормоза");
        });
        tableFrontBreaks.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateFrontBreakDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createFrontBreakDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setFrontBreak(tableFrontBreaks.getSelectionModel().getSelectedItem());
            }
        });
        deleteFrontBreak.setOnAction(e -> checkingMatches());
        makeWorkFrontBreak.setOnAction(e -> {
            try {
                doWorksFrontBreak(tableFrontBreaks.getSelectionModel().getSelectedItem(),
                        listOfWorksFrontBreak);
            } catch (NullPointerException exception) {
                openInformationWindow(TextConstants.MAKE_WORKS_AGGREGATES);
                log.error("Пользователь не выбрал передний тормоз, на котором хотел выполнить работы {}", exception.getMessage());
            }
        });
    }

    /** Метод обновляет запись остатусе переднего тормоза,установлен ли он на самолет или нет №*/
    private void updateInstalledFrontBreak() {
        for (FrontBreak frontBreak : SaveData.frontBreaksList) {
            boolean isInstalled = true;
            for (Aircraft aircraft : SaveData.aircraftList) {
                if (frontBreak.getSerialNumber().equals(aircraft.getLeftFrontBrake().getSerialNumber())
                        || frontBreak.getSerialNumber().equals(aircraft.getRightFrontBrake().getSerialNumber())) {
                    isInstalled = false;
                    frontBreak.setAircraftNumberInstalled(aircraft.getAircraftNumber());
                }
            }
            if (isInstalled) {
                frontBreak.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
        }
        FilesWriter.serialization(SaveData.frontBreaksList, FrontBreak.class);
    }

    /**
     * Метод обновляет значение поля, в котором хранится номер самолета, на котором установлен данный передний тормоз.
     */
    public void updateTableFrontBreak() {
        updateList(SaveData.frontBreaksList,
                tableFrontBreaks,
                TextConstants.FRONT_BREAK_TEXT);
    }

    /**
     * Метод проверяет на совпадение передние тормоза из ограниченного ресурса и передние тормоза, установленные на самолеты.
     * Если совпадение найдено, открывается всплывающее окно с предупреждением,
     * если совпадения нет, выбранный  передний тормоз удаляется из списка ограниченного ресурса,
     * изменения сериализуются в файл с передними тормозами
     */
    private void checkingMatches() {
        String sideNUmber = "";
        boolean isMatches = true;
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftFrontBrake().getSerialNumber().equals(
                    tableFrontBreaks.getSelectionModel().getSelectedItem().getSerialNumber())
                    || aircraft.getRightFrontBrake().getSerialNumber().equals(
                    tableFrontBreaks.getSelectionModel().getSelectedItem().getSerialNumber())) {
                isMatches = false;
                sideNUmber = aircraft.getAircraftNumber();
            }
        }
        if (isMatches) {
            delete(SaveData.frontBreaksList, tableFrontBreaks, FrontBreak.class);
        } else {
            openInformationWindow(TextConstants.FRONT_BREAK_INSTALLED + sideNUmber +
                    TextConstants.FRONT_BREAK_DELETE);
        }
    }
}


