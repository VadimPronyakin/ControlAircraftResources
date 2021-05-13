package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.data.SaveData;
import sample.data.components.limitedResource.*;
import sample.write.WriteFile;

import java.net.URL;
import java.util.ResourceBundle;

public class MainBreakTabController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createMainBreak;

    @FXML
    private Button changeMainBreak;

    @FXML
    private TextField replacementMainBreak;

    @FXML
    private TextField totalMainBreak;

    @FXML
    private TextField replacement_RotatingDisks;

    @FXML
    private TextField replacement_NonRotatingDisks;

    @FXML
    private TextField replacement_PressureDisk;

    @FXML
    private TextField replacement_ReferenceDisk;

    @FXML
    private TextField numberMainBreak;

    @FXML
    void initialize() {
        createMainBreak.setOnAction(e -> addMainBreak());
    }

    private void addMainBreak() {
        MainBreak mainBreak = MainBreak.builder()
                .totalLandings(Integer.parseInt(totalMainBreak.getText()))
                .resource_Reserve_Replacement_Break(Integer.parseInt(replacementMainBreak.getText()))
                .resource_Reserve_Replacement_RotatingDisks(Integer.parseInt(replacement_RotatingDisks.getText()))
                .resource_Reserve_Replacement_NonRotatingDisks(Integer.parseInt(replacement_NonRotatingDisks.getText()))
                .resource_Reserve_Replacement_PressureDisk(Integer.parseInt(replacement_PressureDisk.getText()))
                .resource_Reserve_Replacement_ReferenceDisk(Integer.parseInt(replacement_ReferenceDisk.getText()))
                .serialNumber(numberMainBreak.getText())
                .build();
        SaveData.mainBreaksList.add(mainBreak);
        WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);
    }
}

