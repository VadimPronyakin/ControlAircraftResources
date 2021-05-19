package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import sample.controllers.tab.ListMainBreaksTabController;
import sample.data.SaveData;
import sample.data.components.limitedResource.MainBreak;
import sample.write.WriteFile;

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

    @Setter
    private ListMainBreaksTabController listMainBreaksTabController;

    private MainBreak mainBreak;

    @FXML
    void initialize() {
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
            changeMainBreak();
            Stage stage = (Stage) changeMainBreak.getScene().getWindow();
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

//    public MainBreak returnMainBreak(String number) {
//        for (MainBreak e : SaveData.mainBreaksList) {
//            if (e.getSerialNumber().equals(number))
//                return e;
//        }
//        return null;
//    }

    public void changeMainBreak() {
        if (StringUtils.isNotBlank(replacementMainBreak.getCharacters())
                && StringUtils.isNotBlank(totalMainBreak.getCharacters())
                && StringUtils.isNotBlank(replacement_NonRotatingDisks.getCharacters())
                && StringUtils.isNotBlank(replacement_RotatingDisks.getCharacters())
                && StringUtils.isNotBlank(replacement_PressureDisk.getCharacters())
                && StringUtils.isNotBlank(replacement_ReferenceDisk.getCharacters())
                && StringUtils.isNotBlank(numberMainBreak.getCharacters())) {
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
        if(string.equals("Добавить тормоз")){
            createMainBreak.setVisible(true);
            changeMainBreak.setVisible(false);
            createMainBreakForAircraft.setVisible(false);
        } else if(string.equals("Изменить запись")) {
            changeMainBreak.setVisible(true);
            createMainBreak.setVisible(false);
            createMainBreakForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createMainBreak.setVisible(false);
            changeMainBreak.setVisible(false);
            createMainBreakForAircraft.setVisible(false);
        }
    }
}
