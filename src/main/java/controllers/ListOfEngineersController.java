package controllers;

import constants.TextConstants;
import controllers.dialog.PersonalEngineerDialogController;
import data.Aircraft;
import data.Engineer;
import data.SaveData;
import data.enums.Rank;
import io.writer.FilesWriter;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.StringUtils;

import static builder.Builder.createEngineer;
import static service.DuplicateProtection.engineerProtection;
import static utils.Utils.*;


public class ListOfEngineersController {

    @FXML
    private Button returnHomePage;
    @FXML
    private ComboBox<Rank> listOfRanks;
    @FXML
    private TextField inputEngineerName;
    @FXML
    private Button createNewEngineer;
    @FXML
    private Button changeEngineer;
    @FXML
    private Button deleteEngineer;
    @FXML
    private TableView<Engineer> tableView;
    @FXML
    private TableColumn<Rank, Rank> columnRank;
    @FXML
    private TableColumn<String, String> columnFullName;
    @FXML
    private ChoiceBox<String> listOfLinks;
    @FXML
    private TextField inputNtzName;

    @FXML
    void initialize() {
        updateList(SaveData.engineersList, tableView, TextConstants.ENGINEER_TEXT);
        listOfLinks.getItems().addAll("№1", "№2", "№3");
        columnRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        columnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        deleteEngineer.setOnAction(e -> checkingMatches());
        returnHomePage.setOnAction(e -> openNewScene("/fxmlFiles/sample.fxml", returnHomePage));
        listOfRanks.getItems().addAll(Rank.LIEUTENANT, Rank.ST_LIEUTENANT, Rank.CAPTAIN, Rank.MAJOR);
        createNewEngineer.setOnAction(e -> {
            if (engineerProtection(inputEngineerName, listOfRanks)) {
                createEngineer(listOfRanks, listOfLinks,
                        inputEngineerName, inputNtzName,
                        tableView);
            }
        });
        changeEngineer.setOnAction(e -> {
            if (engineerProtection(inputEngineerName, listOfRanks)) {
                changeEngineer();
            }
        });
        tableView.setOnMouseClicked(e -> {
            completionFields();
            if (e.getClickCount() ==2) {
                PersonalEngineerDialogController controller = showDialogWindow(
                        "/fxmlFiles/dialog/personalEngineerDialog.fxml");
                controller.setEngineer(tableView.getSelectionModel().getSelectedItem());
            }
        });
    }

    /**
     * Метод позволяет вносить изменения в данные о существующих инженерах(ак)
     */
    private void changeEngineer() {
        if (StringUtils.isNotBlank(inputEngineerName.getCharacters())
                && StringUtils.isNotBlank(inputNtzName.getCharacters())
                && listOfRanks.getSelectionModel().getSelectedItem() != null
                && listOfLinks.getSelectionModel().getSelectedItem() != null) {
            for (Aircraft aircraft : SaveData.aircraftList) {
                if (aircraft.getIak().getFullName().equals(
                        tableView.getSelectionModel().getSelectedItem().getFullName())) {
                    aircraft.getIak().setFullName(inputEngineerName.getText());
                    aircraft.getIak().setNtzFullName(inputNtzName.getText());
                    aircraft.getIak().setRank(listOfRanks.getSelectionModel().getSelectedItem());
                    aircraft.getIak().setLink(listOfLinks.getSelectionModel().getSelectedItem());
                    aircraft.setFullNameEngineer(inputEngineerName.getText());
                }
            }
            tableView.getSelectionModel().getSelectedItem().setFullName(inputEngineerName.getText());
            tableView.getSelectionModel().getSelectedItem().setNtzFullName(inputNtzName.getText());
            tableView.getSelectionModel().getSelectedItem().setRank(listOfRanks.getSelectionModel().getSelectedItem());
            tableView.getSelectionModel().getSelectedItem().setLink(listOfLinks.getSelectionModel().getSelectedItem());
        }
        FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
        FilesWriter.serialization(SaveData.engineersList, Engineer.class);
        updateList(SaveData.engineersList, tableView, TextConstants.ENGINEER_TEXT);
    }

    /**
     * Метод проверяет инженера(ак) на наличие закрепленных за ним самолетов.
     * Если за инженером(ак) закпреплено 1 или более ВС, открывается всплывающее окно с предупреждением,
     * если закрепленных ВС нет, выбранный инженер(ак) удаляется из списка инженеров, изменения сериализуются в файл с инженерами
     */
    private void checkingMatches() {
        boolean isMatches = true;
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getFullNameEngineer().equals(
                    tableView.getSelectionModel().getSelectedItem().getFullName())) {
                isMatches = false;
            }
        }
        if (isMatches) {
            delete(SaveData.engineersList, tableView, Engineer.class);
        } else {
            openInformationWindow(TextConstants.ENGINEER_ASSIGNED);
        }
    }

    /**
     * Метод предзаполненяет поля данными выбранного инженера(ак)
     */
    private void completionFields() {
        inputEngineerName.setText(tableView.getSelectionModel().getSelectedItem().getFullName());
        inputNtzName.setText(tableView.getSelectionModel().getSelectedItem().getNtzFullName());
        listOfLinks.setValue(tableView.getSelectionModel().getSelectedItem().getLink());
        listOfRanks.setValue(tableView.getSelectionModel().getSelectedItem().getRank());
    }
}
