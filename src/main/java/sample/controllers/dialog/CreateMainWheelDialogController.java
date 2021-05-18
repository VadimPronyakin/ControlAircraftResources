package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import sample.controllers.tab.ListMainWheelsTabController;
import sample.data.SaveData;
import sample.data.components.limitedResource.MainWheel;
import sample.write.WriteFile;

public class CreateMainWheelDialogController {
    ;

    @FXML
    private TextField numberMainWheel;

    @FXML
    private TextField totalMainWheel;

    @FXML
    private TextField replacementMainWheel;

    @FXML
    private Button createMainWheel;

    @FXML
    private Button changeMainWheel;

    @Setter
    private ListMainWheelsTabController listMainWheelsTabController;

    private MainWheel mainWheel;

    @FXML
    void initialize() {
        createMainWheel.setOnAction(e -> {
            addMainWheel();
            Stage stage = (Stage) createMainWheel.getScene().getWindow();
            stage.close();
        });
        changeMainWheel.setOnAction(e -> {
            changeMainWheel();
            Stage stage = (Stage) changeMainWheel.getScene().getWindow();
            stage.close();
        });
    }

   public void setMainWheel(MainWheel mainWheel) {
        this.mainWheel = mainWheel;
        totalMainWheel.setText(String.valueOf(mainWheel.getTotalLandings()));
        numberMainWheel.setText(mainWheel.getSerialNumber());
        replacementMainWheel.setText(String.valueOf(mainWheel.getResource_Reserve_Replacement_Wheel()));
    }

    private void addMainWheel() {
        MainWheel mainWheel = MainWheel.builder()
                .totalLandings(Integer.parseInt(totalMainWheel.getText()))
                .resource_Reserve_Replacement_Wheel(Integer.parseInt(replacementMainWheel.getText()))
                .serialNumber(numberMainWheel.getText())
                .build();
        SaveData.mainWheelsList.add(mainWheel);
        WriteFile.serialization(SaveData.mainWheelsList, MainWheel.class);
        listMainWheelsTabController.updateTableMainWheels();
    }

    public MainWheel returnMainWheel(String number) {
        for (MainWheel e : SaveData.mainWheelsList) {
            if (e.getSerialNumber().equals(number)) {
                return e;
            }
        }
        return null;
    }

    public void changeMainWheel() {
        if (StringUtils.isNotBlank(numberMainWheel.getCharacters())
                && StringUtils.isNotBlank(totalMainWheel.getCharacters())
                && StringUtils.isNotBlank(replacementMainWheel.getCharacters())) {
            mainWheel.setSerialNumber(numberMainWheel.getText());
            mainWheel.setTotalLandings(Integer.parseInt(totalMainWheel.getText()));
            mainWheel.setResource_Reserve_Replacement_Wheel(Integer.parseInt(replacementMainWheel.getText()));
        }
        WriteFile.serialization(SaveData.mainWheelsList, MainWheel.class);
        listMainWheelsTabController.updateTableMainWheels();

    }

    public void setButtonVisible(String string) {
        if (string.equals("Добавить колесо")) {
            createMainWheel.setVisible(true);
            changeMainWheel.setVisible(false);
        } else if (string.equals("Изменить запись")) {
            changeMainWheel.setVisible(true);
            createMainWheel.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createMainWheel.setVisible(false);
            changeMainWheel.setVisible(false);
        }
    }
}

