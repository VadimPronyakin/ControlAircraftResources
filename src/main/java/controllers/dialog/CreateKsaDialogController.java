package controllers.dialog;

import constants.TextConstants;
import controllers.tab.ListAllKsaTabController;
import data.Aircraft;
import data.SaveData;
import data.components.Ksa;
import data.enums.TypesOfWorks;
import io.writer.FilesWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static builder.Builder.createKsa;
import static java.lang.Integer.parseInt;
import static service.AircraftWorks.doWorkKsa;
import static service.BooleanValueSetter.setBooleanValueKsa;
import static service.DuplicateProtection.ksaProtection;
import static service.Notification.notificationKsa;
import static utils.Utils.*;

@Slf4j
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

    @FXML
    void initialize() {
        listOfWorksKsa.getItems().addAll(TypesOfWorks.WORKS_AFTER_25_HOURS, TypesOfWorks.OIL_CHANGE_OPERATIONS);
        createKsaForAircraft.setOnAction(e -> {
            try {
                if (minutesChecker(returnFullArray()) && ksaProtection(numberKsa)) {
                        createKsa(returnFullArray());
                        Stage stage = (Stage) createKsaForAircraft.getScene().getWindow();
                        stage.close();
                        log.info("Пользователь создал КСА во время создания самолета");
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании КСА во время создания самолета, пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        createKsa.setOnAction(e -> {
            try {
                if (minutesChecker(returnFullArray()) && ksaProtection(numberKsa)) {
                        createKsa(returnFullArray());
                    listAllKsaTabController.updateTableKsa();
                        Stage stage = (Stage) createKsa.getScene().getWindow();
                        stage.close();
                        log.info("Пользователь создал КСА в списке агрегатов");
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании КСА, пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        changeKsa.setOnAction(e -> {
            try {
                if (minutesChecker(returnFullArray())) {
                    updateAircraftKsa();
                    changeKsa(ksa);
                    listAllKsaTabController.updateTableKsa();
                    Stage stage = (Stage) changeKsa.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При редактировании КСА, пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        makeWorksKsa.setOnAction(e -> {
            try {
                doWorkKsa(ksa, listOfWorksKsa);
                update_Ksa_After_Work();
                personalAircraftDialogController.updateNotificationKsa(ksa);
                FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
                Stage stage = (Stage) makeWorksKsa.getScene().getWindow();
                stage.close();
            } catch (NullPointerException exception) {
                openInformationWindow(TextConstants.MAKE_WORKS_AGGREGATES);
                log.error("Пользователь нажал кнопку выполнить работы в КСА, не выбрав вид работ {}",
                        exception.getMessage());
            }
        });
    }

    public void setKsa(Ksa ksa) {
        this.ksa = ksa;
        numberKsa.setText(ksa.getSerialNumber());
        totalStartKsa.setText(String.valueOf(ksa.getTotal_Starting_Ksa_Count()));
        totalRightKsa.setText(String.valueOf(ksa.getStarting_Right_Count()));
        totalLeftKsa.setText(String.valueOf(ksa.getStarting_Left_Count()));
        totalOperatingKsaHours.setText(String.valueOf(ksa.getTotal_Operating_Time() / 60));
        totalOperatingKsaMinutes.setText(String.valueOf(ksa.getTotal_Operating_Time() % 60));
        before_25hoursKsaHours.setText(String.valueOf(ksa.getResource_Reserve_Before_25hours() / 60));
        before_25hoursKsaMinutes.setText(String.valueOf(ksa.getResource_Reserve_Before_25hours() % 60));
        oilChangeKsaHours.setText(String.valueOf(ksa.getOilChange() / 60));
        oilChangeKsaMinutes.setText(String.valueOf(ksa.getOilChange() % 60));
        notificationKsa(ksa, alarm25Hours, alarmOilChange);
    }

    /**
     * Метод возвращает массив заполненый TextFields
     */
    private TextField[] returnFullArray() {
        return new TextField[]{totalOperatingKsaHours, totalLeftKsa,
                totalRightKsa, totalOperatingKsaMinutes,
                totalStartKsa, before_25hoursKsaMinutes,
                before_25hoursKsaHours, oilChangeKsaMinutes,
                oilChangeKsaHours, numberKsa};
    }

    /**
     * Метод позволяет вносить изменения в существующую КСА
     */
    public void changeKsa(Ksa ksa) {
        if (checkInput(before_25hoursKsaHours, before_25hoursKsaMinutes,
                oilChangeKsaHours, oilChangeKsaMinutes,
                totalStartKsa, totalLeftKsa,
                totalRightKsa, numberKsa,
                totalOperatingKsaHours, totalOperatingKsaMinutes)) {
            ksa.setSerialNumber(numberKsa.getText());
            ksa.setTotal_Starting_Ksa_Count(parseInt(totalStartKsa.getText()));
            ksa.setStarting_Left_Count(parseInt(totalLeftKsa.getText()));
            ksa.setStarting_Right_Count(parseInt(totalRightKsa.getText()));
            ksa.setResource_Reserve_Before_25hours((parseInt(before_25hoursKsaHours.getText()) * 60) +
                    parseInt(before_25hoursKsaMinutes.getText()));
            ksa.setOilChange((parseInt(oilChangeKsaHours.getText()) * 60) +
                    parseInt(oilChangeKsaMinutes.getText()));
            ksa.setTotal_Operating_Time((parseInt(totalOperatingKsaHours.getText()) * 60 +
                    parseInt(totalOperatingKsaMinutes.getText())));
        }
        ksa.setIsNeedAttention(setBooleanValueKsa(ksa));
        FilesWriter.serialization(SaveData.ksaList, Ksa.class);
        log.info("Пользователь внес изменения в КСА");

    }

    /**
     * Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем
     */
    public void setButtonVisible(boolean isCreate, boolean isChange,
                                 boolean isCreateForAircraft, boolean isSelectionWorks,
                                 boolean isVisibleList, boolean isMakeWorks) {
        changeKsa.setVisible(isChange);
        createKsa.setVisible(isCreate);
        createKsaForAircraft.setVisible(isCreateForAircraft);
        selectionWorks.setVisible(isSelectionWorks);
        listOfWorksKsa.setVisible(isVisibleList);
        makeWorksKsa.setVisible(isMakeWorks);
    }

    /**
     * Метод синхронизирует КСА, из списка агрегатов и КСА, установленные на самолете
     * если мы выполняем работы в КСА на самолете,
     * то изменения будут автоматически внесеныв эту КСА в списке агрегатов
     */
    private void update_Ksa_After_Work() {
        for (Ksa ksaFromList : SaveData.ksaList) {
            if (ksaFromList.getSerialNumber().equals(ksa.getSerialNumber())) {
                doWorkKsa(ksaFromList, listOfWorksKsa);
                FilesWriter.serialization(SaveData.ksaList, Ksa.class);
            }
        }
    }

    /**
     * Метод синхронизирует КСА, из списка агрегатов и КСА, установленные на самолете
     * если мы вносим изменения в КСА в списке агрегатов,которые установлено на какой либо самолет,
     * то изменения будут автоматически внесеныв эту КСА на самолете
     */
    private void updateAircraftKsa() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getKsa() == null) {
                System.out.println("На самолете отсутствует КСА");
            } else if (aircraft.getKsa().getSerialNumber().equals(ksa.getSerialNumber())) {
                changeKsa(aircraft.getKsa());
                FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
            }
        }
    }
}

