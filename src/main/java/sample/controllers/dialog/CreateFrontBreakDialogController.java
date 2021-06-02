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
import sample.write.WriteFile;

import static sample.builder.Builder.createFrontBreak;
import static sample.notification.Notification.notificationFrontBreak;
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
        TextField[] fields = new TextField[]{totalFrontBreak,
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
    /** Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем */
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
    /** Метод синхронизирует передние тормоза, из списка огранич. ресурса и передние тормоза, установленные на самолете
     * если мы вносим изменения в передний тормоз в списке огранич.ресурса,который установлен на какой либо самолет,
     * то изменения будут автоматически внесены в этот передний тормоз на самолете */
    private void updateAircraftFrontBreaks() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftFrontBrake().getSerialNumber().equals(frontBreak.getSerialNumber())) {
                changeFrontBreak(aircraft.getLeftFrontBrake());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
             if (aircraft.getRightFrontBrake().getSerialNumber().equals(frontBreak.getSerialNumber())) {
                changeFrontBreak(aircraft.getRightFrontBrake());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
        }
    }
}

