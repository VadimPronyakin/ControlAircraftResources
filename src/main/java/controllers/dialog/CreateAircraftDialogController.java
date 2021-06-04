package controllers.dialog;

import constants.TextConstants;
import controllers.ListOfAircraftController;
import data.Aircraft;
import data.Engineer;
import data.SaveData;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.*;
import io.writer.FilesWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

import static builder.Builder.completionAircraft;
import static service.BooleanValueSetter.setBooleanValueAircraft;
import static service.DuplicateProtection.aircraftProtection;
import static service.DuplicateProtection.changesEngineerProtection;
import static utils.Utils.*;

@Slf4j
public class CreateAircraftDialogController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField sideNumber;
    @FXML
    private TextField orderNumber;
    @FXML
    private ComboBox<Engineer> listOfEngineers;
    @FXML
    private ComboBox<Engine> leftEngineList;
    @FXML
    private ComboBox<Engine> rightEngineList;
    @FXML
    private ComboBox<MainWheel> mainLeftWheelList;
    @FXML
    private ComboBox<CylinderOfRetractionExtension> mainLeftCylinderList;
    @FXML
    private ComboBox<FrontWheel> frontLeftWheelList;
    @FXML
    private ComboBox<MainBreak> mainRightBreakList;
    @FXML
    private ComboBox<FrontBreak> frontLeftBreakList;
    @FXML
    private ComboBox<Ksa> ksaList;
    @FXML
    private Button addMainLeftWheelList;
    @FXML
    private Button addFrontLeftWheelList;
    @FXML
    private Button addMainRightBreakList;
    @FXML
    private Button addFrontLeftBreakList;
    @FXML
    private Button addMainLeftCylinderList;
    @FXML
    private ComboBox<MainWheel> mainRightWheelList;
    @FXML
    private ComboBox<CylinderOfRetractionExtension> mainRightCylinderList;
    @FXML
    private ComboBox<FrontWheel> frontRightWheelList;
    @FXML
    private ComboBox<MainBreak> mainLeftBreakList;
    @FXML
    private ComboBox<FrontBreak> frontRightBreakList;
    @FXML
    private Button addMainRightWheelList;
    @FXML
    private Button addFrontRightWheelList;
    @FXML
    private Button addMainLeftBreakList;
    @FXML
    private Button addFrontRightBreakList;
    @FXML
    private Button addMainRightCylinderList;
    @FXML
    private Button createAircraft;
    @FXML
    private ComboBox<CylinderOfRetractionExtension> frontCylinderList;
    @FXML
    private Button addFrontCylinderList;
    @FXML
    private Button addKsa;
    @FXML
    private Button addRightEngine;
    @FXML
    private Button addLeftEngine;
    @FXML
    private ComboBox<Planer> listOfPlaners;
    @FXML
    private Button createPlaner;
    @FXML
    private Button updateMainLeftCylinderList;
    @FXML
    private Button updateFrontLeftBreakList;
    @FXML
    private Button updateMainRightBreakList;
    @FXML
    private Button updateFrontLeftWheelList;
    @FXML
    private Button updateFrontCylinderList;
    @FXML
    private Button updateMainRightCylinderList;
    @FXML
    private Button updateFrontRightBreakList;
    @FXML
    private Button updateMainLeftBreakList;
    @FXML
    private Button updateFrontRightWheelList;
    @FXML
    private Button updateMainRightWheelList;
    @FXML
    private Button updateKsaList;
    @FXML
    private Button updateRightEngineList;
    @FXML
    private Button updateLeftEngineList;
    @FXML
    private Button updateListOfPlaners;
    @FXML
    private Button updateMainLeftWheelList;
    @FXML
    private Button changeAircraft;
    @FXML
    private DatePicker orderDate;

    @Setter
    private ListOfAircraftController listOfAircraftController;

    private Aircraft aircraft;

    public static void completionBoxes(Aircraft aircraft) {
    }

    @FXML
    void initialize() {
        handleUpdateButtons();
        completionChoiceBox();
        handleCreateButton();
        createAircraft.setOnAction(e -> {
            try {
                if (checkingOnDuplications(returnFullArray())
                        && aircraftProtection(sideNumber)) {
                    addAircraft();
                    Stage stage = (Stage) createAircraft.getScene().getWindow();
                    stage.close();
                }
            } catch (NullPointerException exception) {
                openInformationWindow(TextConstants.THE_FIELDS_ARE_REQUIRED);
                log.error("Оставил какое либо поле пустым, при создании самолета {}", exception.getMessage());
            }
        });
        changeAircraft.setOnAction(e -> {
            try {
                if (changesEngineerProtection(aircraft, listOfEngineers, orderNumber, orderDate)
                && checkingOnDuplications(returnFullArray())) {
                    changeAircraft(aircraft);
                    Stage stage = (Stage) changeAircraft.getScene().getWindow();
                    stage.close();
                }
            } catch (Exception exception) {
                openInformationWindow(TextConstants.THE_FIELDS_ARE_REQUIRED);
                log.error("Оставил какое либо поле пустым, при редактировании самолета {}", exception.getMessage());
            }
        });
    }

    /**
     * В методе заданы действия для кнопок добавления новых агрегатов непосредственно при создании самолета
     */
    private void handleCreateButton() {
        addKsa.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createKsaDialog.fxml");
            log.info("Нажал кнопку добавления КСА при создании самолета");
        });
        addLeftEngine.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createEngineDialog.fxml");
            log.info("Нажал кнопку добавления левого двигателя при создании самолета");
        });
        addRightEngine.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createEngineDialog.fxml");
            log.info("Нажал кнопку добавления правого двигателя при создании самолета");
        });
        addFrontCylinderList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createCylinderDialog.fxml");
            log.info("Нажал кнопку добавления переднего  цилиндра подкоса при создании самолета");
        });
        addMainLeftCylinderList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createCylinderDialog.fxml");
            log.info("Нажал кнопку добавления левого цилиндра подкоса при создании самолета");
        });
        addMainRightCylinderList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createCylinderDialog.fxml");
            log.info("Нажал кнопку добавления правого цилиндра подкоса при создании самолета");
        });
        addFrontLeftBreakList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createFrontBreakDialog.fxml");
            log.info("Нажал кнопку добавления левого переднего тормоза при создании самолета");
        });
        addFrontRightBreakList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createFrontBreakDialog.fxml");
            log.info("Нажал кнопку добавления правого переднего тормоза при создании самолета");
        });
        addMainLeftBreakList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createMainBreakDialog.fxml");
            log.info("Нажал кнопку добавления левого основного тормоза при создании самолета");
        });
        addMainRightBreakList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createMainBreakDialog.fxml");
            log.info("Нажал кнопку добавления правого основного тормоза при создании самолета");
        });
        addFrontLeftWheelList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createFrontWheelDialog.fxml");
            log.info("Нажал кнопку добавления левого переднего колеса при создании самолета");
        });
        addFrontRightWheelList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createFrontWheelDialog.fxml");
            log.info("Нажал кнопку добавления правого переднего колеса при создании самолета");
        });
        addMainLeftWheelList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createMainWheelDialog.fxml");
            log.info("Нажал кнопку добавления левого основного колеса при создании самолета");
        });
        addMainRightWheelList.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createMainWheelDialog.fxml");
            log.info("Нажал кнопку добавления правого основного колеса при создании самолета");
        });
        createPlaner.setOnAction(e -> {
            showDialogWindow("/fxmlFiles/dialog/createPlanerDialog.fxml");
            log.info("Нажал кнопку добавления планера при создании самолета");
        });
    }

    /**
     * Метод заполняет все ChoiceBox на странице создания самолета
     */
    private void completionChoiceBox() {
        listOfEngineers.getItems().addAll(SaveData.engineersList);
        leftEngineList.getItems().addAll(filterInstalledEngine());
        rightEngineList.getItems().addAll(filterInstalledEngine());
        mainLeftWheelList.getItems().addAll(filterInstalledMainWheel());
        mainLeftCylinderList.getItems().addAll(filterInstalledCylinder());
        frontLeftWheelList.getItems().addAll(filterInstalledFrontWheel());
        mainRightBreakList.getItems().addAll(filterInstalledMainBreak());
        frontLeftBreakList.getItems().addAll(filterInstalledFrontBreak());
        ksaList.getItems().addAll(filterInstalledKsa());
        mainRightWheelList.getItems().addAll(filterInstalledMainWheel());
        mainRightCylinderList.getItems().addAll(filterInstalledCylinder());
        frontRightWheelList.getItems().addAll(filterInstalledFrontWheel());
        mainLeftBreakList.getItems().addAll(filterInstalledMainBreak());
        frontRightBreakList.getItems().addAll(filterInstalledFrontBreak());
        frontCylinderList.getItems().addAll(filterInstalledCylinder());
        listOfPlaners.getItems().addAll(filterInstalledPlaner());
    }

    /**
     * Метод возвращает заполненный массив из ChoiceBox
     */
    private ComboBox[] returnFullArray() {
        return new ComboBox[]{listOfEngineers, leftEngineList,
                rightEngineList, ksaList,
                listOfPlaners, mainLeftBreakList,
                mainRightBreakList, mainLeftWheelList,
                mainRightWheelList, frontLeftBreakList,
                frontRightBreakList, frontLeftWheelList,
                frontRightWheelList, mainLeftCylinderList,
                mainRightCylinderList, frontCylinderList};
    }

    /**
     * В методе заданы действия для всех кнопок обновления ChoiceBox на старнице создания самолета
     */
    private void handleUpdateButtons() {
        updateLeftEngineList.setOnAction(e -> {
            updateComboBoxes(1);
            log.info("Нажал кнопку обновления ComboBox - левый двигатель при создании или редактировании самолета");
        });
        updateRightEngineList.setOnAction(e -> {
            updateComboBoxes(2);
            log.info("Нажал кнопку обновления ComboBox - правый двигатель при создании или редактировании самолета");
        });
        updateKsaList.setOnAction(e -> {
            updateComboBoxes(3);
            log.info("Нажал кнопку обновления ComboBox - КСА при создании или редактировании самолета");
        });
        updateMainLeftWheelList.setOnAction(e -> {
            updateComboBoxes(4);
            log.info("Нажал кнопку обновления ComboBox - левое основное колесо при создании или редактировании самолета");
        });
        updateMainRightWheelList.setOnAction(e -> {
            updateComboBoxes(5);
            log.info("Нажал кнопку обновления ComboBox - правое основное колесо при создании или редактировании самолета");
        });
        updateFrontLeftWheelList.setOnAction(e -> {
            updateComboBoxes(6);
            log.info("Нажал кнопку обновления ComboBox - левое переднее колесо при создании или редактировании самолета");
        });
        updateFrontRightWheelList.setOnAction(e -> {
            updateComboBoxes(7);
            log.info("Нажал кнопку обновления ComboBox - правое переднее колесо при создании или редактировании самолета");
        });
        updateMainLeftBreakList.setOnAction(e -> {
            updateComboBoxes(8);
            log.info("Нажал кнопку обновления ComboBox - левый основной тормоз при создании или редактировании самолета");
        });
        updateMainRightBreakList.setOnAction(e -> {
            updateComboBoxes(9);
            log.info("Нажал кнопку обновления ComboBox - правый основной тормоз при создании или редактировании самолета");
        });
        updateFrontLeftBreakList.setOnAction(e -> {
            updateComboBoxes(10);
            log.info("Нажал кнопку обновления ComboBox - левый передний тормоз при создании или редактировании самолета");
        });
        updateFrontRightBreakList.setOnAction(e -> {
            updateComboBoxes(11);
            log.info("Нажал кнопку обновления ComboBox - правый передний тормоз при создании или редактировании самолета");
        });
        updateMainLeftCylinderList.setOnAction(e -> {
            updateComboBoxes(12);
            log.info("Нажал кнопку обновления ComboBox - левый цилиндр подкоса при создании или редактировании самолета");
        });
        updateMainRightCylinderList.setOnAction(e -> {
            updateComboBoxes(13);
            log.info("Нажал кнопку обновления ComboBox - правый цилиндр подкоса при создании или редактировании самолета");
        });
        updateFrontCylinderList.setOnAction(e -> {
            updateComboBoxes(14);
            log.info("Нажал кнопку обновления ComboBox - передний цилиндр подкоса при создании или редактировании самолета");
        });
        updateListOfPlaners.setOnAction(e -> {
            updateComboBoxes(15);
            log.info("Нажал кнопку обновления ComboBox - планер при создании или редактировании самолета");
        });
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        sideNumber.setText(aircraft.getAircraftNumber());
        leftEngineList.setValue(aircraft.getLeftEngine());
        rightEngineList.setValue(aircraft.getRightEngine());
        ksaList.setValue(aircraft.getKsa());
        listOfPlaners.setValue(aircraft.getPlaner());
        mainLeftBreakList.setValue(aircraft.getLeftMainBrake());
        mainRightBreakList.setValue(aircraft.getRightMainBrake());
        mainLeftWheelList.setValue(aircraft.getLeftMainWheel());
        mainRightWheelList.setValue(aircraft.getRightMainWheel());
        frontLeftBreakList.setValue(aircraft.getLeftFrontBrake());
        frontRightBreakList.setValue(aircraft.getRightFrontBrake());
        frontLeftWheelList.setValue(aircraft.getLeftFrontWheel());
        frontRightWheelList.setValue(aircraft.getRightFrontWheel());
        mainLeftCylinderList.setValue(aircraft.getLeftMainCylinder());
        mainRightCylinderList.setValue(aircraft.getRightMainCylinder());
        frontCylinderList.setValue(aircraft.getFrontCylinder());
        listOfEngineers.setValue(aircraft.getIak());
        orderNumber.textProperty().setValue(String.valueOf(aircraft.getOrderNumber()));
        orderDate.setValue(aircraft.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    /**
     * Метод создает новый самолет
     */
    private void addAircraft() {
        Aircraft aircraft = new Aircraft();
        completionAircraft(aircraft, sideNumber, orderNumber, orderDate, returnFullArray());
        aircraft.setIsNeedAttention(setBooleanValueAircraft(aircraft));
        aircraft.setFullNameEngineer(aircraft.getIak().getFullName());
        SaveData.aircraftList.add(aircraft);
        FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
        listOfAircraftController.updateTableAircraft();
        log.info("Создал новый самолет");
    }

    /**
     * Метод вносит изменения в созданные самолеты
     */
    public void changeAircraft(Aircraft aircraft) {
        completionAircraft(aircraft, sideNumber, orderNumber, orderDate, returnFullArray());
        aircraft.setAircraftNumber(sideNumber.getText());
        aircraft.setFullNameEngineer(aircraft.getIak().getFullName());
        aircraft.setIsNeedAttention(setBooleanValueAircraft(aircraft));
        FilesWriter.serialization(SaveData.aircraftList, Aircraft.class);
        listOfAircraftController.updateTableAircraft();
        log.info("Внес изменения в самолет");
    }

    /**
     * Метод обновляет содержимое ChoiceBox
     */
    public void updateComboBoxes(int id) {
        ComboBox box = null;
        List list = null;
        switch (id) {
            case 1:
                box = leftEngineList;
                list = filterInstalledEngine();
                break;
            case 2:
                box = rightEngineList;
                list = filterInstalledEngine();
                break;
            case 3:
                box = ksaList;
                list = filterInstalledKsa();
                break;
            case 4:
                box = mainLeftWheelList;
                list = filterInstalledMainWheel();
                break;
            case 5:
                box = mainRightWheelList;
                list = filterInstalledMainWheel();
                break;
            case 6:
                box = frontLeftWheelList;
                list = filterInstalledFrontWheel();
                break;
            case 7:
                box = frontRightWheelList;
                list = filterInstalledFrontWheel();
                break;
            case 8:
                box = mainLeftBreakList;
                list = filterInstalledMainBreak();
                break;
            case 9:
                box = mainRightBreakList;
                list = filterInstalledMainBreak();
                break;
            case 10:
                box = frontLeftBreakList;
                list = filterInstalledFrontBreak();
                break;
            case 11:
                box = frontRightBreakList;
                list = filterInstalledFrontBreak();
                break;
            case 12:
                box = mainLeftCylinderList;
                list = filterInstalledCylinder();
                break;
            case 13:
                box = mainRightCylinderList;
                list = filterInstalledCylinder();
                break;
            case 14:
                box = frontCylinderList;
                list = filterInstalledCylinder();
                break;
            case 15:
                box = listOfPlaners;
                list = filterInstalledPlaner();
                break;
        }
        box.getItems().clear();
        box.setValue(null);
        for (int i = 0; i < list.size(); i++) {
            box.getItems().addAll(list.get(i));
        }
    }

    /**
     * Делает видимыми нужные кнопки в диалоговом окне,в зависимости от того,для каких целей мы его открываем
     */
    public void setButtonVisible(boolean isCreate, boolean isChange) {
        createAircraft.setVisible(isCreate);
        changeAircraft.setVisible(isChange);
    }
}

