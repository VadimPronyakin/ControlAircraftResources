package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Cylinder;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import sample.controllers.tab.ListCylindersTabController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.limitedResource.CylinderOfRetractionExtension;
import sample.write.WriteFile;

import java.util.ArrayList;
import java.util.List;

import static sample.notification.NotificationAircraft.notificationCylinder;
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

    private CylinderOfRetractionExtension cylinder;

    List<Text> textOfAlarm = new ArrayList<>();

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
        textOfAlarm.add(alarmFirstRepair);
        textOfAlarm.add(alarmSecondRepair);
        textOfAlarm.add(alarmReplacement);
        notificationCylinder(cylinder, textOfAlarm);
    }

    private void addCylinder() {
        CylinderOfRetractionExtension cylinder = CylinderOfRetractionExtension.builder()
                .totalLandings(Integer.parseInt(totalCylinder.getText()))
                .resource_Reserve_Before_First_Repair(Integer.parseInt(first_Repair_Cylinder.getText()))
                .resource_Reserve_Before_Second_Repair(Integer.parseInt(second_Repair_Cylinder.getText()))
                .resource_Reserve_Before_Replacement(Integer.parseInt(replacementCylinder.getText()))
                .serialNumber(numberCylinder.getText())
                .build();
        SaveData.cylindersList.add(cylinder);
        WriteFile.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);

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
        WriteFile.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
        listCylindersTabController.updateTableCylinders();

    }

    public void setButtonVisible(String string) {
        if(string.equals("Добавить цилиндр")){
            createCylinder.setVisible(true);
            changeCylinder.setVisible(false);
            createCylinderForAircraft.setVisible(false);
        } else if(string.equals("Изменить запись")) {
            changeCylinder.setVisible(true);
            createCylinder.setVisible(false);
            createCylinderForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createCylinder.setVisible(false);
            changeCylinder.setVisible(false);
            createCylinderForAircraft.setVisible(false);
        }
    }

    private void updateAircraftCylinders() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftMainCylinder().getSerialNumber().equals(cylinder.getSerialNumber())){
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
