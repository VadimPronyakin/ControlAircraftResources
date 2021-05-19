package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import sample.controllers.tab.ListAllEnginesTabController;
import sample.controllers.tab.ListCylindersTabController;
import sample.data.SaveData;
import sample.data.components.limitedResource.CylinderOfRetractionExtension;
import sample.write.WriteFile;

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

    @Setter
    private ListCylindersTabController listCylindersTabController;

    private CylinderOfRetractionExtension cylinder;

    @FXML
    void initialize() {
        createCylinderForAircraft.setOnAction(e -> {
            addCylinder();
            Stage stage = (Stage) createCylinderForAircraft.getScene().getWindow();
            stage.close();
        });
        createCylinder.setOnAction(e -> {
            addCylinder();
            listCylindersTabController.updateTableCylinders();
            Stage stage = (Stage) createCylinder.getScene().getWindow();
            stage.close();
        });
        changeCylinder.setOnAction(e -> {
            changeCylinder();
            Stage stage = (Stage) changeCylinder.getScene().getWindow();
            stage.close();
        });
    }
    public void setCylinder(CylinderOfRetractionExtension cylinder) {
        this.cylinder = cylinder;
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
        listCylindersTabController.updateTableCylinders();

    }

    public void setButtonVisible(String string) {
        if(string.equals("Добавить цилиндр")){
            createCylinder.setVisible(true);
            changeCylinder.setVisible(false);
            createCylinderForAircraft.setVisible(false);
        } else if(string.equals("Изменить запись")) {
            changeCylinder.setVisible(true);
            createCylinder.setVisible(false);
            createCylinderForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createCylinder.setVisible(false);
            changeCylinder.setVisible(false);
            createCylinderForAircraft.setVisible(false);
        }
    }
}