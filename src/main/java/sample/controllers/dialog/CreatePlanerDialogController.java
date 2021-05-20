package sample.controllers.dialog;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.data.SaveData;
import sample.data.components.Planer;
import sample.write.WriteFile;

import java.time.ZoneId;
import java.util.Date;

public class CreatePlanerDialogController {

    @FXML
    private TextField sideNumber;

    @FXML
    private TextField total_Landing_Count_Planer;

    @FXML
    private TextField before_30Days_Parking_Planer;

    @FXML
    private TextField before_6months_Operating_Planer_Months;

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
    private TextField before_6months_Operating_Planer_Days;

    @FXML
    private DatePicker last_Flight_Date_Planer;

    @FXML
    private DatePicker date_Work_After_6months_Operation_Planer;

    @FXML
    private Button createPlaner;

    @FXML
    private Button changePlaner;

    @FXML
    void initialize() {
        createPlaner.setOnAction(e -> {
            createPlaner();
            Stage stage = (Stage) createPlaner.getScene().getWindow();
            stage.close();
        });


    }

    private void createPlaner() {
        Planer planer = Planer.builder()
                .sideNumber(sideNumber.getText())
                .total_Landing_Count(Integer.parseInt(total_Landing_Count_Planer.getText()))
                .last_Flight_Date(Date.from(last_Flight_Date_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .days_Reserve_Before_30DaysParking(Integer.parseInt(before_30Days_Parking_Planer.getText()))
                .days_Reserve_Before_6months_Operating((Integer.parseInt(before_6months_Operating_Planer_Months.getText()) * 30) +
                        Integer.parseInt(before_6months_Operating_Planer_Days.getText()))
                .date_Work_After_6months_Operation(Date.from(date_Work_After_6months_Operation_Planer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
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
}

//(planer.getLast_Flight_Date().getTime() - planer.getDate_Work_After_6months_Operation().getTime()) / (24 * 60 * 60 * 1000)