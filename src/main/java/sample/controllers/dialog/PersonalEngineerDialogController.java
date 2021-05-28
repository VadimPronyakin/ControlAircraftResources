package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.data.Aircraft;
import sample.data.Engineer;
import sample.data.SaveData;

public class PersonalEngineerDialogController {

    @FXML
    private Label rank;

    @FXML
    private Label fullName;

    @FXML
    private Label listAircraft;

    @FXML
    private Label linkNumber;

    @FXML
    private Label ntzFullName;

    private Engineer engineer;
    @FXML
    void initialize() {
    }
    public void setEngineer(Engineer engineer1) {
        this.engineer = engineer1;
        rank.textProperty().setValue(String.valueOf(engineer1.getRank().getDescription()));
        fullName.textProperty().setValue(engineer1.getFullName());
        linkNumber.textProperty().setValue(engineer1.getLink());
        ntzFullName.textProperty().setValue(String.valueOf(engineer1.getNtzFullName()));
    }
    public void updateListAircraft() {
        String aircraftList = null;
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (engineer.getFullName().equals(aircraft.getFullNameEngineer())) {
                aircraftList = aircraft.getAircraftNumber() + "; ";
            }
        }
        if (aircraftList == null) {
            listAircraft.setText("За данным инженером(АК) нет закрпеленых самолетов");
        } else {
            listAircraft.setText(aircraftList);
        }
    }
}
