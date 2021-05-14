package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import sample.data.SaveData;
import sample.data.components.Ksa;
import sample.write.WriteFile;


public class KsaTabController {

    @FXML
    private Button createKsa;

    @FXML
    private Button changeKsa;

    @FXML
    private TextField totalStartKsa;

    @FXML
    private TextField totalOperatingKsaHours;

    @FXML
    private TextField totalRightKsa;

    @FXML
    private TextField totalLeftKsa;

    @FXML
    private TextField before_25hoursKsaHours;

    @FXML
    private TextField oilChangeKsaHours;

    @FXML
    private TextField numberKsa;

    @FXML
    private TextField totalOperatingKsaMinutes;

    @FXML
    private TextField before_25hoursKsaMinutes;

    @FXML
    private TextField oilChangeKsaMinutes;

    private Ksa ksa;


    @FXML
    void initialize() {

        createKsa.setOnAction(e -> addKsa());
        changeKsa.setOnAction(e -> changeKsa());
    }
    void setKsa(String number) {
        this.ksa = returnKsa(number);
        numberKsa.setText(ksa.getSerialNumberKsa());
        totalStartKsa.setText(String.valueOf(ksa.getTotal_Starting_Ksa_Count()));
        totalRightKsa.setText(String.valueOf(ksa.getStarting_Right_Count()));
        totalLeftKsa.setText(String.valueOf(ksa.getStarting_Left_Count()));
        totalOperatingKsaHours.setText(String.valueOf(ksa.getTotal_Operating_Time() / 60));
        totalOperatingKsaMinutes.setText(String.valueOf(ksa.getTotal_Operating_Time() % 60));
        before_25hoursKsaHours.setText(String.valueOf(ksa.getResource_Reserve_Before_25hours() / 60));
        before_25hoursKsaMinutes.setText(String.valueOf(ksa.getResource_Reserve_Before_25hours() % 60));
        oilChangeKsaHours.setText(String.valueOf(ksa.getOilChange() / 60));
        oilChangeKsaMinutes.setText(String.valueOf(ksa.getOilChange() % 60));
    }

    private void addKsa() {
        Ksa ksa = Ksa.builder()
                .total_Operating_Time((Integer.parseInt(totalOperatingKsaHours.getText()) * 60) +
                        Integer.parseInt(totalOperatingKsaMinutes.getText()))
                .starting_Left_Count(Integer.parseInt(totalLeftKsa.getText()))
                .starting_Right_Count(Integer.parseInt(totalRightKsa.getText()))
                .total_Starting_Ksa_Count(Integer.parseInt(totalStartKsa.getText()))
                .resource_Reserve_Before_25hours((Integer.parseInt(before_25hoursKsaHours.getText()) * 60) +
                        Integer.parseInt(before_25hoursKsaMinutes.getText()))
                .oilChange((Integer.parseInt(oilChangeKsaHours.getText()) * 60) +
                        Integer.parseInt(oilChangeKsaMinutes.getText()))
                .serialNumberKsa(numberKsa.getText())
                .build();
        SaveData.ksaList.add(ksa);
        WriteFile.serialization(SaveData.ksaList, Ksa.class);
    }
    public Ksa returnKsa(String number) {
        for (Ksa e : SaveData.ksaList) {
            if (e.getSerialNumberKsa().equals(number)) {
                return e;
            }
        }
        return null;
    }
    public void changeKsa() {
        if (StringUtils.isNotBlank(before_25hoursKsaHours.getCharacters())
                && StringUtils.isNotBlank(before_25hoursKsaMinutes.getCharacters())
                && StringUtils.isNotBlank(oilChangeKsaHours.getCharacters())
                && StringUtils.isNotBlank(oilChangeKsaMinutes.getCharacters())
                && StringUtils.isNotBlank(totalStartKsa.getCharacters())
                && StringUtils.isNotBlank(totalLeftKsa.getCharacters())
                && StringUtils.isNotBlank(totalRightKsa.getCharacters())
                && StringUtils.isNotBlank(numberKsa.getCharacters())) {
            ksa.setSerialNumberKsa(numberKsa.getText());
            ksa.setTotal_Starting_Ksa_Count(Integer.parseInt(totalStartKsa.getText()));
            ksa.setStarting_Left_Count(Integer.parseInt(totalLeftKsa.getText()));
            ksa.setStarting_Right_Count(Integer.parseInt(totalRightKsa.getText()));
            ksa.setResource_Reserve_Before_25hours((Integer.parseInt(before_25hoursKsaHours.getText()) * 60) +
                   Integer.parseInt(before_25hoursKsaMinutes.getText()));
            ksa.setOilChange((Integer.parseInt(oilChangeKsaHours.getText()) * 60) +
                   Integer.parseInt(oilChangeKsaMinutes.getText()));
        }
        WriteFile.serialization(SaveData.ksaList, Ksa.class);
    }
}

