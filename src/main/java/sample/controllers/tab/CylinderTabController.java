package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import sample.data.SaveData;
import sample.data.components.limitedResource.*;
import sample.write.WriteFile;


public class CylinderTabController {

    @FXML
    private Button createCylinder;

    @FXML
    private Button changeCylinder;

    @FXML
    private TextField first_Repair_Cylinder;

    @FXML
    private TextField totalCylinder;

    @FXML
    private TextField second_Repair_Cylinder;

    @FXML
    private TextField replacementCylinder;

    @FXML
    private TextField numberCylinder;

    private CylinderOfRetractionExtension cylinder;

    @FXML
    void initialize() {

        createCylinder.setOnAction(e -> addCylinder());
        changeCylinder.setOnAction(e -> changeCylinder());
    }

    void setCylinder(String number) {
        this.cylinder = returnCylinder(number);
        first_Repair_Cylinder.setText(String.valueOf(cylinder.getResource_Reserve_Before_First_Repair()));
        totalCylinder.setText(String.valueOf(cylinder.getTotalLandings()));
        second_Repair_Cylinder.setText(String.valueOf(cylinder.getResource_Reserve_Before_Second_Repair()));
        replacementCylinder.setText(String.valueOf(cylinder.getResource_Reserve_Before_Replacement()));
        numberCylinder.setText(cylinder.getSerialNumber());
    }

    private void addCylinder() {
        CylinderOfRetractionExtension cylinder = CylinderOfRetractionExtension.builder()
                .totalLandings(Integer.parseInt(totalCylinder.getText()))
                .resource_Reserve_Before_First_Repair(Integer.parseInt(first_Repair_Cylinder.getText()))
                .resource_Reserve_Before_Second_Repair(Integer.parseInt(second_Repair_Cylinder.getText()))
                .resource_Reserve_Before_Replacement(Integer.parseInt(replacementCylinder.getText()))
                .serialNumber(numberCylinder.getText())
                .build();
        SaveData.cylindersList.add(cylinder);
        WriteFile.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
    }
    public CylinderOfRetractionExtension returnCylinder(String number) {
        for (CylinderOfRetractionExtension e : SaveData.cylindersList) {
            if (e.getSerialNumber().equals(number)) {
                return e;
            }
        }
        return null;
    }
    public void changeCylinder() {
        if (StringUtils.isNotBlank(first_Repair_Cylinder.getCharacters())
                && StringUtils.isNotBlank(second_Repair_Cylinder.getCharacters())
                && StringUtils.isNotBlank(totalCylinder.getCharacters())
                && StringUtils.isNotBlank(replacementCylinder.getCharacters())
                && StringUtils.isNotBlank(numberCylinder.getCharacters())) {
            cylinder.setResource_Reserve_Before_First_Repair(Integer.parseInt(first_Repair_Cylinder.getText()));
            cylinder.setResource_Reserve_Before_Second_Repair(Integer.parseInt(second_Repair_Cylinder.getText()));
            cylinder.setTotalLandings(Integer.parseInt(totalCylinder.getText()));
            cylinder.setSerialNumber(numberCylinder.getText());
            cylinder.setResource_Reserve_Before_Replacement(Integer.parseInt(replacementCylinder.getText()));
        }
        WriteFile.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
    }
}

