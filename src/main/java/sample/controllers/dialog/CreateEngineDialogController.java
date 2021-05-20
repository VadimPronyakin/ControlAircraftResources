package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import sample.controllers.tab.ListAllEnginesTabController;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.write.WriteFile;




import static java.lang.Integer.parseInt;
import static sample.utils.Utils.checkInput;

public class CreateEngineDialogController {

    @FXML
    private TextField numberEngine;

    @FXML
    private TextField totalStartingEngine;

    @FXML
    private TextField totalOperatingEngineHours;

    @FXML
    private TextField before_278bulletinEngineMinutes;

    @FXML
    private TextField before_10hoursEngineHours;

    @FXML
    private TextField totalOperatingEngineMinutes;

    @FXML
    private TextField before_25hoursEngineHours;

    @FXML
    private TextField before_10hoursEngineMinutes;

    @FXML
    private TextField before_278bulletinEngineHours;

    @FXML
    private TextField before_25hoursEngineMinutes;

    @FXML
    private TextField before_50hoursEngineHours;

    @FXML
    private TextField before_50hoursEngineMinutes;

    @FXML
    private TextField before_100hoursEngineHours;

    @FXML
    private TextField before_100hoursEngineMinutes;

    @FXML
    private TextField before_150hoursEngineHours;

    @FXML
    private TextField before_150hoursEngineMinutes;

    @FXML
    private TextField oilChangeEngineHours;

    @FXML
    private TextField oilChangeEngineMinutes;

    @FXML
    private Button createEngine;

    @FXML
    private Button changeEngine;

    @FXML
    private Button createEngineForAircraft;

    @Setter
    private ListAllEnginesTabController listAllEnginesTabController;

    private Engine engine;

    @FXML
    void initialize() {
        createEngineForAircraft.setOnAction(e -> {
           addEngine();
            Stage stage = (Stage) createEngineForAircraft.getScene().getWindow();
            stage.close();
        });
        createEngine.setOnAction(e -> {
            addEngine();
            listAllEnginesTabController.updateTableEngines();
            Stage stage = (Stage) createEngine.getScene().getWindow();
            stage.close();
        });
        changeEngine.setOnAction(e -> {
            changeEngine();
            Stage stage = (Stage) changeEngine.getScene().getWindow();
            stage.close();
        });
    }

    public void setButtonVisible(String string) {
        if (string.equals("Добавить двигатель")) {
            createEngine.setVisible(true);
            changeEngine.setVisible(false);
            createEngineForAircraft.setVisible(false);
        } else if (string.equals("Изменить запись")) {
            changeEngine.setVisible(true);
            createEngine.setVisible(false);
            createEngineForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createEngine.setVisible(false);
            changeEngine.setVisible(false);
            createEngineForAircraft.setVisible(false);
        }
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
        numberEngine.setText(engine.getSerialNumberEngine());
        totalStartingEngine.setText(String.valueOf(engine.getTotalStartingEngineCount()));
        oilChangeEngineHours.setText(String.valueOf(engine.getOilChange() / 60));
        oilChangeEngineMinutes.setText(String.valueOf((engine.getOilChange() % 60)));
        before_10hoursEngineHours.setText(String.valueOf(engine.getResourceReserveBefore_10hours() / 60));
        before_10hoursEngineMinutes.setText(String.valueOf(engine.getResourceReserveBefore_10hours() % 60));
        before_25hoursEngineHours.setText(String.valueOf(engine.getResourceReserveBefore_25hours() / 60));
        before_25hoursEngineMinutes.setText(String.valueOf(engine.getResourceReserveBefore_25hours() % 60));
        before_50hoursEngineHours.setText(String.valueOf(engine.getResourceReserveBefore_50hours() / 60));
        before_50hoursEngineMinutes.setText(String.valueOf(engine.getResourceReserveBefore_50hours() % 60));
        before_100hoursEngineHours.setText(String.valueOf(engine.getResourceReserveBefore_100hours() / 60));
        before_100hoursEngineMinutes.setText(String.valueOf(engine.getResourceReserveBefore_100hours() % 60));
        before_150hoursEngineHours.setText(String.valueOf(engine.getResourceReserveBefore_150hours() / 60));
        before_150hoursEngineMinutes.setText(String.valueOf(engine.getResourceReserveBefore_150hours() % 60));
        before_278bulletinEngineHours.setText(String.valueOf(engine.getResourceReserveBefore_278bulletin() / 60));
        before_278bulletinEngineMinutes.setText(String.valueOf(engine.getResourceReserveBefore_278bulletin() % 60));
        totalOperatingEngineHours.setText(String.valueOf(engine.getTotalOperatingTime() / 60));
        totalOperatingEngineMinutes.setText(String.valueOf(engine.getTotalOperatingTime() % 60));

    }

    @FXML
    private void addEngine() {
        Engine engine = Engine.builder()
                .totalOperatingTime((Integer.parseInt(totalOperatingEngineHours.getText()) * 60) +
                        parseInt(totalOperatingEngineMinutes.getText()))
                .totalStartingEngineCount(Integer.parseInt(totalStartingEngine.getText()))
                .resourceReserveBefore_10hours((Integer.parseInt(before_10hoursEngineHours.getText()) * 60) +
                        parseInt(before_10hoursEngineMinutes.getText()))
                .resourceReserveBefore_25hours((parseInt(before_25hoursEngineHours.getText()) * 60) +
                        parseInt(before_25hoursEngineMinutes.getText()))
                .resourceReserveBefore_50hours((parseInt(before_50hoursEngineHours.getText()) * 60) +
                        parseInt(before_50hoursEngineMinutes.getText()))
                .resourceReserveBefore_100hours((parseInt(before_100hoursEngineHours.getText()) * 60) +
                        parseInt(before_100hoursEngineMinutes.getText()))
                .resourceReserveBefore_150hours((parseInt(before_150hoursEngineHours.getText()) * 60) +
                        parseInt(before_150hoursEngineMinutes.getText()))
                .resourceReserveBefore_278bulletin((parseInt(before_278bulletinEngineHours.getText()) * 60) +
                        parseInt(before_278bulletinEngineMinutes.getText()))
                .oilChange((parseInt(oilChangeEngineHours.getText()) * 60) +
                        parseInt(oilChangeEngineMinutes.getText()))
                .serialNumberEngine(numberEngine.getText())
                .build();
        SaveData.enginesList.add(engine);
        WriteFile.serialization(SaveData.enginesList, Engine.class);
    }


    public void changeEngine() {
        if(checkInput(before_10hoursEngineMinutes, before_25hoursEngineHours,
                before_25hoursEngineMinutes, before_50hoursEngineHours,
                before_50hoursEngineMinutes, before_100hoursEngineHours,
                before_100hoursEngineMinutes, before_150hoursEngineHours,
                before_150hoursEngineMinutes, before_278bulletinEngineHours,
                before_278bulletinEngineMinutes, oilChangeEngineHours,
                totalOperatingEngineHours, totalOperatingEngineMinutes,
                totalStartingEngine, numberEngine)) {
            engine.setResourceReserveBefore_10hours((parseInt(before_10hoursEngineHours.getText()) * 60) +
                    parseInt(before_10hoursEngineMinutes.getText()));
            engine.setResourceReserveBefore_25hours((parseInt(before_25hoursEngineHours.getText()) * 60) +
                    parseInt(before_25hoursEngineMinutes.getText()));
            engine.setResourceReserveBefore_50hours((parseInt(before_50hoursEngineHours.getText()) * 60) +
                    parseInt(before_50hoursEngineMinutes.getText()));
            engine.setResourceReserveBefore_100hours((parseInt(before_100hoursEngineHours.getText()) * 60) +
                    parseInt(before_100hoursEngineMinutes.getText()));
            engine.setResourceReserveBefore_150hours((parseInt(before_150hoursEngineHours.getText()) * 60) +
                    parseInt(before_150hoursEngineMinutes.getText()));
            engine.setResourceReserveBefore_278bulletin((parseInt(before_278bulletinEngineHours.getText()) * 60) +
                    parseInt(before_278bulletinEngineMinutes.getText()));
            engine.setOilChange((parseInt(oilChangeEngineHours.getText()) * 60) +
                    parseInt(oilChangeEngineMinutes.getText()));
            engine.setSerialNumberEngine(numberEngine.getText());
            engine.setTotalOperatingTime((parseInt(totalOperatingEngineHours.getText()) * 60) +
                    parseInt(totalOperatingEngineMinutes.getText()));
            engine.setTotalStartingEngineCount(parseInt(totalStartingEngine.getText()));

        }
        WriteFile.serialization(SaveData.enginesList, Engine.class);
        listAllEnginesTabController.updateTableEngines();

    }
}

