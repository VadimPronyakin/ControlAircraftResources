package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField totalOperatingKsa;

    @FXML
    private TextField totalRightKsa;

    @FXML
    private TextField totalLeftKsa;

    @FXML
    private TextField before_25hoursKsa;

    @FXML
    private TextField oilChangeKsa;

    @FXML
    private TextField numberKsa;


    @FXML
    void initialize() {

        createKsa.setOnAction(e -> addKsa());
    }

    private void addKsa() {
        Ksa ksa = Ksa.builder()
                .total_Operating_Time(Integer.parseInt(totalOperatingKsa.getText()))
                .starting_Left_Count(Integer.parseInt(totalLeftKsa.getText()))
                .starting_Right_Count(Integer.parseInt(totalRightKsa.getText()))
                .total_Starting_Ksa_Count(Integer.parseInt(totalStartKsa.getText()))
                .resource_Reserve_Before_25hours(Integer.parseInt(before_25hoursKsa.getText()))
                .oilChange(Integer.parseInt(oilChangeKsa.getText()))
                .serialNumber(numberKsa.getText())
                .build();
        SaveData.ksaList.add(ksa);
        WriteFile.serialization(SaveData.ksaList, Ksa.class);
    }
}

