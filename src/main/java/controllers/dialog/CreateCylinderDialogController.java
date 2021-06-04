package controllers.dialog;

import constants.TextConstants;
import controllers.tab.ListCylindersTabController;
import data.Aircraft;
import data.SaveData;
import data.components.limitedResource.CylinderOfRetractionExtension;
import io.writer.FilesWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static builder.Builder.createCylinder;
import static service.BooleanValueSetter.setBooleanValueCylinder;
import static service.DuplicateProtection.cylinderProtection;
import static service.Notification.notificationCylinder;
import static utils.Utils.checkInput;
import static utils.Utils.openInformationWindow;

@Slf4j
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

    @FXML
    void initialize() {
        createCylinderForAircraft.setOnAction(e -> {
            try {
                if (cylinderProtection(numberCylinder)) {
                    addCylinder();
                    Stage stage = (Stage) createCylinderForAircraft.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании цилиндра подкоса во время создания самолета, пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        createCylinder.setOnAction(e -> {
            try {
                if (cylinderProtection(numberCylinder)) {
                    addCylinder();
                    listCylindersTabController.updateTableCylinders();
                    Stage stage = (Stage) createCylinder.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании цилиндра подкоса пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        changeCylinder.setOnAction(e -> {
            try {
                changeCylinder(cylinder);
                updateAircraftCylinders();
                Stage stage = (Stage) changeCylinder.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При редактировании цилиндра подкоса пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
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

    /**
     * Метод добавляет новый цилиндр подкоса
     */
    private void addCylinder() {
        TextField[] fields = new TextField[]{totalCylinder,
                first_Repair_Cylinder,
                second_Repair_Cylinder,
                replacementCylinder,
                numberCylinder};
        createCylinder(fields);
        log.info("Пользователь добавил цилиндр подкоса");
    }

    /**
     * Метод позволяет вносить изменения в существующий цилиндр подкоса
     */
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
        FilesWriter.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
        listCylindersTabController.updateTableCylinders();
        log.info("Пользователь внес изменения в цилиндр подкоса");

    }

    /**
     * Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем
     */
    public void setButtonVisible(boolean isCreate, boolean isChange, boolean isWatching) {
        createCylinder.setVisible(isCreate);
        changeCylinder.setVisible(isChange);
        createCylinderForAircraft.setVisible(isWatching);
    }

    /**
     * Метод синхронизирует цилиндры подкоса, из списка ограниченного ресурса и цилиндры подкоса, установленные на самолете
     * если мы вносим изменения в цилиндр подкоса в списке ограниченного ресурса,который установлен на какой либо самолет,
     * то изменения будут автоматически внесеныв этот цилиндр подкоса на самолете
     */
    private void updateAircraftCylinders() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftMainCylinder().getSerialNumber().equals(cylinder.getSerialNumber())) {
                changeCylinder(aircraft.getLeftMainCylinder());
            }
            if (aircraft.getRightMainCylinder().getSerialNumber().equals(cylinder.getSerialNumber())) {
                changeCylinder(aircraft.getRightMainCylinder());
            }
            if (aircraft.getFrontCylinder().getSerialNumber().equals(cylinder.getSerialNumber())) {
                changeCylinder(aircraft.getFrontCylinder());
            }
            FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
        }
    }
}
