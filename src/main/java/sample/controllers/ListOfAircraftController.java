package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.openNewScene.OpenNewScene;

public class ListOfAircraftController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnHomePage;

    @FXML
    private Button createNewAircraft;

    @FXML
    private Button changeAircraft;

    @FXML
    private Button deleteAircraft;

    @FXML
    private TextField inputAircraftNumber;

    @FXML
    void initialize() {
        OpenNewScene open = new OpenNewScene();
        returnHomePage.setOnAction(event -> {
            open.openNewScene("/sample/fxmlFiles/sample.fxml", returnHomePage);
        });
        createNewAircraft.setOnAction(e -> {
            System.out.println(inputAircraftNumber.getText());
        });
    }
}

