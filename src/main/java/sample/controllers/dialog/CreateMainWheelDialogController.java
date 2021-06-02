package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import sample.builder.Builder;
import sample.controllers.tab.ListMainWheelsTabController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.limitedResource.MainWheel;
import sample.setBoolean.SetBooleanValue;
import sample.write.WriteFile;

import static sample.notification.Notification.notificationMainWheel;
import static sample.utils.Utils.checkInput;

public class CreateMainWheelDialogController {

    @FXML
    private TextField numberMainWheel;
    @FXML
    private TextField totalMainWheel;
    @FXML
    private TextField replacementMainWheel;
    @FXML
    private Button createMainWheel;
    @FXML
    private Button changeMainWheel;
    @FXML
    private Button createMainWheelForAircraft;
    @FXML
    private Text alarmReplacement;
    @Setter
    private ListMainWheelsTabController listMainWheelsTabController;

    private MainWheel mainWheel;

    @FXML
    void initialize() {
        createMainWheelForAircraft.setOnAction(e -> {
            addMainWheel();
            Stage stage = (Stage) createMainWheelForAircraft.getScene().getWindow();
            stage.close();
        });
        createMainWheel.setOnAction(e -> {
            addMainWheel();
            listMainWheelsTabController.updateTableMainWheels();
            Stage stage = (Stage) createMainWheel.getScene().getWindow();
            stage.close();
        });
        changeMainWheel.setOnAction(e -> {
            updateAircraftMainWheels();
            changeMainWheel(mainWheel);
            Stage stage = (Stage) changeMainWheel.getScene().getWindow();
            stage.close();
        });
    }

    public void setMainWheel(MainWheel mainWheel) {
        this.mainWheel = mainWheel;
        totalMainWheel.setText(String.valueOf(mainWheel.getTotalLandings()));
        numberMainWheel.setText(mainWheel.getSerialNumber());
        replacementMainWheel.setText(String.valueOf(mainWheel.getResource_Reserve_Replacement_Wheel()));
        notificationMainWheel(mainWheel, alarmReplacement);
    }

    private void addMainWheel() {
        Builder.createMainWheel(totalMainWheel, replacementMainWheel, numberMainWheel);
    }

    public void changeMainWheel(MainWheel mainWheel) {
        if (checkInput(numberMainWheel, totalMainWheel, replacementMainWheel)) {
            mainWheel.setSerialNumber(numberMainWheel.getText());
            mainWheel.setTotalLandings(Integer.parseInt(totalMainWheel.getText()));
            mainWheel.setResource_Reserve_Replacement_Wheel(Integer.parseInt(replacementMainWheel.getText()));
            mainWheel.setIsNeedAttention(SetBooleanValue.setBooleanValueMainWheel(mainWheel));
        } else {
            mainWheel.setIsNeedAttention(SetBooleanValue.setBooleanValueMainWheel(mainWheel));
        }
        WriteFile.serialization(SaveData.mainWheelsList, MainWheel.class);
        listMainWheelsTabController.updateTableMainWheels();

    }
    /** Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем */
    public void setButtonVisible(String string) {
        if (string.equals("Добавить колесо")) {
            createMainWheel.setVisible(true);
            changeMainWheel.setVisible(false);
            createMainWheelForAircraft.setVisible(false);
        } else if (string.equals("Изменить запись")) {
            changeMainWheel.setVisible(true);
            createMainWheel.setVisible(false);
            createMainWheelForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createMainWheel.setVisible(false);
            changeMainWheel.setVisible(false);
            createMainWheelForAircraft.setVisible(false);
        }
    }
    /** Метод синхронизирует основные колеса, из списка огранич. ресурса и основные колеса, установленные на самолете
     * если мы вносим изменения в основное колесо в списке огранич.ресурса,которое установлено на какой либо самолет,
     * то изменения будут автоматически внесены в это колесо на самолете */
    private void updateAircraftMainWheels() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if ( aircraft.getLeftMainWheel() == null) {
                System.out.println("Нет левого основного колеса на самолете");
            } else if (aircraft.getLeftMainWheel().getSerialNumber().equals(mainWheel.getSerialNumber())){
                changeMainWheel(aircraft.getLeftMainWheel());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
            if (aircraft.getRightMainWheel() == null) {
                System.out.println("Нет правого основного колеса на самолете");
            } else if (aircraft.getRightMainWheel().getSerialNumber().equals(mainWheel.getSerialNumber())) {
                changeMainWheel(aircraft.getRightMainWheel());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
        }
    }
}

