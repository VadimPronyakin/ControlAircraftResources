package controllers.tab;

import constants.TextConstants;
import controllers.dialog.CreateCylinderDialogController;
import data.Aircraft;
import data.SaveData;
import data.components.limitedResource.CylinderOfRetractionExtension;
import data.enums.TypesOfWorks;
import io.writer.FilesWriter;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;

import static service.AircraftWorks.doWorksCylinder;
import static utils.Utils.*;

@Slf4j
public class ListCylindersTabController {

    @FXML
    private TableView<CylinderOfRetractionExtension> tableCylinders;
    @FXML
    private TableColumn<CylinderOfRetractionExtension, CylinderOfRetractionExtension> columnNumberCylinder;
    @FXML
    private TableColumn<String, String> columnInstalledCylinder;
    @FXML
    private Button createNewCylinder;
    @FXML
    private Button changeCylinder;
    @FXML
    private Button deleteCylinder;
    @FXML
    private Button makeWorkCylinder;
    @FXML
    private ComboBox<TypesOfWorks> listOfWorksCylinder;
    @FXML
    private TableColumn<CylinderOfRetractionExtension, Boolean> columnNotification;


    @FXML
    void initialize() {
        updateInstalledCylinder();
        updateTableCylinders();
        columnNumberCylinder.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledCylinder.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        columnNotification.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsNeedAttention()) {
        });
        columnNotification.setCellFactory(col -> {
            TableCell<CylinderOfRetractionExtension, Boolean> cell = new TableCell<>();
            cell.itemProperty().addListener((obs, old, newVal) -> {
                if (newVal != null) {
                    Node centreBox = createPriorityGraphic(newVal);
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(centreBox));
                }
            });
            return cell;
        });
        listOfWorksCylinder.getItems().addAll(TypesOfWorks.FIRST_REPAIR_CYLINDER, TypesOfWorks.SECOND_REPAIR_CYLINDER);
        createNewCylinder.setOnAction(e -> {
            CreateCylinderDialogController controller = showDialogWindow("/fxmlFiles/dialog/createCylinderDialog.fxml");
            controller.setListCylindersTabController(this);
            controller.setButtonVisible(true, false, false);
            log.info("Перешел на страницу создания цилиндра подкоса");
        });
        changeCylinder.setOnAction(e -> {
            CreateCylinderDialogController controller = showDialogWindow("/fxmlFiles/dialog/createCylinderDialog.fxml");
            controller.setListCylindersTabController(this);
            controller.setCylinder(tableCylinders.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(false, true, false);
        });
        tableCylinders.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateCylinderDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createCylinderDialog.fxml");
                controller.setButtonVisible(false, false, false);
                controller.setCylinder(tableCylinders.getSelectionModel().getSelectedItem());
                log.info("Перешел на страницу редактирования цилиндра подкоса");
            }
        });
        deleteCylinder.setOnAction(e -> checkingMatches());
        //todo: запретить выполнять второй рмеонт,пока не выполнен первый
        makeWorkCylinder.setOnAction(e -> {
            try {
                doWorksCylinder(tableCylinders.getSelectionModel().getSelectedItem(),
                        listOfWorksCylinder);
            } catch (NullPointerException exception) {
                openInformationWindow(TextConstants.MAKE_WORKS_AGGREGATES);
                log.error("Пользователь не выбрал цилиндр подкоса, на котором хотел выполнить работы {}", exception.getMessage());
            }
        });
    }

    /** Метод обновляет запись остатусе цилиндра подкоса,установлен ли он на самолет или нет */
    private void updateInstalledCylinder() {
        for (CylinderOfRetractionExtension cylinder : SaveData.cylindersList) {
            boolean isInstalled = true;
            for (Aircraft aircraft : SaveData.aircraftList) {
                if (cylinder.getSerialNumber().equals(aircraft.getLeftMainCylinder().getSerialNumber())
                        || cylinder.getSerialNumber().equals(aircraft.getRightMainCylinder().getSerialNumber())
                        || cylinder.getSerialNumber().equals(aircraft.getFrontCylinder().getSerialNumber())) {
                    isInstalled = false;
                    cylinder.setAircraftNumberInstalled(aircraft.getAircraftNumber());
                }
            }
            if (isInstalled) {
                cylinder.setAircraftNumberInstalled(TextConstants.NOT_INSTALLED_ON_AIRCRAFT);
            }
        }
        FilesWriter.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
    }

    /**
     * Метод обновляет содержимое tableCylinder после изменений.
     */
    public void updateTableCylinders() {
        updateList(SaveData.cylindersList,
                tableCylinders,
                TextConstants.CYLINDER_TEXT);
    }

    /**
     * Метод проверяет на совпадение цилиндры подкоса из ограниченного ресурса и цилиндры подкоса, установленные на самолеты.
     * Если совпадение найдено, открывается всплывающее окно с предупреждением,
     * если совпадения нет, выбранный  цилиндр подкоса удаляется из списка ограниченного ресурса,
     * изменения сериализуются в файл с цилиндрами подкоса
     */
    private void checkingMatches() {
        String sideNUmber = "";
        boolean isMatches = true;
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftMainCylinder().getSerialNumber().equals(
                    tableCylinders.getSelectionModel().getSelectedItem().getSerialNumber())
                    || aircraft.getRightMainCylinder().getSerialNumber().equals(
                    tableCylinders.getSelectionModel().getSelectedItem().getSerialNumber())
                    || aircraft.getFrontCylinder().getSerialNumber().equals(
                    tableCylinders.getSelectionModel().getSelectedItem().getSerialNumber())) {
                isMatches = false;
                sideNUmber = aircraft.getAircraftNumber();
            }
        }
        if (isMatches) {
            delete(SaveData.cylindersList, tableCylinders, CylinderOfRetractionExtension.class);
        } else {
            openInformationWindow(TextConstants.CYLINDER_INSTALLED + sideNUmber +
                    TextConstants.CYLINDER_DELETE);
        }
    }
}

