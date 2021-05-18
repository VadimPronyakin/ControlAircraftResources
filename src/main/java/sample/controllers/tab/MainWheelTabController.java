package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
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
    private Button addMainWheelOnAircraft;

    private MainWheel mainWheel;

    @FXML
    void initialize() {

        createMainWheel.setOnAction(e -> addMainWheel());
        changeMainWheel.setOnAction(e -> changeMainWheel());
        addMainWheelOnAircraft.setOnAction(e -> addMainWheel());
    }

    void setMainWheel(String number) {
        this.mainWheel = returnMainWheel(number);
        totalMainWheel.setText(String.valueOf(mainWheel.getTotalLandings()));
        numberMainWheel.setText(mainWheel.getSerialNumber());
        replacementMainWheel.setText(String.valueOf(mainWheel.getResource_Reserve_Replacement_Wheel()));
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

    public MainWheel returnMainWheel(String number) {
        for (MainWheel e : SaveData.mainWheelsList) {
            if (e.getSerialNumber().equals(number)) {
                return e;
            }
        }
        return null;
    }

    public void changeMainWheel() {
        if (StringUtils.isNotBlank(numberMainWheel.getCharacters())
                && StringUtils.isNotBlank(totalMainWheel.getCharacters())
                && StringUtils.isNotBlank(replacementMainWheel.getCharacters())) {
            mainWheel.setSerialNumber(numberMainWheel.getText());
            mainWheel.setTotalLandings(Integer.parseInt(totalMainWheel.getText()));
            mainWheel.setResource_Reserve_Replacement_Wheel(Integer.parseInt(replacementMainWheel.getText()));
        }
        WriteFile.serialization(SaveData.mainWheelsList, MainWheel.class);
    }

    public void visibleButton(Button text) {
        if(text.getText().equals("Добавить запись")){
            createMainWheel.setVisible(true);
        }else if(text.getText().equals("Изменить запись")) {
            changeMainWheel.setVisible(true);
        } else if (text.getText().equals("+")) {
            addMainWheelOnAircraft.setVisible(true);
        }
    }
}

