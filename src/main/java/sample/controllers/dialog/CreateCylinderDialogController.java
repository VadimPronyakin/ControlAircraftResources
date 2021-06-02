package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import sample.builder.Builder;
import sample.controllers.tab.ListCylindersTabController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.limitedResource.CylinderOfRetractionExtension;
import sample.write.WriteFile;

import static sample.notification.Notification.notificationCylinder;
import static sample.setBoolean.SetBooleanValue.setBooleanValueCylinder;
import static sample.utils.Utils.checkInput;

public class CreateCylinderDialogController {


    @FXML
    private TextField numberCylinder;

    @FXML
    private TextField totalCylinder;

    @FXML
    private TextField first_Repair_Cylinder;

    @FXML
    private TextField second_Repair_Cylinder;

    @FXML
    private TextField replacementCylinder;

    @FXML
    private Button createCylinder;

    @FXML
    private Button changeCylinder;

    @FXML
    private Button createCylinderForAircraft;

    @FXML
    private Text alarmSecondRepair;

    @FXML
    private Text alarmReplacement;

    @FXML
    private Text alarmFirstRepair;

    @Setter
    private ListCylindersTabController listCylindersTabController;

    @Setter
    private PersonalAircraftDialogController personalAircraftDialogController;

    private CylinderOfRetractionExtension cylinder;

    @FXML
    void initialize() {
        createCylinderForAircraft.setOnAction(e -> {
            addCylinder();
            Stage stage = (Stage) createCylinderForAircraft.getScene().getWindow();
            stage.close();
        });
        createCylinder.setOnAction(e -> {
            addCylinder();
            listCylindersTabController.updateTableCylinders();
            Stage stage = (Stage) createCylinder.getScene().getWindow();
            stage.close();
        });
        changeCylinder.setOnAction(e -> {
            changeCylinder(cylinder);
            updateAircraftCylinders();
            Stage stage = (Stage) changeCylinder.getScene().getWindow();
            stage.close();
        });
    }

    public void setCylinder(CylinderOfRetractionExtension cylinder) {
        this.cylinder = cylinder;
        first_Repair_Cylinder.setText(String.valueOf(cylinder.getResource_Reserve_Before_First_Repair()));
        totalCylinder.setText(String.valueOf(cylinder.getTotalLandings()));
        second_Repair_Cylinder.setText(String.valueOf(cylinder.getResource_Reserve_Before_Second_Repair()));
        replacementCylinder.setText(String.valueOf(cylinder.getResource_Reserve_Before_Replacement()));
        numberCylinder.setText(cylinder.getSerialNumber());
        notificationCylinder(cylinder, alarmFirstRepair, alarmSecondRepair, alarmReplacement);
    }

    private void addCylinder() {
        TextField[] fields = new TextField[]{totalCylinder,
                first_Repair_Cylinder,
                second_Repair_Cylinder,
                replacementCylinder,
                numberCylinder};
        Builder.createCylinder(fields);
    }

    public void changeCylinder(CylinderOfRetractionExtension cylinder) {
        if (checkInput(first_Repair_Cylinder,
                second_Repair_Cylinder,
                totalCylinder,
                replacementCylinder,
                numberCylinder)) {
            cylinder.setResource_Reserve_Before_First_Repair(Integer.parseInt(first_Repair_Cylinder.getText()));
            cylinder.setResource_Reserve_Before_Second_Repair(Integer.parseInt(second_Repair_Cylinder.getText()));
            cylinder.setTotalLandings(Integer.parseInt(totalCylinder.getText()));
            cylinder.setSerialNumber(numberCylinder.getText());
            cylinder.setResource_Reserve_Before_Replacement(Integer.parseInt(replacementCylinder.getText()));
        }
        cylinder.setIsNeedAttention(setBooleanValueCylinder(cylinder));
        WriteFile.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
        listCylindersTabController.updateTableCylinders();

    }
    /** Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем */
    public void setButtonVisible(String string) {
        if (string.equals("Добавить цилиндр")) {
            createCylinder.setVisible(true);
            changeCylinder.setVisible(false);
            createCylinderForAircraft.setVisible(false);
        } else if (string.equals("Изменить запись")) {
            changeCylinder.setVisible(true);
            createCylinder.setVisible(false);
            createCylinderForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createCylinder.setVisible(false);
            changeCylinder.setVisible(false);
            createCylinderForAircraft.setVisible(false);
        }
    }
    /** Метод синхронизирует цилиндры подкоса, из списка ограниченного ресурса и цилиндры подкоса, установленные на самолете
     * если мы вносим изменения в цилиндр подкоса в списке ограниченного ресурса,который установлен на какой либо самолет,
     * то изменения будут автоматически внесеныв этот цилиндр подкоса на самолете */
    private void updateAircraftCylinders() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftMainCylinder().getSerialNumber().equals(cylinder.getSerialNumber())) {
                changeCylinder(aircraft.getLeftMainCylinder());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
            if (aircraft.getRightMainCylinder().getSerialNumber().equals(cylinder.getSerialNumber())) {
                changeCylinder(aircraft.getRightMainCylinder());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
            if (aircraft.getFrontCylinder().getSerialNumber().equals(cylinder.getSerialNumber())) {
                changeCylinder(aircraft.getFrontCylinder());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
        }
    }
}
