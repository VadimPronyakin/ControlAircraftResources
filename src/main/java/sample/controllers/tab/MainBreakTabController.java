package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import sample.data.SaveData;
import sample.data.components.limitedResource.MainBreak;
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

    private MainBreak mainBreak;

    @FXML
    void initialize() {
        createMainBreak.setOnAction(e -> addMainBreak());
        changeMainBreak.setOnAction(e -> changeMainBreak());
    }

    void setMainBreak(String number) {
        this.mainBreak = returnMainBreak(number);
        totalMainBreak.setText(String.valueOf(mainBreak.getTotalLandings()));
        numberMainBreak.setText(mainBreak.getSerialNumber());
        replacementMainBreak.setText(String.valueOf(mainBreak.getResource_Reserve_Replacement_Break()));
        replacement_RotatingDisks.setText(String.valueOf(mainBreak.getResource_Reserve_Replacement_RotatingDisks()));
        replacement_NonRotatingDisks.setText(String.valueOf(mainBreak.getResource_Reserve_Replacement_NonRotatingDisks()));
        replacement_PressureDisk.setText(String.valueOf(mainBreak.getResource_Reserve_Replacement_PressureDisk()));
        replacement_ReferenceDisk.setText(String.valueOf(mainBreak.getResource_Reserve_Replacement_ReferenceDisk()));
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

    public MainBreak returnMainBreak(String number) {
        for (MainBreak e : SaveData.mainBreaksList) {
            if (e.getSerialNumber().equals(number))
                return e;
        }
        return null;
    }

    public void changeMainBreak() {
        if (StringUtils.isNotBlank(replacementMainBreak.getCharacters())
                && StringUtils.isNotBlank(totalMainBreak.getCharacters())
                && StringUtils.isNotBlank(replacement_NonRotatingDisks.getCharacters())
                && StringUtils.isNotBlank(replacement_RotatingDisks.getCharacters())
                && StringUtils.isNotBlank(replacement_PressureDisk.getCharacters())
                && StringUtils.isNotBlank(replacement_ReferenceDisk.getCharacters())
                && StringUtils.isNotBlank(numberMainBreak.getCharacters())) {
            mainBreak.setResource_Reserve_Replacement_Break(Integer.parseInt(replacementMainBreak.getText()));
            mainBreak.setTotalLandings(Integer.parseInt(totalMainBreak.getText()));
            mainBreak.setResource_Reserve_Replacement_NonRotatingDisks(Integer.parseInt(replacement_NonRotatingDisks.getText()));
            mainBreak.setResource_Reserve_Replacement_RotatingDisks(Integer.parseInt(replacement_RotatingDisks.getText()));
            mainBreak.setResource_Reserve_Replacement_PressureDisk(Integer.parseInt(replacement_PressureDisk.getText()));
            mainBreak.setResource_Reserve_Replacement_ReferenceDisk(Integer.parseInt(replacement_ReferenceDisk.getText()));
            mainBreak.setSerialNumber(numberMainBreak.getText());
        }
        WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);
    }

    void visibleButton(Button tvAnswerFour) {
        String currentText =  tvAnswerFour.getText();
        if(currentText.equals("Добавить запись")){
            createMainBreak.setVisible(true);
        }else if(currentText.equals("Изменить запись")) {
            changeMainBreak.setVisible(true);
        }
    }
}

