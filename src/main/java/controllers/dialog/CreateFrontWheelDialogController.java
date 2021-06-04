package controllers.dialog;

import constants.TextConstants;
import controllers.tab.ListFrontWheelTabController;
import data.Aircraft;
import data.SaveData;
import data.components.limitedResource.FrontWheel;
import io.writer.FilesWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static builder.Builder.createFrontWheel;
import static service.BooleanValueSetter.setBooleanValueFrontWheel;
import static service.DuplicateProtection.frontWheelProtection;
import static service.Notification.notificationFrontWheel;
import static utils.Utils.checkInput;
import static utils.Utils.openInformationWindow;

@Slf4j
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

    private FrontWheel frontWheel;

    @FXML
    void initialize() {
        createFrontWheelForAircraft.setOnAction(e -> {
            try {
                if (frontWheelProtection(numberFrontWheel)) {
                    createFrontWheel(totalFrontWheel, replacementFrontWheel, numberFrontWheel);
                    Stage stage = (Stage) createFrontWheelForAircraft.getScene().getWindow();
                    stage.close();
                    log.info("Пользователь создал переднее колесо во время создания самолета");
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании переднего колеса во время создания самолета, +\n" +
                        "пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        createFrontWheel.setOnAction(e -> {
            try {
                if (frontWheelProtection(numberFrontWheel)) {
                    createFrontWheel(totalFrontWheel, replacementFrontWheel, numberFrontWheel);
                    listFrontWheelTabController.updateTableFrontWheels();
                    Stage stage = (Stage) createFrontWheel.getScene().getWindow();
                    stage.close();
                    log.info("Пользователь создал переднее колесо в ограниченном ресурсе");
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании переднего тормоза, +\n" +
                        "пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        changeFrontWheel.setOnAction(e -> {
            try {
                updateAircraftFrontWheels();
                changeFrontWheel(frontWheel);
                Stage stage = (Stage) changeFrontWheel.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При редактировании переднего тормоза, +\n" +
                        "пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
    }

    public void setFrontWheel(FrontWheel frontWheel) {
        this.frontWheel = frontWheel;
        numberFrontWheel.setText(frontWheel.getSerialNumber());
        totalFrontWheel.setText(String.valueOf(frontWheel.getTotalLandings()));
        replacementFrontWheel.setText(String.valueOf(frontWheel.getResource_Reserve_Replacement_Wheel()));
        notificationFrontWheel(frontWheel, alarmReplacement);
    }

    /**
     * Метод позволяет вносить изменения в существующее переднее колесо
     */
    public void changeFrontWheel(FrontWheel frontWheel) {
        if (checkInput(numberFrontWheel, totalFrontWheel, replacementFrontWheel)) {
            frontWheel.setSerialNumber(numberFrontWheel.getText());
            frontWheel.setTotalLandings(Integer.parseInt(totalFrontWheel.getText()));
            frontWheel.setResource_Reserve_Replacement_Wheel(Integer.parseInt(replacementFrontWheel.getText()));
        }
        frontWheel.setIsNeedAttention(setBooleanValueFrontWheel(frontWheel));
        FilesWriter.serialization(SaveData.frontWheelsList, FrontWheel.class);
        listFrontWheelTabController.updateTableFrontWheels();
        log.info("Пользователь внес изменения в переднее колесо");

    }

    /**
     * Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем
     */
    public void setButtonVisible(boolean isCreate, boolean isChange, boolean isCreateForAircraft) {
        createFrontWheel.setVisible(isCreate);
        changeFrontWheel.setVisible(isChange);
        createFrontWheelForAircraft.setVisible(isCreateForAircraft);
    }

    /**
     * Метод синхронизирует передние колеса, из списка ограниченного ресура и передние колеса, установленные на самолете
     * если мы вносим изменения в колесо в списке ограниченного ресурса,которые установлено на какой либо самолет,
     * то изменения будут автоматически внесеныв это колесо на самолете
     */
    private void updateAircraftFrontWheels() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftFrontWheel().getSerialNumber().equals(frontWheel.getSerialNumber())) {
                changeFrontWheel(aircraft.getLeftFrontWheel());
            } else if (aircraft.getRightFrontWheel().getSerialNumber().equals(frontWheel.getSerialNumber())) {
                changeFrontWheel(aircraft.getRightFrontWheel());
            }
            FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
        }
    }
}


