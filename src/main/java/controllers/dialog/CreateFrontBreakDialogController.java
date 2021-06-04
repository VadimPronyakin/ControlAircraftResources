package controllers.dialog;

import constants.TextConstants;
import controllers.tab.ListFrontBreakTabController;
import data.Aircraft;
import data.SaveData;
import data.components.limitedResource.FrontBreak;
import io.writer.FilesWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static builder.Builder.createFrontBreak;
import static service.BooleanValueSetter.setBooleanValueFrontBreak;
import static service.DuplicateProtection.frontBreakProtection;
import static service.Notification.notificationFrontBreak;
import static utils.Utils.checkInput;
import static utils.Utils.openInformationWindow;

@Slf4j
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
            try {
                if (frontBreakProtection(numberFrontBreak)) {
                    addFrontBreak();
                    log.info("Пользователь создал передний тормоз, во время создания самолета");
                    Stage stage = (Stage) createFrontBreakForAircraft.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании переднего тормоза во время создания самолета, +\n" +
                        "пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        createFrontBreak.setOnAction(e -> {
            try {
                if (frontBreakProtection(numberFrontBreak)) {
                    addFrontBreak();
                    log.info("Пользователь создал передний тормоз в ограниченном ресурсе");
                    listFrontBreakTabController.updateTableFrontBreak();
                    Stage stage = (Stage) createFrontBreak.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании переднего тормоза пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        changeFrontBreak.setOnAction(e -> {
            try {
                updateAircraftFrontBreaks();
                changeFrontBreak(frontBreak);
                Stage stage = (Stage) changeFrontBreak.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При редактировании переднего тормоза пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
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

    /**
     * Метод создает новый передий тормоз
     */
    private void addFrontBreak() {
        TextField[] fields = new TextField[]{totalFrontBreak,
                first_Repair_FrontBreak,
                replacementFrontBreak,
                numberFrontBreak};
        createFrontBreak(fields);
    }

    /**
     * Метод позволяет вносить изменения в существующие передние тормоза
     */
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
        FilesWriter.serialization(SaveData.frontBreaksList, FrontBreak.class);
        listFrontBreakTabController.updateTableFrontBreak();
        log.info("Пользователь внес изменения в передний тормоз");

    }

    /**
     * Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем
     */
    public void setButtonVisible(boolean isCreate, boolean isChange, boolean isCreateForAircraft) {
        createFrontBreak.setVisible(isCreate);
        changeFrontBreak.setVisible(isChange);
        createFrontBreakForAircraft.setVisible(isCreateForAircraft);
    }

    /**
     * Метод синхронизирует передние тормоза, из списка огранич. ресурса и передние тормоза, установленные на самолете
     * если мы вносим изменения в передний тормоз в списке огранич.ресурса,который установлен на какой либо самолет,
     * то изменения будут автоматически внесены в этот передний тормоз на самолете
     */
    public void updateAircraftFrontBreaks() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftFrontBrake().getSerialNumber().equals(frontBreak.getSerialNumber())) {
                changeFrontBreak(aircraft.getLeftFrontBrake());
            }
            if (aircraft.getRightFrontBrake().getSerialNumber().equals(frontBreak.getSerialNumber())) {
                changeFrontBreak(aircraft.getRightFrontBrake());
            }
            FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
        }
    }
}

