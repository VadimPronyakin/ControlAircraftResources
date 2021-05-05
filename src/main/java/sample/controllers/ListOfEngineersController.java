package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.createNewObject.CreateEngineer;
import sample.data.Engineer;
import sample.data.SaveData;
import sample.data.enums.Rank;
import sample.openNewScene.OpenNewScene;
import sample.output.OutputFileStream;

import java.net.URL;
import java.util.ResourceBundle;

public class ListOfEngineersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        OpenNewScene open = new OpenNewScene();
        columnRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        columnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        deleteEngineer.setOnAction(e -> deleteEngineer());
        returnHomePage.setOnAction(e -> {
            open.openNewScene("/sample/fxmlFiles/sample.fxml", returnHomePage);
        });
        listOfRanks.getItems().addAll(Rank.LIEUTENANT, Rank.ST_LIEUTENANT, Rank.CAPTAIN, Rank.MAJOR);
        createNewEngineer.setOnAction(e -> addEngineer());
    }
    private void deleteEngineer() {
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
        tableView.refresh();
        SaveData.engineersList.remove(tableView.getSelectionModel().getSelectedItem());
        OutputFileStream.serialization(SaveData.engineersList);
    }
    private void addEngineer() {
        Engineer engineer = CreateEngineer.createNewEngineer(listOfRanks.getValue(), inputEngineerName.getText());
        SaveData.engineersList.add(engineer);
        tableView.getItems().add(engineer);
        OutputFileStream.serialization(SaveData.engineersList);

    }
}


//EnumSet.allOf(Rank.class)
//                .stream()
//                .map(e -> e.getDescription())
//                .collect(Collectors.toList()));