package controllers.dialog;

import constants.TextConstants;
import controllers.tab.ListMainWheelsTabController;
import data.Aircraft;
import data.SaveData;
import data.components.limitedResource.MainWheel;
import io.writer.FilesWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import service.BooleanValueSetter;

import static builder.Builder.createMainWheel;
import static service.DuplicateProtection.mainWheelProtection;
import static service.Notification.notificationMainWheel;
import static utils.Utils.checkInput;
import static utils.Utils.openInformationWindow;

@Slf4j
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
            try {
                if (mainWheelProtection(numberMainWheel)) {
                    createMainWheel(totalMainWheel, replacementMainWheel, numberMainWheel);
                    Stage stage = (Stage) createMainWheelForAircraft.getScene().getWindow();
                    stage.close();
                    log.info("Пользователь создал основное колесо во время создания самолета");
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании основного колеса во время создания самолета, + \n" +
                        "пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        createMainWheel.setOnAction(e -> {
            try {
                if (mainWheelProtection(numberMainWheel)) {
                    createMainWheel(totalMainWheel, replacementMainWheel, numberMainWheel);
                    listMainWheelsTabController.updateTableMainWheels();
                    Stage stage = (Stage) createMainWheel.getScene().getWindow();
                    stage.close();
                    log.info("Пользователь создаль основное колесо в ограниченном ресурсе");
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании основного колеса, пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        changeMainWheel.setOnAction(e -> {
            try {
                updateAircraftMainWheels();
                changeMainWheel(mainWheel);
                Stage stage = (Stage) changeMainWheel.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При редактировании основного колеса, пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
    }

    public void setMainWheel(MainWheel mainWheel) {
        this.mainWheel = mainWheel;
        totalMainWheel.setText(String.valueOf(mainWheel.getTotalLandings()));
        numberMainWheel.setText(mainWheel.getSerialNumber());
        replacementMainWheel.setText(String.valueOf(mainWheel.getResource_Reserve_Replacement_Wheel()));
        notificationMainWheel(mainWheel, alarmReplacement);
    }

    /**
     * Метод позволяте вносить изменения в существующее основное колесо
     */
    public void changeMainWheel(MainWheel mainWheel) {
        if (checkInput(numberMainWheel, totalMainWheel, replacementMainWheel)) {
            mainWheel.setSerialNumber(numberMainWheel.getText());
            mainWheel.setTotalLandings(Integer.parseInt(totalMainWheel.getText()));
            mainWheel.setResource_Reserve_Replacement_Wheel(Integer.parseInt(replacementMainWheel.getText()));
        }
        mainWheel.setIsNeedAttention(BooleanValueSetter.setBooleanValueMainWheel(mainWheel));
        FilesWriter.serialization(SaveData.mainWheelsList, MainWheel.class);
        listMainWheelsTabController.updateTableMainWheels();
        log.info("Пользователь внес изменения в основное колесо");

    }

    /**
     * Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем
     */
    public void setButtonVisible(boolean isCreate, boolean isChange, boolean isCreateForAircraft) {
        createMainWheel.setVisible(isCreate);
        changeMainWheel.setVisible(isChange);
        createMainWheelForAircraft.setVisible(isCreateForAircraft);
    }

    /**
     * Метод синхронизирует основные колеса, из списка огранич. ресурса и основные колеса, установленные на самолете
     * если мы вносим изменения в основное колесо в списке огранич.ресурса,которое установлено на какой либо самолет,
     * то изменения будут автоматически внесены в это колесо на самолете
     */
    private void updateAircraftMainWheels() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftMainWheel().getSerialNumber().equals(mainWheel.getSerialNumber())) {
                changeMainWheel(aircraft.getLeftMainWheel());
            }
            if (aircraft.getRightMainWheel().getSerialNumber().equals(mainWheel.getSerialNumber())) {
                changeMainWheel(aircraft.getRightMainWheel());
            }
            FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
        }
    }
}

