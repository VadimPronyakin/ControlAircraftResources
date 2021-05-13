package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    @FXML
    void initialize() {

        createCylinder.setOnAction(e -> addCylinder());
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
}

