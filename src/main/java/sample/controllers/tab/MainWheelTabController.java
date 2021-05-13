package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.data.SaveData;
import sample.data.components.limitedResource.*;
import sample.write.WriteFile;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWheelTabController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createMainWheel;

    @FXML
    private Button changeMainWheel;

    @FXML
    private TextField replacementMainWheel;

    @FXML
    private TextField totalMainWheel;

    @FXML
    private TextField numberMainWheel;

    @FXML
    void initialize() {

        createMainWheel.setOnAction(e -> addMainWheel());
    }

    private void addMainWheel() {
        MainWheel mainWheel = MainWheel.builder()
                .totalLandings(Integer.parseInt(totalMainWheel.getText()))
                .resource_Reserve_Replacement_Wheel(Integer.parseInt(replacementMainWheel.getText()))
                .serialNumber(numberMainWheel.getText())
                .build();
        SaveData.mainWheelsList.add(mainWheel);
        WriteFile.serialization(SaveData.mainWheelsList, MainWheel.class);
    }
}

