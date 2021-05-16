package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
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

    private FrontWheel frontWheel;

    @FXML
    void initialize() {

        createFrontWheel.setOnAction(e -> addFrontWheel());
        changeFrontWheel.setOnAction(e -> changeFrontWheel());
    }

    void setFrontWheel(String number) {
        this.frontWheel = returnFrontWheel(number);
        numberFrontWheel.setText(frontWheel.getSerialNumber());
        totalFrontWheel.setText(String.valueOf(frontWheel.getTotalLandings()));
        replacementFrontWheel.setText(String.valueOf(frontWheel.getResource_Reserve_Replacement_Wheel()));
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

    public FrontWheel returnFrontWheel(String number) {
        for (FrontWheel e : SaveData.frontWheelsList) {
            if (e.getSerialNumber().equals(number)) {
                return e;
            }
        }
        return null;
    }
    public void changeFrontWheel() {
        if (StringUtils.isNotBlank(numberFrontWheel.getCharacters())
                && StringUtils.isNotBlank(totalFrontWheel.getCharacters())
                && StringUtils.isNotBlank(replacementFrontWheel.getCharacters())) {
            frontWheel.setSerialNumber(numberFrontWheel.getText());
            frontWheel.setTotalLandings(Integer.parseInt(totalFrontWheel.getText()));
            frontWheel.setResource_Reserve_Replacement_Wheel(Integer.parseInt(replacementFrontWheel.getText()));
        }
        WriteFile.serialization(SaveData.frontWheelsList, FrontWheel.class);
    }

    void visibleButton(Button tvAnswerFour) {
        String currentText =  tvAnswerFour.getText();
        if(currentText.equals("Добавить запись")){
            createFrontWheel.setVisible(true);
        }else if(currentText.equals("Изменить запись")) {
            changeFrontWheel.setVisible(true);
        }
    }
}

