package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.data.SaveData;
import sample.data.components.limitedResource.FrontBreak;
import sample.write.WriteFile;


public class FrontBreakTabController {

    @FXML
    private Button createFrontBreak;

    @FXML
    private Button changeFrontBreak;

    @FXML
    private TextField first_Repair_FrontBreak;

    @FXML
    private TextField totalFrontBreak;

    @FXML
    private TextField replacementFrontBreak;

    @FXML
    private TextField numberFrontBreak;

    @FXML
    void initialize() {

        createFrontBreak.setOnAction(e -> addFrontBreak());
    }

    private void addFrontBreak() {
        FrontBreak frontBreak = FrontBreak.builder()
                .totalLandings(Integer.parseInt(totalFrontBreak.getText()))
                .resource_Reserve_Before_First_Repair(Integer.parseInt(first_Repair_FrontBreak.getText()))
                .resource_Reserve_Before_Replacement(Integer.parseInt(replacementFrontBreak.getText()))
                .serialNumber(numberFrontBreak.getText())
                .build();
        SaveData.frontBreaksList.add(frontBreak);
        WriteFile.serialization(SaveData.frontBreaksList, FrontBreak.class);
    }

}

