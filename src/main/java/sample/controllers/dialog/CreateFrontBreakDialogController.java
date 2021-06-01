package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import sample.controllers.tab.ListFrontBreakTabController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.limitedResource.FrontBreak;
import sample.setBoolean.SetBooleanValue;
import sample.write.WriteFile;

import java.util.ArrayList;
import java.util.List;

import static sample.builder.Builder.createFrontBreak;
import static sample.notification.NotificationAircraft.notificationFrontBreak;
import static sample.setBoolean.SetBooleanValue.setBooleanValueFrontBreak;
import static sample.utils.Utils.checkInput;

public class CreateFrontBreakDialogController {

    @FXML
    private TextField numberFrontBreak;

    @FXML
    private TextField totalFrontBreak;

    @FXML
    private TextField first_Repair_FrontBreak;

    @FXML
    private TextField replacementFrontBreak;

    @FXML
    private Button createFrontBreak;

    @FXML
    private Button changeFrontBreak;

    @FXML
    private Button createFrontBreakForAircraft;

    @FXML
    private Text alarmFirstRepair;

    @FXML
    private Text alarmReplacement;

    @Setter
    private ListFrontBreakTabController listFrontBreakTabController;

    @Setter
    private PersonalAircraftDialogController personalAircraftDialogController;

    private FrontBreak frontBreak;

    @FXML
    void initialize() {
       createFrontBreakForAircraft.setOnAction(e -> {
           addFrontBreak();
           Stage stage = (Stage) createFrontBreakForAircraft.getScene().getWindow();
           stage.close();
       });
        createFrontBreak.setOnAction(e -> {
            addFrontBreak();
            listFrontBreakTabController.updateTableFrontBreak();
            Stage stage = (Stage) createFrontBreak.getScene().getWindow();
            stage.close();
        });
        changeFrontBreak.setOnAction(e -> {
            updateAircraftFrontBreaks();
            changeFrontBreak(frontBreak);
            Stage stage = (Stage) changeFrontBreak.getScene().getWindow();
            stage.close();
        });
    }

   public void setFrontBreak(FrontBreak frontBreak) {
        this.frontBreak = frontBreak;
        numberFrontBreak.setText(frontBreak.getSerialNumber());
        first_Repair_FrontBreak.setText(String.valueOf(frontBreak.getResource_Reserve_Before_First_Repair()));
        totalFrontBreak.setText(String.valueOf(frontBreak.getTotalLandings()));
        replacementFrontBreak.setText(String.valueOf(frontBreak.getResource_Reserve_Before_Replacement()));
       notificationFrontBreak(frontBreak, alarmFirstRepair, alarmReplacement);
    }

    private void addFrontBreak() {
        TextField[] fields = new TextField[] {totalFrontBreak,
                first_Repair_FrontBreak,
                replacementFrontBreak,
                numberFrontBreak};
        createFrontBreak(fields);
    }

    public void changeFrontBreak(FrontBreak frontBreak) {
        if (checkInput(numberFrontBreak,
                              first_Repair_FrontBreak,
                              totalFrontBreak,
                              replacementFrontBreak)) {
            frontBreak.setResource_Reserve_Before_First_Repair(Integer.parseInt(first_Repair_FrontBreak.getText()));
            frontBreak.setResource_Reserve_Before_Replacement(Integer.parseInt(replacementFrontBreak.getText()));
            frontBreak.setSerialNumber(numberFrontBreak.getText());
            frontBreak.setTotalLandings(Integer.parseInt(totalFrontBreak.getText()));
        }
        frontBreak.setIsNeedAttention(setBooleanValueFrontBreak(frontBreak));
        WriteFile.serialization(SaveData.frontBreaksList, FrontBreak.class);
        listFrontBreakTabController.updateTableFrontBreak();

    }

   public void setButtonVisible(String string) {
        if (string.equals("Добавить тормоз")) {
            createFrontBreak.setVisible(true);
            changeFrontBreak.setVisible(false);
            createFrontBreakForAircraft.setVisible(false);
        } else if (string.equals("Изменить запись")) {
            changeFrontBreak.setVisible(true);
            createFrontBreak.setVisible(false);
            createFrontBreakForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createFrontBreak.setVisible(false);
            changeFrontBreak.setVisible(false);
            createFrontBreakForAircraft.setVisible(false);
       }

    }
    private void updateAircraftFrontBreaks() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if ( aircraft.getLeftFrontBrake() == null) {
                System.out.println("Нет левого переднего тормоза на самолете");
            } else if (aircraft.getLeftFrontBrake().getSerialNumber().equals(frontBreak.getSerialNumber())){
                changeFrontBreak(aircraft.getLeftFrontBrake());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
            if (aircraft.getRightFrontBrake() == null) {
                System.out.println("Нет правого переднего тормоза на самолете");
            } else if (aircraft.getRightFrontBrake().getSerialNumber().equals(frontBreak.getSerialNumber())) {
                changeFrontBreak(aircraft.getRightFrontBrake());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
        }
    }
}

