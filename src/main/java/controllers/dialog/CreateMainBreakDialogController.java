package controllers.dialog;

import constants.TextConstants;
import controllers.tab.ListMainBreaksTabController;
import data.Aircraft;
import data.SaveData;
import data.components.limitedResource.MainBreak;
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

import java.util.ArrayList;
import java.util.List;

import static builder.Builder.createMainBreak;
import static java.lang.Integer.parseInt;
import static service.AircraftWorks.doWorksMainBreak;
import static service.BooleanValueSetter.setBooleanValueMainBreak;
import static service.DuplicateProtection.mainBreakProtection;
import static service.Notification.notificationMainBreak;
import static utils.Utils.checkInput;
import static utils.Utils.openInformationWindow;

@Slf4j
public class CreateMainBreakDialogController {

    List<Text> textOfAlarm = new ArrayList<>();
    @FXML
    private TextField numberMainBreak;
    @FXML
    private TextField totalMainBreak;
    @FXML
    private TextField replacementMainBreak;
    @FXML
    private TextField replacement_RotatingDisks;
    @FXML
    private TextField replacement_NonRotatingDisks;
    @FXML
    private TextField replacement_PressureDisk;
    @FXML
    private TextField replacement_ReferenceDisk;
    @FXML
    private Button createMainBreak;
    @FXML
    private Button changeMainBreak;
    @FXML
    private Button createMainBreakForAircraft;
    @FXML
    private Text alarmReplacement;
    @FXML
    private Text alarmRotatingDisks;
    @FXML
    private Text alarmNonRotatingDisks;
    @FXML
    private Text alarmPressureDisk;
    @FXML
    private Text alarmReferenceDisk;
    @FXML
    private ComboBox<TypesOfWorks> listOfWorks;
    @FXML
    private Text selectionWorks;
    @FXML
    private Button makeWork;
    @Setter
    private ListMainBreaksTabController listMainBreaksTabController;
    @Setter

    private PersonalAircraftDialogController personalAircraftDialogController;
    private MainBreak mainBreak;

    @FXML
    void initialize() {
        listOfWorks.getItems().addAll(TypesOfWorks.REPLACEMENT_ROTATING_DISKS,
                TypesOfWorks.REPLACEMENT_NON_ROTATING_DISKS,
                TypesOfWorks.REPLACEMENT_PRESSURE_DISKS,
                TypesOfWorks.REPLACEMENT_REFERENCE_DISKS);
        createMainBreakForAircraft.setOnAction(e -> {
            try {
                if (mainBreakProtection(numberMainBreak)) {
                    addMainBreak();
                    Stage stage = (Stage) createMainBreakForAircraft.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании основного тормоза во время создания самолета, + \n" +
                        "пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        createMainBreak.setOnAction(e -> {
            try {
                if (mainBreakProtection(numberMainBreak)) {
                    addMainBreak();
                    listMainBreaksTabController.updateTableMainBreaks();
                    Stage stage = (Stage) createMainBreak.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При создании основного тормоза, пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        changeMainBreak.setOnAction(e -> {
            try {
                updateAircraftMainBreaks();
                changeMainBreak(mainBreak);
                Stage stage = (Stage) changeMainBreak.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT);
                log.error("При редактировании основного тормоза, пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        makeWork.setOnAction(e -> {
            try {
                doWorksMainBreak(mainBreak, listOfWorks);
                personalAircraftDialogController.updateNotificationMainBreak(mainBreak);
                FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
                update_MainBreak_After_Work();
                Stage stage = (Stage) makeWork.getScene().getWindow();
                stage.close();
            } catch (NullPointerException exception) {
                openInformationWindow(TextConstants.MAKE_WORKS_AGGREGATES);
                log.error("Пользователь нажал кнопку выполнить работы в основном тормозе, не выбрав вид работ {}",
                        exception.getMessage());

            }
        });
    }

    public void setMainBreak(MainBreak mainBreak) {
        this.mainBreak = mainBreak;
        totalMainBreak.setText(String.valueOf(mainBreak.getTotalLandings()));
        numberMainBreak.setText(mainBreak.getSerialNumber());
        replacementMainBreak.setText(String.valueOf(mainBreak.getResource_Reserve_Replacement_Break()));
        replacement_RotatingDisks.setText(String.valueOf(mainBreak.getResource_Reserve_Replacement_RotatingDisks()));
        replacement_NonRotatingDisks.setText(String.valueOf(mainBreak.getResource_Reserve_Replacement_NonRotatingDisks()));
        replacement_PressureDisk.setText(String.valueOf(mainBreak.getResource_Reserve_Replacement_PressureDisk()));
        replacement_ReferenceDisk.setText(String.valueOf(mainBreak.getResource_Reserve_Replacement_ReferenceDisk()));
        textOfAlarm.add(alarmReplacement);
        textOfAlarm.add(alarmRotatingDisks);
        textOfAlarm.add(alarmNonRotatingDisks);
        textOfAlarm.add(alarmPressureDisk);
        textOfAlarm.add(alarmReferenceDisk);
        notificationMainBreak(mainBreak, textOfAlarm);
    }

    /**
     * Метод добавляет новый основной тормоз
     */
    private void addMainBreak() {
        TextField[] fields = new TextField[]{totalMainBreak, replacementMainBreak,
                replacement_RotatingDisks, replacement_NonRotatingDisks,
                replacement_PressureDisk, replacement_ReferenceDisk,
                numberMainBreak};
        createMainBreak(fields);
        log.info("Пользователь создал основной тормоз");
    }

    /**
     * Метод позволяет вносить изменения в существующий основной тормоз
     */
    public void changeMainBreak(MainBreak mainBreak) {
        if (checkInput(replacementMainBreak,
                totalMainBreak,
                replacement_NonRotatingDisks,
                replacement_RotatingDisks,
                replacement_PressureDisk,
                replacement_ReferenceDisk,
                numberMainBreak)) {
            mainBreak.setResource_Reserve_Replacement_Break(parseInt(replacementMainBreak.getText()));
            mainBreak.setTotalLandings(parseInt(totalMainBreak.getText()));
            mainBreak.setResource_Reserve_Replacement_NonRotatingDisks(parseInt(replacement_NonRotatingDisks.getText()));
            mainBreak.setResource_Reserve_Replacement_RotatingDisks(parseInt(replacement_RotatingDisks.getText()));
            mainBreak.setResource_Reserve_Replacement_PressureDisk(parseInt(replacement_PressureDisk.getText()));
            mainBreak.setResource_Reserve_Replacement_ReferenceDisk(parseInt(replacement_ReferenceDisk.getText()));
            mainBreak.setSerialNumber(numberMainBreak.getText());
        }
        mainBreak.setIsNeedAttention(setBooleanValueMainBreak(mainBreak));
        FilesWriter.serialization(SaveData.mainBreaksList, MainBreak.class);
        listMainBreaksTabController.updateTableMainBreaks();
        log.info("Пользователь внес изменения в основной тормоз");

    }

    /**
     * Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем
     */
    public void setButtonVisible(boolean isCreate, boolean isChange,
                                 boolean isCreateForAircraft, boolean isSelectionWorks,
                                 boolean isVisibleList, boolean isMakeWorks) {
        createMainBreak.setVisible(isCreate);
        changeMainBreak.setVisible(isChange);
        createMainBreakForAircraft.setVisible(isCreateForAircraft);
        selectionWorks.setVisible(isSelectionWorks);
        listOfWorks.setVisible(isVisibleList);
        makeWork.setVisible(isMakeWorks);
    }

    /**
     * Метод синхронизирует основные тормоза, из списка огранич. ресурса и основные тормоза, установленные на самолете
     * если мы вносим изменения в основной тормоз в списке огранич.ресурса,который установлен на какой либо самолет,
     * то изменения будут автоматически внесены в этот основной тормоз на самолете
     */
    private void updateAircraftMainBreaks() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftMainBrake().getSerialNumber().equals(mainBreak.getSerialNumber())) {
                changeMainBreak(aircraft.getLeftMainBrake());
            }
            if (aircraft.getRightMainBrake().getSerialNumber().equals(mainBreak.getSerialNumber())) {
                changeMainBreak(aircraft.getRightMainBrake());
            }
            FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
        }
    }

    /**
     * Метод синхронизирует осноные тормоза, из списка агрегатов и основные тормоза, установленные на самолете
     * если мы выполняем работы в основном тормозе на самолете,
     * то изменения будут автоматически внесеныв этот тормоз в списке агрегатов
     */
    private void update_MainBreak_After_Work() {
        for (MainBreak breakFromList : SaveData.mainBreaksList) {
            if (breakFromList.getSerialNumber().equals(mainBreak.getSerialNumber())) {
                doWorksMainBreak(breakFromList, listOfWorks);
                FilesWriter.serialization(SaveData.mainBreaksList, MainBreak.class);
            }
        }
    }
}
