package sample.controllers.tab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import sample.constants.TextConstants;
import sample.data.Engineer;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.update.UpdateList;
import sample.write.WriteFile;


public class EngineTabController {

    @FXML
    private Button createEngine;

    @FXML
    private Button changeEngine;

    @FXML
    private TextField totalStartingEngine;

    @FXML
    private TextField totalOperatingEngine;

    @FXML
    private TextField before_10hoursEngine;

    @FXML
    private TextField before_25hoursEngine;

    @FXML
    private TextField before_278bulletinEngine;

    @FXML
    private TextField before_50hoursEngine;

    @FXML
    private TextField before_100hoursEngine;

    @FXML
    private TextField before_150hoursEngine;

    @FXML
    private TextField oilChangeEngine;

    @FXML
    private TextField numberEngine;


    @FXML
    void initialize() {
        createEngine.setOnAction(e -> addEngine());
        changeEngine.setOnAction(e -> changeEngine());
    }

    @FXML
    private void addEngine() {
        Engine engine = Engine.builder()
                .totalOperatingTime(Integer.parseInt(totalOperatingEngine.getText()))
                .totalStartingEngineCount(Integer.parseInt(totalStartingEngine.getText()))
                .resourceReserveBefore_10hours(Integer.parseInt(before_10hoursEngine.getText()))
                .resourceReserveBefore_25hours(Integer.parseInt(before_25hoursEngine.getText()))
                .resourceReserveBefore_50hours(Integer.parseInt(before_50hoursEngine.getText()))
                .resourceReserveBefore_100hours(Integer.parseInt(before_100hoursEngine.getText()))
                .resourceReserveBefore_150hours(Integer.parseInt(before_150hoursEngine.getText()))
                .resourceReserveBefore_278bulletin(Integer.parseInt(before_278bulletinEngine.getText()))
                .oilChange(Integer.parseInt(oilChangeEngine.getText()))
                .serialNumberEngine(numberEngine.getText())
                .build();
        SaveData.enginesList.add(engine);
        WriteFile.serialization(SaveData.enginesList, Engine.class);
    }
    public void changeEngine() {
        ListAllEnginesTabController listAllEnginesTabController = new ListAllEnginesTabController();
        Engine engine = listAllEnginesTabController.returnEngine();
        if (StringUtils.isNotBlank(before_10hoursEngine.getCharacters())
                && StringUtils.isNotBlank(before_25hoursEngine.getCharacters())
                && StringUtils.isNotBlank(before_50hoursEngine.getCharacters())
                && StringUtils.isNotBlank(before_100hoursEngine.getCharacters())
                && StringUtils.isNotBlank(before_150hoursEngine.getCharacters())
                && StringUtils.isNotBlank(before_278bulletinEngine.getCharacters())
                && StringUtils.isNotBlank(oilChangeEngine.getCharacters())) {
            engine.setResourceReserveBefore_10hours(Integer.parseInt(before_10hoursEngine.getText()));
            engine.setResourceReserveBefore_25hours(Integer.parseInt(before_25hoursEngine.getText()));
            engine.setResourceReserveBefore_50hours(Integer.parseInt(before_50hoursEngine.getText()));
            engine.setResourceReserveBefore_100hours(Integer.parseInt(before_100hoursEngine.getText()));
            engine.setResourceReserveBefore_150hours(Integer.parseInt(before_150hoursEngine.getText()));
            engine.setResourceReserveBefore_278bulletin(Integer.parseInt(before_278bulletinEngine.getText()));
            engine.setOilChange(Integer.parseInt(oilChangeEngine.getText()));

        }
        WriteFile.serialization(SaveData.enginesList, Engine.class);
    }
}

