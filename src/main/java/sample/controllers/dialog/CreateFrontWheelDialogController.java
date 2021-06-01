package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import sample.controllers.tab.ListFrontWheelTabController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.limitedResource.FrontWheel;
import sample.setBoolean.SetBooleanValue;
import sample.write.WriteFile;

import static sample.builder.Builder.createFrontWheel;
import static sample.notification.NotificationAircraft.notificationFrontWheel;
import static sample.setBoolean.SetBooleanValue.setBooleanValueFrontWheel;
import static sample.utils.Utils.checkInput;


public class CreateFrontWheelDialogController {

    @FXML
    private TextField numberFrontWheel;
    @FXML
    private TextField totalFrontWheel;
    @FXML
    private TextField replacementFrontWheel;
    @FXML
    private Button createFrontWheel;
    @FXML
    private Button changeFrontWheel;
    @FXML
    private Button createFrontWheelForAircraft;
    @FXML
    private Text alarmReplacement;
    @Setter
    private ListFrontWheelTabController listFrontWheelTabController;
    @Setter
    private PersonalAircraftDialogController personalAircraftDialogController;

    private FrontWheel frontWheel;

    @FXML
    void initialize() {
        createFrontWheelForAircraft.setOnAction(e -> {
            addFrontWheel();
            Stage stage = (Stage) createFrontWheelForAircraft.getScene().getWindow();
            stage.close();
        });
        createFrontWheel.setOnAction(e -> {
            addFrontWheel();
            listFrontWheelTabController.updateTableFrontWheels();
            Stage stage = (Stage) createFrontWheel.getScene().getWindow();
            stage.close();
        });
        changeFrontWheel.setOnAction(e -> {
            updateAircraftFrontWheels();
            changeFrontWheel(frontWheel);
            Stage stage = (Stage) changeFrontWheel.getScene().getWindow();
            stage.close();
        });
    }

    public void setFrontWheel(FrontWheel frontWheel) {
        this.frontWheel = frontWheel;
        numberFrontWheel.setText(frontWheel.getSerialNumber());
        totalFrontWheel.setText(String.valueOf(frontWheel.getTotalLandings()));
        replacementFrontWheel.setText(String.valueOf(frontWheel.getResource_Reserve_Replacement_Wheel()));
        notificationFrontWheel(frontWheel, alarmReplacement);
    }

    private void addFrontWheel() {
        createFrontWheel(totalFrontWheel, replacementFrontWheel, numberFrontWheel);
    }

    public void changeFrontWheel(FrontWheel frontWheel) {
        if (checkInput(numberFrontWheel, totalFrontWheel, replacementFrontWheel)) {
            frontWheel.setSerialNumber(numberFrontWheel.getText());
            frontWheel.setTotalLandings(Integer.parseInt(totalFrontWheel.getText()));
            frontWheel.setResource_Reserve_Replacement_Wheel(Integer.parseInt(replacementFrontWheel.getText()));
        }
        frontWheel.setIsNeedAttention(setBooleanValueFrontWheel(frontWheel));
        WriteFile.serialization(SaveData.frontWheelsList, FrontWheel.class);
        listFrontWheelTabController.updateTableFrontWheels();

    }

    public void setButtonVisible(String string) {
        if (string.equals("Добавить колесо")) {
            createFrontWheel.setVisible(true);
            changeFrontWheel.setVisible(false);
            createFrontWheelForAircraft.setVisible(false);
        } else if (string.equals("Изменить запись")) {
            changeFrontWheel.setVisible(true);
            createFrontWheel.setVisible(false);
            createFrontWheelForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createFrontWheel.setVisible(false);
            changeFrontWheel.setVisible(false);
            createFrontWheelForAircraft.setVisible(false);
        }
    }

    private void updateAircraftFrontWheels() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftFrontWheel().getSerialNumber().equals(frontWheel.getSerialNumber())) {
                changeFrontWheel(aircraft.getLeftFrontWheel());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            } else if (aircraft.getRightFrontWheel().getSerialNumber().equals(frontWheel.getSerialNumber())) {
                changeFrontWheel(aircraft.getRightFrontWheel());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }

        }
    }
}


