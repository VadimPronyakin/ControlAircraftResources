package sample.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import sample.controllers.tab.ListAllEnginesTabController;
import sample.data.Aircraft;
import sample.data.SaveData;
import sample.data.components.Engine;
import sample.data.enums.TypesOfWorks;
import sample.write.WriteFile;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static sample.builder.Builder.createEngine;
import static sample.notification.Notification.notificationEngine;
import static sample.setBoolean.SetBooleanValue.setBooleanValueEngine;
import static sample.utils.Utils.checkInput;
import static sample.works.MakeWorks.doWorksEngine;

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
    @FXML
    private ComboBox<TypesOfWorks> listOfWorksEngine;
    @FXML
    private Text selectionWorks;
    @FXML
    private Button makeWorksEngine;
    @FXML
    private Text alarm10Hours;
    @FXML
    private Text alarm25Hours;
    @FXML
    private Text alarm278Bulletin;
    @FXML
    private Text alarm50Hours;
    @FXML
    private Text alarm100Hours;
    @FXML
    private Text alarm150Hours;
    @FXML
    private Text alarmOilChange;
    @Setter
    private ListAllEnginesTabController listAllEnginesTabController;
    @Setter
    private PersonalAircraftDialogController personalAircraftDialogController;
    private Engine engine;

    @FXML
    void initialize() {
        listOfWorksEngine.getItems().addAll(TypesOfWorks.WORKS_AFTER_10_HOURS,
                TypesOfWorks.WORKS_AFTER_25_HOURS,
                TypesOfWorks.WORKS_AFTER_50_HOURS,
                TypesOfWorks.WORKS_AFTER_100_HOURS,
                TypesOfWorks.WORKS_AFTER_150_HOURS,
                TypesOfWorks.WORKS_AFTER_278_BULLETIN,
                TypesOfWorks.OIL_CHANGE_OPERATIONS);
        makeWorksEngine.setOnAction(e -> {
            doWorksEngine(engine, listOfWorksEngine);
            update_Engine_After_Work();
            personalAircraftDialogController.updateNotificationEngine(engine);
            WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            Stage stage = (Stage) makeWorksEngine.getScene().getWindow();
            stage.close();
        });
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
            updateAircraftEngines();
            changeEngine(engine);
            Stage stage = (Stage) changeEngine.getScene().getWindow();
            stage.close();
        });
    }
    /** Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем */
    public void setButtonVisible(String string) {
        if (string.equals("Добавить двигатель")) {
            createEngine.setVisible(true);
            createEngineForAircraft.setVisible(false);
        } else if (string.equals("Изменить запись")) {
            changeEngine.setVisible(true);
            createEngineForAircraft.setVisible(false);
        } else if (string.equals("Двойное нажатие")) {
            createEngineForAircraft.setVisible(false);
        } else if (string.equals("Выполнить работ")) {
            createEngineForAircraft.setVisible(false);
            selectionWorks.setVisible(true);
            listOfWorksEngine.setVisible(true);
            makeWorksEngine.setVisible(true);
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
        List<Text> textOfAlarm = new ArrayList<>();
        textOfAlarm.add(alarm10Hours);
        textOfAlarm.add(alarm25Hours);
        textOfAlarm.add(alarm50Hours);
        textOfAlarm.add(alarm100Hours);
        textOfAlarm.add(alarm150Hours);
        textOfAlarm.add(alarm278Bulletin);
        textOfAlarm.add(alarmOilChange);
        notificationEngine(engine, textOfAlarm);
    }

    @FXML
    private void addEngine() {
        TextField[] fields = new TextField[]{totalOperatingEngineHours, totalOperatingEngineMinutes,
                totalStartingEngine, before_10hoursEngineHours,
                before_10hoursEngineMinutes, before_25hoursEngineHours,
                before_25hoursEngineMinutes, before_50hoursEngineHours,
                before_50hoursEngineMinutes, before_100hoursEngineHours,
                before_100hoursEngineMinutes, before_150hoursEngineHours,
                before_150hoursEngineMinutes, before_278bulletinEngineHours,
                before_278bulletinEngineMinutes, oilChangeEngineHours,
                oilChangeEngineMinutes, numberEngine};
        createEngine(fields);
    }

    public void changeEngine(Engine engine) {
        if (checkInput(before_10hoursEngineMinutes, before_25hoursEngineHours,
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
            engine.setSerialNumberEngine(numberEngine.getText());
        }
        engine.setIsNeedAttention(setBooleanValueEngine(engine));
        WriteFile.serialization(SaveData.enginesList, Engine.class);
        listAllEnginesTabController.updateTableEngines();
    }
    /** Метод синхронизирует двигатели, из списка агрегатов и двигатели, установленные на самолете
     * если мы вносим изменения в двигатель в списке агрегатов,который установлен на какой либо самолет,
     * то изменения будут автоматически внесены в этот двигатель на самолете */
    private void updateAircraftEngines() {
        for (Aircraft aircraft : SaveData.aircraftList) {
            if (aircraft.getLeftEngine() == null) {
                System.out.println("Нет левого двигателя на самолете");
            } else if (aircraft.getLeftEngine().getSerialNumberEngine().equals(engine.getSerialNumberEngine())) {
                changeEngine(aircraft.getLeftEngine());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
            if (aircraft.getRightEngine() == null) {
                System.out.println("Нет правого двигателя на самолете");
            } else if (aircraft.getRightEngine().getSerialNumberEngine().equals(engine.getSerialNumberEngine())) {
                changeEngine(aircraft.getRightEngine());
                WriteFile.serialization(SaveData.aircraftList, Aircraft.class);
            }
        }
    }
    /** Метод синхронизирует двигатели, из списка агрегатов и двигатели, установленные на самолете
     * если мы выполняем работы в двигателе на самолете,
     * то изменения будут автоматически внесеныв этот двигатель в списке агрегатов */
    private void update_Engine_After_Work() {
        for (Engine e : SaveData.enginesList) {
            if (e.getSerialNumberEngine().equals(engine.getSerialNumberEngine())) {
                doWorksEngine(e, listOfWorksEngine);
                WriteFile.serialization(SaveData.enginesList, Engine.class);
            }
        }
    }
}

