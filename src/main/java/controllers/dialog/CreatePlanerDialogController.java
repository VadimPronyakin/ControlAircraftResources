package controllers.dialog;


import constants.TextConstants;
import data.Aircraft;
import data.SaveData;
import data.components.Planer;
import data.enums.TypesOfWorks;
import io.writer.FilesWriter;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static builder.Builder.createPlaner;
import static java.lang.Integer.parseInt;
import static service.AircraftWorks.doWorksPlaner;
import static service.BooleanValueSetter.setBooleanValuePlaner;
import static service.DateResourcesCalculator.calculateDateInDays;
import static service.DateResourcesCalculator.calculateDateInMonth;
import static service.DuplicateProtection.planerProtection;
import static service.Notification.notificationPlaner;
import static utils.Utils.*;

@Slf4j
public class CreatePlanerDialogController {

    @FXML
    private TextField sideNumber;
    @FXML
    private TextField total_Landing_Count_Planer;
    @FXML
    private Label before_30Days_Parking_Planer;
    @FXML
    private TextField total_Operating_Planer_Hours;
    @FXML
    private TextField before_100hours_Planer_Hours;
    @FXML
    private TextField before_200hours_Planer_Hours;
    @FXML
    private TextField before_100hours_Planer_Minutes;
    @FXML
    private TextField before_200hours_Planer_Minutes;
    @FXML
    private TextField total_Operating_Planer_Minutes;
    @FXML
    private Label before_6months_Operating_Planer_Days;
    @FXML
    private DatePicker last_Flight_Date_Planer;
    @FXML
    private DatePicker date_Work_After_6months_Operation_Planer;
    @FXML
    private Button createPlaner;
    @FXML
    private Button changePlaner;
    @FXML
    private Text alarm_Resource_6months;
    @FXML
    private Text alarm30Days;
    @FXML
    private Text alarm100Hours;
    @FXML
    private Text alarm200Hours;
    @FXML
    private DatePicker date_Work_After_30Days_Parking_Planer;
    @FXML
    private ChoiceBox<TypesOfWorks> listOfWorksPlaner;
    @FXML
    private Text selectionWorks;
    @FXML
    private Text selectionDate;
    @FXML
    private DatePicker date_Make_Work;
    @FXML
    private Button makeWorksPlaner;

    private Planer planer;

    @Setter
    private PersonalAircraftDialogController personalAircraftDialogController;

    @FXML
    void initialize() {
        listOfWorksPlaner.getItems().addAll(TypesOfWorks.WORKS_AFTER_30_DAYS_PARKING,
                TypesOfWorks.WORKS_AFTER_6_MONTH_OPERATION,
                TypesOfWorks.WORKS_AFTER_100_HOURS_PLANER,
                TypesOfWorks.WORKS_AFTER_200_HOURS_PLANER);
        createPlaner.setOnAction(e -> {
            try {
                if (minutesChecker(returnFullArray()) && planerProtection(sideNumber)) {
                    addPlaner();
                    Stage stage = (Stage) createPlaner.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT_PLANER);
                log.error("При создании планера, пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        changePlaner.setOnAction(e -> {
            try {
                if (minutesChecker(returnFullArray())) {
                    updateAircraftPlaner();
                    changePlaner(planer);
                    personalAircraftDialogController.updateNotificationPlaner(planer);
                    Stage stage = (Stage) changePlaner.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException exception) {
                openInformationWindow(TextConstants.ONLY_INT_PLANER);
                log.error("При редактировании планера, пользователь пытался ввести символы в поля, +\n" +
                        "которые принимают только цифры + \n" +
                        "либо оставил какое-либо поле пустым {}", exception.getMessage());
            }
        });
        makeWorksPlaner.setOnAction(e -> {
            try {
                doWorksPlaner(planer, listOfWorksPlaner, date_Make_Work);
                personalAircraftDialogController.updateNotificationPlaner(planer);
                FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
                update_Planer_After_Work();
                Stage stage = (Stage) makeWorksPlaner.getScene().getWindow();
                stage.close();
            } catch (NullPointerException exception) {
                openInformationWindow(TextConstants.MAKE_WORKS_PLANER);
                log.error("Пользователь нажал кнопку выполнить работы в планере, не выбрав вид работ или дату {}",
                        exception.getMessage());
            }
        });
    }

    private TextField[] returnFullArray() {
        return new TextField[]{sideNumber, total_Landing_Count_Planer,
                total_Operating_Planer_Hours, total_Operating_Planer_Minutes,
                before_100hours_Planer_Hours, before_100hours_Planer_Minutes,
                before_200hours_Planer_Hours, before_200hours_Planer_Minutes};
    }

    public void setPlaner(Planer planer) {
        this.planer = planer;
        sideNumber.setText(planer.getSerialNumber());
        date_Work_After_6months_Operation_Planer.setValue(
                planer.getDate_Work_After_6months_Operation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        total_Landing_Count_Planer.setText(String.valueOf(planer.getTotal_Landing_Count()));
        before_30Days_Parking_Planer.setText(String.valueOf(planer.getDays_Reserve_Before_30DaysParking() / 86_400_000));
        before_6months_Operating_Planer_Days.setText(String.valueOf(planer.getDays_Reserve_Before_6months_Operating() / 86_400_000));
        total_Operating_Planer_Hours.setText(String.valueOf(planer.getTotal_Operating_Time() / 60));
        total_Operating_Planer_Minutes.setText(String.valueOf(planer.getTotal_Operating_Time() % 60));
        before_100hours_Planer_Hours.setText(String.valueOf(planer.getResource_Reserve_Before_100hours() / 60));
        before_100hours_Planer_Minutes.setText(String.valueOf(planer.getResource_Reserve_Before_100hours() % 60));
        before_200hours_Planer_Hours.setText(String.valueOf(planer.getResource_Reserve_Before_200hours() / 60));
        before_200hours_Planer_Minutes.setText(String.valueOf(planer.getResource_Reserve_Before_200hours() % 60));
        last_Flight_Date_Planer.setValue(planer.getLast_Flight_Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        date_Work_After_30Days_Parking_Planer.setValue(
                planer.getDate_Work_After_30days_Parking().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        List<Text> textOfAlarms = new ArrayList<>();
        textOfAlarms.add(alarm30Days);
        textOfAlarms.add(alarm_Resource_6months);
        textOfAlarms.add(alarm100Hours);
        textOfAlarms.add(alarm200Hours);
        notificationPlaner(planer,
                before_30Days_Parking_Planer,
                before_6months_Operating_Planer_Days,
                textOfAlarms);
    }

    /**
     * Метод добавляет новый планер
     */
    private void addPlaner() {
        createPlaner(returnFullArray(),
                last_Flight_Date_Planer,
                date_Work_After_6months_Operation_Planer,
                date_Work_After_30Days_Parking_Planer);
        log.info("Пользователь создал планер");
    }

    /**
     * Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем
     */
    public void setButtonVisible(boolean isCreate, boolean isChange,
                                 boolean isSelectionWorks, boolean isSelectionDate,
                                 boolean isVisibleList, boolean isDateMakeWorks,
                                 boolean isMakeWorks) {
        createPlaner.setVisible(isCreate);
        changePlaner.setVisible(isChange);
        selectionDate.setVisible(isSelectionDate);
        selectionWorks.setVisible(isSelectionWorks);
        listOfWorksPlaner.setVisible(isVisibleList);
        date_Make_Work.setVisible(isDateMakeWorks);
        makeWorksPlaner.setVisible(isMakeWorks);
    }

    /**
     * Метод позволяет вносить изменения в существующий планер
     */
    public void changePlaner(Planer planer) {
        if (checkInputPlaner(last_Flight_Date_Planer, date_Work_After_6months_Operation_Planer,
                date_Work_After_30Days_Parking_Planer, sideNumber,
                total_Landing_Count_Planer, total_Operating_Planer_Hours,
                before_100hours_Planer_Hours, before_200hours_Planer_Hours,
                before_100hours_Planer_Minutes, before_200hours_Planer_Minutes,
                total_Operating_Planer_Minutes)) {
            planer.setSerialNumber(sideNumber.getText());
            planer.setTotal_Landing_Count(parseInt(total_Landing_Count_Planer.getText()));
            planer.setLast_Flight_Date(Date.from(
                    last_Flight_Date_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            planer.setDate_Work_After_6months_Operation(Date.from(
                    date_Work_After_6months_Operation_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            planer.setDate_Work_After_30days_Parking(Date.from(
                    date_Work_After_30Days_Parking_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            planer.setResource_Reserve_Before_100hours((parseInt(before_100hours_Planer_Hours.getText()) * 60) +
                    parseInt(before_100hours_Planer_Minutes.getText()));
            planer.setResource_Reserve_Before_200hours((parseInt(before_200hours_Planer_Hours.getText()) * 60) +
                    parseInt(before_200hours_Planer_Minutes.getText()));
            planer.setTotal_Operating_Time((parseInt(total_Operating_Planer_Hours.getText()) * 60) +
                    parseInt(total_Operating_Planer_Minutes.getText()));
        }
        planer.setDays_Reserve_Before_30DaysParking(calculateDateInDays(Date.from(
                last_Flight_Date_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(date_Work_After_30Days_Parking_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
        planer.setDays_Reserve_Before_6months_Operating(calculateDateInMonth(Date.from(
                date_Work_After_6months_Operation_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
        planer.setIsNeedAttention(setBooleanValuePlaner(planer));
        FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
        log.info("Пользователь внес изменения в планер");
    }

    /**
     * Метод сериализует все изменения планера, совершаемые в карточке самолета в файл, где хранятся все планеры.
     */
    private void updateAircraftPlaner() {
        for (Planer planer : SaveData.planersList) {
            if (planer.getSerialNumber().equals(this.planer.getSerialNumber())) {
                changePlaner(planer);
                FilesWriter.serialization(SaveData.planersList, Planer.class);
            }
        }
    }

    /**
     * Метод сериализует изменения, после выполнения работ по планеру в карточке самолета, в файл, где хранятся все планеры.
     */
    private void update_Planer_After_Work() {
        for (Planer planerFromList : SaveData.planersList) {
            if (planerFromList.getSerialNumber().equals(planer.getSerialNumber())) {
                doWorksPlaner(planerFromList, listOfWorksPlaner, date_Make_Work);
                FilesWriter.serialization(SaveData.planersList, Planer.class);
            }
        }
    }
}
