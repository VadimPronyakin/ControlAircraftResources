package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.StringUtils;
import sample.constants.TextConstants;
import sample.controllers.dialog.PersonalEngineerDialogController;
import sample.data.Engineer;
import sample.data.SaveData;
import sample.data.enums.Rank;
import sample.delete.DeleteObject;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;
import sample.write.WriteFile;

import static sample.openNewScene.OpenNewScene.showEditDialogDoubleClick;


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
        UpdateList.updateList(SaveData.engineersList, tableView, TextConstants.ENGINEER_TEXT);
        listOfLinks.getItems().addAll("№1", "№2", "№3");
        columnRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        columnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        deleteEngineer.setOnAction(e -> DeleteObject.delete(SaveData.engineersList, tableView, Engineer.class));
        returnHomePage.setOnAction(e -> OpenNewScene.openNewScene("/fxmlFiles/sample.fxml", returnHomePage));
        listOfRanks.getItems().addAll(Rank.LIEUTENANT, Rank.ST_LIEUTENANT, Rank.CAPTAIN, Rank.MAJOR);
        createNewEngineer.setOnAction(e -> addEngineer());
        changeEngineer.setOnAction(e -> changeEngineer());
        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                PersonalEngineerDialogController controller = showEditDialogDoubleClick(e,
                        "/fxmlFiles/dialog/personalEngineerDialog.fxml");
                controller.setEngineer(tableView.getSelectionModel().getSelectedItem());
                controller.updateListAircraft();
            }
        });
    }

    private void addEngineer() {
        Engineer engineer = Engineer.builder()
                .rank(listOfRanks.getValue())
                .fullName(inputEngineerName.getText())
                .link(listOfLinks.getSelectionModel().getSelectedItem())
                .ntzFullName(inputNtzName.getText())
                .build();
        SaveData.engineersList.add(engineer);
        tableView.getItems().add(engineer);
        WriteFile.serialization(SaveData.engineersList, Engineer.class);
    }

    private void changeEngineer() {
        if (StringUtils.isNotBlank(inputEngineerName.getCharacters())
                && StringUtils.isNotBlank(inputNtzName.getCharacters())
                && listOfRanks.getSelectionModel().getSelectedItem() != null
                && listOfLinks.getSelectionModel().getSelectedItem() != null) {
            tableView.getSelectionModel().getSelectedItem().setFullName(inputEngineerName.getText());
            tableView.getSelectionModel().getSelectedItem().setNtzFullName(inputNtzName.getText());
            tableView.getSelectionModel().getSelectedItem().setRank(listOfRanks.getSelectionModel().getSelectedItem());
            tableView.getSelectionModel().getSelectedItem().setLink(listOfLinks.getSelectionModel().getSelectedItem());
        }
        WriteFile.serialization(SaveData.engineersList, Engineer.class);
        UpdateList.updateList(SaveData.engineersList, tableView, TextConstants.ENGINEER_TEXT);
    }
}
