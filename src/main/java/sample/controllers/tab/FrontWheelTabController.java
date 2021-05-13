package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.data.SaveData;
import sample.data.components.limitedResource.FrontWheel;
import sample.write.WriteFile;


public class FrontWheelTabController {

    @FXML
    private Button createFrontWheel;

    @FXML
    private Button changeFrontWheel;

    @FXML
    private TextField replacementFrontWheel;

    @FXML
    private TextField totalFrontWheel;

    @FXML
    private TextField numberFrontWheel;

    @FXML
    void initialize() {

        createFrontWheel.setOnAction(e -> addFrontWheel());
    }

    private void addFrontWheel() {
        FrontWheel frontWheel = FrontWheel.builder()
                .totalLandings(Integer.parseInt(totalFrontWheel.getText()))
                .resource_Reserve_Replacement_Wheel(Integer.parseInt(replacementFrontWheel.getText()))
                .serialNumber(numberFrontWheel.getText())
                .build();
        SaveData.frontWheelsList.add(frontWheel);
        WriteFile.serialization(SaveData.frontWheelsList, FrontWheel.class);
    }
}

