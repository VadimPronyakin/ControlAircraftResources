package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import sample.controllers.tab.ListMainBreaksTabController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.limitedResource.MainBreak;
import sample.data.enums.TypesOfWorks;
import sample.write.WriteFile;

import java.util.ArrayList;
import java.util.List;

import static sample.notification.NotificationAircraft.notificationMainBreak;
import static sample.utils.Utils.checkInput;
import static sample.works.MakeWorks.doWorksMainBreak;

public class CreateMainBreakDialogController {

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

    List<Text> textOfAlarm = new ArrayList<>();

    @FXML
    void initialize() {
        listOfWorks.getItems().addAll(TypesOfWorks.REPLACEMENT_ROTATING_DISKS,
                TypesOfWorks.REPLACEMENT_NON_ROTATING_DISKS,
                TypesOfWorks.REPLACEMENT_PRESSURE_DISKS,
                TypesOfWorks.REPLACEMENT_REFERENCE_DISKS);
        createMainBreakForAircraft.setOnAction(e -> {
            addMainBreak();
            Stage stage = (Stage) createMainBreakForAircraft.getScene().getWindow();
            stage.close();
        });
        createMainBreak.setOnAction(e -> {
            addMainBreak();
            listMainBreaksTabController.updateTableMainBreaks();
            Stage stage = (Stage) createMainBreak.getScene().getWindow();
            stage.close();
        });
        changeMainBreak.setOnAction(e -> {
            updateAircraftMainBreaks();
            changeMainBreak(mainBreak);
            Stage stage = (Stage) changeMainBreak.getScene().getWindow();
            stage.close();
        });
        makeWork.setOnAction(e -> {
            doWorksMainBreak(mainBreak, listOfWorks);
            WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            update_MainBreak_After_Work();
            Stage stage = (Stage) makeWork.getScene().getWindow();
            stage.close();
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

    private void addMainBreak() {
        MainBreak mainBreak = MainBreak.builder()
                .totalLandings(Integer.parseInt(totalMainBreak.getText()))
                .resource_Reserve_Replacement_Break(Integer.parseInt(replacementMainBreak.getText()))
                .resource_Reserve_Replacement_RotatingDisks(Integer.parseInt(replacement_RotatingDisks.getText()))
                .resource_Reserve_Replacement_NonRotatingDisks(Integer.parseInt(replacement_NonRotatingDisks.getText()))
                .resource_Reserve_Replacement_PressureDisk(Integer.parseInt(replacement_PressureDisk.getText()))
                .resource_Reserve_Replacement_ReferenceDisk(Integer.parseInt(replacement_ReferenceDisk.getText()))
                .serialNumber(numberMainBreak.getText())
                .build();
        SaveData.mainBreaksList.add(mainBreak);
        WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);

    }

    public void changeMainBreak(MainBreak mainBreak) {
        if (checkInput(replacementMainBreak,
                totalMainBreak,
                replacement_NonRotatingDisks,
                replacement_RotatingDisks,
                replacement_PressureDisk,
                replacement_ReferenceDisk,
                numberMainBreak)) {
            mainBreak.setResource_Reserve_Replacement_Break(Integer.parseInt(replacementMainBreak.getText()));
            mainBreak.setTotalLandings(Integer.parseInt(totalMainBreak.getText()));
            mainBreak.setResource_Reserve_Replacement_NonRotatingDisks(Integer.parseInt(replacement_NonRotatingDisks.getText()));
            mainBreak.setResource_Reserve_Replacement_RotatingDisks(Integer.parseInt(replacement_RotatingDisks.getText()));
            mainBreak.setResource_Reserve_Replacement_PressureDisk(Integer.parseInt(replacement_PressureDisk.getText()));
            mainBreak.setResource_Reserve_Replacement_ReferenceDisk(Integer.parseInt(replacement_ReferenceDisk.getText()));
            mainBreak.setSerialNumber(numberMainBreak.getText());
        }
        WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);
        listMainBreaksTabController.updateTableMainBreaks();

    }

    public void setButtonVisible(String string) {
        if (string.equals("Добавить тормоз")) {
            createMainBreak.setVisible(true);
            changeMainBreak.setVisible(false);
            createMainBreakForAircraft.setVisible(false);
        } else if (string.equals("Изменить запись")) {
            changeMainBreak.setVisible(true);
            createMainBreak.setVisible(false);
            createMainBreakForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createMainBreak.setVisible(false);
            changeMainBreak.setVisible(false);
            createMainBreakForAircraft.setVisible(false);
        } else if (string.equals("Выполнить работ")) {
            createMainBreakForAircraft.setVisible(false);
            selectionWorks.setVisible(true);
            listOfWorks.setVisible(true);
            makeWork.setVisible(true);
        }
    }

    private void updateAircraftMainBreaks() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftMainBrake() == null) {
                System.out.println("Нет левого основного на самолете");
            } else if (aircraft.getLeftMainBrake().getSerialNumber().equals(mainBreak.getSerialNumber())) {
                changeMainBreak(aircraft.getLeftMainBrake());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
            if (aircraft.getRightEngine() == null) {
                System.out.println("Нет правого основного тормоза на самолете");
            } else if (aircraft.getRightEngine().getSerialNumberEngine().equals(mainBreak.getSerialNumber())) {
                changeMainBreak(aircraft.getRightMainBrake());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
        }
    }

    private void update_MainBreak_After_Work() {
        for (MainBreak e : SaveData.mainBreaksList) {
            if (e.getSerialNumber().equals(mainBreak.getSerialNumber())) {
                doWorksMainBreak(e, listOfWorks);
                WriteFile.serialization(SaveData.mainBreaksList, MainBreak.class);
            }
        }
    }
}
