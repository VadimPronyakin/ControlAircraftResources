package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import sample.data.SaveData;
import sample.data.components.Engine;
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

    private FrontBreak frontBreak;

    @FXML
    void initialize() {

        createFrontBreak.setOnAction(e -> addFrontBreak());
        changeFrontBreak.setOnAction(e -> changeFrontBreak());
    }

    void setFrontBreak(String number){
        this.frontBreak = returnFrontBreak(number);
        numberFrontBreak.setText(frontBreak.getSerialNumber());
        first_Repair_FrontBreak.setText(String.valueOf(frontBreak.getResource_Reserve_Before_First_Repair()));
        totalFrontBreak.setText(String.valueOf(frontBreak.getTotalLandings()));
        replacementFrontBreak.setText(String.valueOf(frontBreak.getResource_Reserve_Before_Replacement()));
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
    public FrontBreak returnFrontBreak(String number) {
        for (FrontBreak e : SaveData.frontBreaksList) {
            if (e.getSerialNumber().equals(number)) {
                return e;
            }
        }
        return null;
    }
    public void changeFrontBreak() {
        if (StringUtils.isNotBlank(numberFrontBreak.getCharacters())
                && StringUtils.isNotBlank(first_Repair_FrontBreak.getCharacters())
                && StringUtils.isNotBlank(totalFrontBreak.getCharacters())
                && StringUtils.isNotBlank(replacementFrontBreak.getCharacters())) {
            frontBreak.setResource_Reserve_Before_First_Repair(Integer.parseInt(first_Repair_FrontBreak.getText()));
            frontBreak.setResource_Reserve_Before_Replacement(Integer.parseInt(replacementFrontBreak.getText()));
            frontBreak.setSerialNumber(numberFrontBreak.getText());
            frontBreak.setTotalLandings(Integer.parseInt(totalFrontBreak.getText()));
        }
        WriteFile.serialization(SaveData.frontBreaksList, FrontBreak.class);
    }
}

