package controllers.tab;

import constants.TextConstants;
import controllers.dialog.CreateKsaDialogController;
import data.Aircraft;
import data.SaveData;
import data.components.Ksa;
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
    private TableColumn<Ksa, Boolean> columnNotification;

    @FXML
    void initialize() {
        updateTableKsa();
        columnNumberKsa.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnInstalledKsa.setCellValueFactory(new PropertyValueFactory<>("aircraftNumberInstalled"));
        columnNotification.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsNeedAttention()) {
        });
        columnNotification.setCellFactory(col -> {
            TableCell<Ksa, Boolean> cell = new TableCell<>();
            cell.itemProperty().addListener((obs, old, newVal) -> {
                if (newVal != null) {
                    Node centreBox = createPriorityGraphic(newVal);
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(centreBox));
                }
            });
            return cell;
        });
        createNewKsa.setOnAction(e -> {
            CreateKsaDialogController controller = showDialogWindow("/fxmlFiles/dialog/createKsaDialog.fxml");
            controller.setListAllKsaTabController(this);
            controller.setButtonVisible(true, false,
                    false, false,
                    false, false);
            log.info("Перешел на страницу создания КСА");
        });
        changeKsa.setOnAction(e -> {
            CreateKsaDialogController controller = showDialogWindow("/fxmlFiles/dialog/createKsaDialog.fxml");
            controller.setListAllKsaTabController(this);
            controller.setKsa(tableKsa.getSelectionModel().getSelectedItem());
            controller.setButtonVisible(false, true,
                    false, false,
                    false, false);
            log.info("Перешел на страницу изменения КСА");
        });
        tableKsa.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CreateKsaDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/createKsaDialog.fxml");
                controller.setButtonVisible(false, false,
                        false, false,
                        false, false);
                controller.setKsa(tableKsa.getSelectionModel().getSelectedItem());
            }
        });
        deleteKsa.setOnAction(e -> {
            checkingMatches();
            log.info("Перешел удалил КСА");
        });
    }

    public void updateTableKsa() {
        updateList(SaveData.ksaList, tableKsa, TextConstants.KSA_TEXT);
    }

    /**
     * Метод проверяет на совпадение КСА из списка агрегатов и КСА, установленные на самолеты.
     * Если совпадение найдено, открывается всплывающее окно с предупреждением,
     * если совпадения нет, выбранная КСА удаляется из списка агрегатов, изменения сериализуются в файл с КСА
     */
    private void checkingMatches() {
        String sideNUmber = "";
        boolean isMatches = true;
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getKsa() != null) {
                if (aircraft.getKsa().getSerialNumber().equals(
                        tableKsa.getSelectionModel().getSelectedItem().getSerialNumber())) {
                    isMatches = false;
                    sideNUmber = aircraft.getAircraftNumber();
                }
            }
        }
        if (isMatches) {
            delete(SaveData.ksaList, tableKsa, Ksa.class);
        } else {
            openInformationWindow(TextConstants.KSA_INSTALLED  + sideNUmber +
                    TextConstants.KSA_DELETE);
            log.info("Пользователь пытался удалить КСА, которая установлена нас самолете");
        }
    }
}
