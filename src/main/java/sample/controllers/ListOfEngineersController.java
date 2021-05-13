package sample.controllers;

import org.apache.commons.lang3.StringUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.constants.TextConstants;
import sample.data.Engineer;
import sample.data.SaveData;
import sample.data.enums.Rank;
import sample.delete.DeleteObject;
import sample.openNewScene.OpenNewScene;
import sample.update.UpdateList;
import sample.write.WriteFile;


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
    void initialize() {
        UpdateList.updateList(SaveData.engineersList, tableView, Engineer.class, TextConstants.ENGINEER_TEXT);
        OpenNewScene open = new OpenNewScene();
        columnRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        columnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        deleteEngineer.setOnAction(e -> DeleteObject.delete(SaveData.engineersList, tableView, Engineer.class));
        returnHomePage.setOnAction(e -> open.openNewScene("/sample/fxmlFiles/sample.fxml", returnHomePage));
        listOfRanks.getItems().addAll(Rank.LIEUTENANT, Rank.ST_LIEUTENANT, Rank.CAPTAIN, Rank.MAJOR);
        createNewEngineer.setOnAction(e -> addEngineer());
        changeEngineer.setOnAction(e -> editEngineer());
    }
    private void addEngineer() {
        Engineer engineer = Engineer.builder()
                .rank(listOfRanks.getValue())
                .fullName(inputEngineerName.getText())
                .build();
        SaveData.engineersList.add(engineer);
        tableView.getItems().add(engineer);
        WriteFile.serialization(SaveData.engineersList, Engineer.class);
    }
    private void editEngineer() {
        if (StringUtils.isNotBlank(inputEngineerName.getCharacters())
            && listOfRanks.getSelectionModel().getSelectedItem() != null) {
        tableView.getSelectionModel().getSelectedItem().setFullName(inputEngineerName.getText());
        tableView.getSelectionModel().getSelectedItem().setRank(listOfRanks.getSelectionModel().getSelectedItem());
        }
        WriteFile.serialization(SaveData.engineersList, Engineer.class);
        UpdateList.updateList(SaveData.engineersList, tableView, Engineer.class, TextConstants.ENGINEER_TEXT);
    }
}


//EnumSet.allOf(Rank.class)
//                .stream()
//                .map(e -> e.getDescription())
//                .collect(Collectors.toList()));