package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import sample.controllers.tab.ListAllKsaTabController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.enums.TypesOfWorks;
import sample.works.MakeWorks;
import sample.write.WriteFile;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateKsaDialogController {

    @FXML
    private TextField numberKsa;

    @FXML
    private TextField totalStartKsa;

    @FXML
    private TextField totalRightKsa;

    @FXML
    private TextField totalLeftKsa;

    @FXML
    private TextField totalOperatingKsaHours;

    @FXML
    private TextField totalOperatingKsaMinutes;

    @FXML
    private TextField before_25hoursKsaHours;

    @FXML
    private TextField before_25hoursKsaMinutes;

    @FXML
    private TextField oilChangeKsaHours;

    @FXML
    private TextField oilChangeKsaMinutes;

    @FXML
    private Button createKsa;

    @FXML
    private Button changeKsa;

    @FXML
    private Button createKsaForAircraft;

    @FXML
    private Text alarm25Hours;

    @FXML
    private Text alarmOilChange;

    @FXML
    private Text selectionWorks;

    @FXML
    private ComboBox<TypesOfWorks> listOfWorksKsa;

    @FXML
    private Button makeWorksKsa;


    @Setter
    private ListAllKsaTabController listAllKsaTabController;

    @Setter
    private PersonalAircraftDialogController personalAircraftDialogController;

    private Ksa ksa;

    MakeWorks make = new MakeWorks();

    @FXML
    void initialize() {
        listOfWorksKsa.getItems().addAll(TypesOfWorks.WORKS_AFTER_25_HOURS, TypesOfWorks.OIL_CHANGE_OPERATIONS);
        createKsaForAircraft.setOnAction(e -> {
            addKsa();
            Stage stage = (Stage) createKsaForAircraft.getScene().getWindow();
            stage.close();
        });
        createKsa.setOnAction(e -> {
            addKsa();
            listAllKsaTabController.updateTableKsa();
            Stage stage = (Stage) createKsa.getScene().getWindow();
            stage.close();
        });
        changeKsa.setOnAction(e -> {
            updateAircraftKsa();
            changeKsa(ksa);
            listAllKsaTabController.updateTableKsa();
            Stage stage = (Stage) changeKsa.getScene().getWindow();
            stage.close();
        });
        makeWorksKsa.setOnAction(e -> {
            make.doWorkKsa(ksa, listOfWorksKsa);
            WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            update_Ksa_After_Work();
            Stage stage = (Stage) makeWorksKsa.getScene().getWindow();
            stage.close();
        });
    }
    public void setKsa(Ksa ksa) {
        this.ksa = ksa;
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
//    public Ksa returnKsa(String number) {
//        for (Ksa e : SaveData.ksaList) {
//            if (e.getSerialNumberKsa().equals(number)) {
//                return e;
//            }
//        }
//        return null;
//    }
    public void changeKsa(Ksa ksa) {
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
        listAllKsaTabController.updateTableKsa();

    }
    public void setButtonVisible(String string) {
        if(string.equals("Добавить КСА")){
            createKsa.setVisible(true);
            createKsaForAircraft.setVisible(false);
        }else if(string.equals("Изменить запись")) {
            changeKsa.setVisible(true);
            createKsaForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createKsaForAircraft.setVisible(false);
        } else if (string.equals("Выполнить работы")) {
            createKsaForAircraft.setVisible(false);
            selectionWorks.setVisible(true);
            listOfWorksKsa.setVisible(true);
            makeWorksKsa.setVisible(true);

        }
    }

    private void update_Ksa_After_Work() {
        for (Ksa a : SaveData.ksaList) {
            if (a.getSerialNumberKsa().equals(ksa.getSerialNumberKsa())) {
                make.doWorkKsa(a, listOfWorksKsa);
                WriteFile.serialization(SaveData.ksaList, Ksa.class);
            }
        }
    }
    private void updateAircraftKsa() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getKsa().getSerialNumberKsa().equals(ksa.getSerialNumberKsa())){
                changeKsa(aircraft.getKsa());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            } else {
                System.out.println("Соси бибу");
            }
        }
    }
}

