package sample.controllers.dialog;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import sample.calculating.CalculatingDateResources;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.components.Planer;
import sample.data.enums.TypesOfWorks;
import sample.works.MakeWorks;
import sample.write.WriteFile;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;
import static sample.calculating.CalculatingDateResources.calculateDateInMonth;
import static sample.notification.NotificationAircraft.notificationPlaner;
import static sample.utils.Utils.checkInput;
import static sample.utils.Utils.checkInputPlaner;


public class CreatePlanerDialogController {

    List<Text> textOfAlarms = new ArrayList<>();
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
            createPlaner();
            Stage stage = (Stage) createPlaner.getScene().getWindow();
            stage.close();
        });
        changePlaner.setOnAction(e -> {
            updateAircraftPlaner();
            changePlaner(planer);
            Stage stage = (Stage) changePlaner.getScene().getWindow();
            stage.close();
        });
        makeWorksPlaner.setOnAction(e -> {
            MakeWorks.doWorksPlaner(planer, listOfWorksPlaner, date_Make_Work);
            WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            update_Planer_After_Work();
            Stage stage = (Stage) makeWorksPlaner.getScene().getWindow();
            stage.close();
        });
    }

    public void setPlaner(Planer planer1) {
        this.planer = planer1;
        sideNumber.setText(planer1.getSideNumber());
        date_Work_After_6months_Operation_Planer.setValue(planer1.getDate_Work_After_6months_Operation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        total_Landing_Count_Planer.setText(String.valueOf(planer1.getTotal_Landing_Count()));
        before_30Days_Parking_Planer.setText(String.valueOf(CalculatingDateResources.calculateDateInDays(planer1.getLast_Flight_Date().getTime(),
                planer1.getDate_Work_After_30days_Parking().getTime()) / 86_400_000));
        before_6months_Operating_Planer_Days.setText(String.valueOf(calculateDateInMonth(planer1.getDate_Work_After_6months_Operation().getTime()) / 86_400_000));
        total_Operating_Planer_Hours.setText(String.valueOf(planer1.getTotal_Operating_Time() / 60));
        total_Operating_Planer_Minutes.setText(String.valueOf(planer1.getTotal_Operating_Time() % 60));
        before_100hours_Planer_Hours.setText(String.valueOf(planer1.getResource_Reserve_Before_100hours() / 60));
        before_100hours_Planer_Minutes.setText(String.valueOf(planer1.getResource_Reserve_Before_100hours() % 60));
        before_200hours_Planer_Hours.setText(String.valueOf(planer1.getResource_Reserve_Before_200hours() / 60));
        before_200hours_Planer_Minutes.setText(String.valueOf(planer1.getResource_Reserve_Before_200hours() % 60));
        last_Flight_Date_Planer.setValue(planer1.getLast_Flight_Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        date_Work_After_30Days_Parking_Planer.setValue(planer1.getDate_Work_After_30days_Parking().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        textOfAlarms.add(alarm30Days);
        textOfAlarms.add(alarm_Resource_6months);
        textOfAlarms.add(alarm100Hours);
        textOfAlarms.add(alarm200Hours);
        notificationPlaner(planer1,
                before_30Days_Parking_Planer,
                before_6months_Operating_Planer_Days,
                textOfAlarms);
    }

    private void createPlaner() {
        Planer planer = Planer.builder()
                .sideNumber(sideNumber.getText())
                .total_Landing_Count(Integer.parseInt(total_Landing_Count_Planer.getText()))
                .last_Flight_Date(Date.from(last_Flight_Date_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .date_Work_After_6months_Operation(Date.from(date_Work_After_6months_Operation_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .date_Work_After_30days_Parking(Date.from(date_Work_After_30Days_Parking_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .total_Operating_Time((Integer.parseInt(total_Operating_Planer_Hours.getText()) * 60) +
                        Integer.parseInt(total_Operating_Planer_Minutes.getText()))
                .resource_Reserve_Before_100hours((Integer.parseInt(before_100hours_Planer_Hours.getText()) * 60) +
                        Integer.parseInt(before_100hours_Planer_Minutes.getText()))
                .resource_Reserve_Before_200hours((Integer.parseInt(before_200hours_Planer_Hours.getText()) * 60) +
                        Integer.parseInt(before_200hours_Planer_Minutes.getText()))
                .build();
        SaveData.planersList.add(planer);
        WriteFile.serialization(SaveData.planersList, Planer.class);
    }

    public void setButtonVisible(String string) {
        if (string.equals("Двойное нажатие")) {
            createPlaner.setVisible(false);
            changePlaner.setVisible(true);
            selectionDate.setVisible(true);
            selectionWorks.setVisible(true);
            listOfWorksPlaner.setVisible(true);
            date_Make_Work.setVisible(true);
            makeWorksPlaner.setVisible(true);
        }
    }
    public void changePlaner(Planer planer) {
        if(checkInputPlaner(last_Flight_Date_Planer, date_Work_After_6months_Operation_Planer,
                date_Work_After_30Days_Parking_Planer, sideNumber,
                total_Landing_Count_Planer, total_Operating_Planer_Hours,
                before_100hours_Planer_Hours, before_200hours_Planer_Hours,
                before_100hours_Planer_Minutes, before_200hours_Planer_Minutes,
                total_Operating_Planer_Minutes)) {
            planer.setSideNumber(sideNumber.getText());
            planer.setTotal_Landing_Count(parseInt(total_Landing_Count_Planer.getText()));
            planer.setLast_Flight_Date(Date.from(last_Flight_Date_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
           //если не сработает вынести за тело if
//            planer.setDays_Reserve_Before_30DaysParking(CalculatingDateResources.calculateDateInDays(planer.getDays_Reserve_Before_30DaysParking()));
            planer.setDate_Work_After_6months_Operation(Date.from(date_Work_After_6months_Operation_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//            planer.setDays_Reserve_Before_6months_Operating(CalculatingDateResources.calculateDateInMonth(planer.getDays_Reserve_Before_6months_Operating()));
            planer.setDate_Work_After_30days_Parking(Date.from(date_Work_After_30Days_Parking_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            planer.setResource_Reserve_Before_100hours((parseInt(before_100hours_Planer_Hours.getText()) * 60) +
                    parseInt(before_100hours_Planer_Minutes.getText()));
            planer.setResource_Reserve_Before_200hours((parseInt(before_200hours_Planer_Hours.getText()) * 60) +
                    parseInt(before_200hours_Planer_Minutes.getText()));
            planer.setTotal_Operating_Time((parseInt(total_Operating_Planer_Hours.getText()) * 60) +
                    parseInt(total_Operating_Planer_Minutes.getText()));
        }
        WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
    }
    private void updateAircraftPlaner() {
        for (Planer planer : SaveData.planersList) {
             if (planer.getSideNumber().equals(this.planer.getSideNumber())){
                changePlaner(planer);
                WriteFile.serialization(SaveData.planersList, Planer.class);
            }
        }
    }
    private void update_Planer_After_Work() {
        for (Planer e : SaveData.planersList) {
            if (e.getSideNumber().equals(planer.getSideNumber())) {
               MakeWorks.doWorksPlaner(e, listOfWorksPlaner, date_Make_Work);
                WriteFile.serialization(SaveData.planersList, Planer.class);
            }
        }
    }
}
