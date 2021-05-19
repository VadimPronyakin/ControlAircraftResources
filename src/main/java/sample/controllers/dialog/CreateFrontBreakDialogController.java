package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import sample.controllers.tab.ListFrontBreakTabController;
import sample.data.SaveData;
import sample.data.components.limitedResource.FrontBreak;
import sample.write.WriteFile;

public class CreateFrontBreakDialogController {

    @FXML
    private TextField numberFrontBreak;

    @FXML
    private TextField totalFrontBreak;

    @FXML
    private TextField first_Repair_FrontBreak;

    @FXML
    private TextField replacementFrontBreak;

    @FXML
    private Button createFrontBreak;

    @FXML
    private Button changeFrontBreak;

    @FXML
    private Button createFrontBreakForAircraft;

    @Setter
    private ListFrontBreakTabController listFrontBreakTabController;

    private FrontBreak frontBreak;

    @FXML
    void initialize() {
       createFrontBreakForAircraft.setOnAction(e -> {
           addFrontBreak();
           Stage stage = (Stage) createFrontBreakForAircraft.getScene().getWindow();
           stage.close();
       });
        createFrontBreak.setOnAction(e -> {
            addFrontBreak();
            listFrontBreakTabController.updateTableFrontBreak();
            Stage stage = (Stage) createFrontBreak.getScene().getWindow();
            stage.close();
        });
        changeFrontBreak.setOnAction(e -> {
            changeFrontBreak();
            Stage stage = (Stage) changeFrontBreak.getScene().getWindow();
            stage.close();
        });
    }

   public void setFrontBreak(FrontBreak frontBreak) {
        this.frontBreak = frontBreak;
        numberFrontBreak.setText(frontBreak.getSerialNumber());
        first_Repair_FrontBreak.setText(String.valueOf(frontBreak.getResource_Reserve_Before_First_Repair()));
        totalFrontBreak.setText(String.valueOf(frontBreak.getTotalLandings()));
        replacementFrontBreak.setText(String.valueOf(frontBreak.getResource_Reserve_Before_Replacement()));
    }

    private void addFrontBreak() {
        FrontBreak frontBreak = FrontBreak.builder()
                .totalLandings(Integer.parseInt(totalFrontBreak.getText()))
                .resource_Reserve_Before_First_Repair(Integer.parseInt(first_Repair_FrontBreak.getText()))
                .resource_Reserve_Before_Replacement(Integer.parseInt(replacementFrontBreak.getText()))
                .serialNumber(numberFrontBreak.getText())
                .build();
        SaveData.frontBreaksList.add(frontBreak);
        WriteFile.serialization(SaveData.frontBreaksList, FrontBreak.class);

    }

//    public FrontBreak returnFrontBreak(String number) {
//        for (FrontBreak e : SaveData.frontBreaksList) {
//            if (e.getSerialNumber().equals(number)) {
//                return e;
//            }
//        }
//        return null;
//    }

    public void changeFrontBreak() {
        if (StringUtils.isNotBlank(numberFrontBreak.getCharacters())
                && StringUtils.isNotBlank(first_Repair_FrontBreak.getCharacters())
                && StringUtils.isNotBlank(totalFrontBreak.getCharacters())
                && StringUtils.isNotBlank(replacementFrontBreak.getCharacters())) {
            frontBreak.setResource_Reserve_Before_First_Repair(Integer.parseInt(first_Repair_FrontBreak.getText()));
            frontBreak.setResource_Reserve_Before_Replacement(Integer.parseInt(replacementFrontBreak.getText()));
            frontBreak.setSerialNumber(numberFrontBreak.getText());
            frontBreak.setTotalLandings(Integer.parseInt(totalFrontBreak.getText()));
        }
        WriteFile.serialization(SaveData.frontBreaksList, FrontBreak.class);
        listFrontBreakTabController.updateTableFrontBreak();

    }

   public void setButtonVisible(String string) {
        if (string.equals("Добавить тормоз")) {
            createFrontBreak.setVisible(true);
            changeFrontBreak.setVisible(false);
            createFrontBreakForAircraft.setVisible(false);
        } else if (string.equals("Изменить запись")) {
            changeFrontBreak.setVisible(true);
            createFrontBreak.setVisible(false);
            createFrontBreakForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createFrontBreak.setVisible(false);
            changeFrontBreak.setVisible(false);
            createFrontBreakForAircraft.setVisible(false);
       }

    }
}

