package controllers.dialog;

import constants.TextConstants;
import data.Aircraft;
import data.Engineer;
import data.SaveData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.commons.lang3.StringUtils;

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

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
        rank.setText(String.valueOf(engineer.getRank().getDescription()));
        fullName.setText(engineer.getFullName());
        linkNumber.setText(engineer.getLink());
        ntzFullName.setText(engineer.getNtzFullName());
        listAircraft.setText(updateListAircraft());
    }

    /**
     * Метод сэтит бортовые номера самолетов, за которыми закреплен техник в графу, кторая находится в карточке инженера
     */

    public String updateListAircraft() {
        String aircraftList = "";
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (engineer.getFullName().equals(aircraft.getFullNameEngineer())) {
                aircraftList = aircraftList + aircraft.getAircraftNumber() + "; ";
            }
        }
        if (StringUtils.isBlank(aircraftList)) {
            return TextConstants.AIRCRAFT_TO_ENGINEER;
        } else {
            return aircraftList;
        }
    }
}
