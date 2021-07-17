package utils;

import constants.TextConstants;
import controllers.dialog.PersonalAircraftDialogController;
import data.Aircraft;
import data.SaveData;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.*;
import io.writer.FilesWriter;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import main.Main;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Slf4j
public class Utils {
    /**
     * Метод проверяет на пустоту содержимое полей, в которе заполняются данные об агрегатах.
     */
    public static boolean checkInput(TextField... fields) {
        for (TextField field : fields) {
            if (StringUtils.isBlank(field.getCharacters())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод проверяет на пустоту содержимое полей, в которе заполняются данные о планере.
     */
    public static boolean checkInputPlaner(DatePicker lastFlightDate,
                                           DatePicker dateWorkAfter_6monthsOperation,
                                           DatePicker dateWorkAfter_30DaysParking,
                                           TextField... fields) {
        for (TextField field : fields) {
            if (StringUtils.isBlank(field.getCharacters())) {
                StringUtils.isBlank((lastFlightDate.getEditor().getCharacters()));
                StringUtils.isBlank((dateWorkAfter_6monthsOperation.getEditor().getCharacters()));
                StringUtils.isBlank((dateWorkAfter_30DaysParking.getEditor().getCharacters()));
                return false;
            }
        }
        return true;
    }

    /**
     * Метод удаляет объект из списка
     */
    public static <T> void delete(List<T> list, TableView<T> tableView, Class<T> clazz) {
        list.remove(tableView.getSelectionModel().getSelectedItem());
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
        tableView.refresh();
        FilesWriter.serialization(list, clazz);
    }

    /**
     * При удалении самолета, метод удаляет планер, который установлен на это самолете
     */
    public static void deletePlaner(Aircraft aircraft) {
        Planer deletePlaner = null;
        for (Planer planer : SaveData.planersList) {
            if (planer.getSerialNumber().equals(aircraft.getPlaner().getSerialNumber())) {
                deletePlaner = planer;
            }
        }
        SaveData.planersList.remove(deletePlaner);
        FilesWriter.serialization(SaveData.planersList, Planer.class);
    }

    /**
     * Метод создает всплывающее окно с предупреждением
     */
    public static void openInformationWindow(String information) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ВНИМАНИЕ!!!");
        alert.setHeaderText(null);
        alert.setContentText(information);
        alert.showAndWait();
    }

    /**
     * Метод добавляет изображение в столбце TableView.
     */
    public static Node createPriorityGraphic(Boolean isPriority) {
        HBox graphicContainer = new HBox();
        graphicContainer.setAlignment(Pos.CENTER);
        if (isPriority) {
            ImageView imageView = new ImageView(new Image(Main.class.getResourceAsStream("/images/1.png")));
            imageView.setFitHeight(25);
            imageView.setPreserveRatio(true);
            graphicContainer.getChildren().add(imageView);
        }
        return graphicContainer;
    }

    /**
     * Метод перехода между окнами.
     */
    public static <T> T openNewScene(String window, Button button) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/images/Programlogo.png"));
        stage.show();
        return loader.getController();
    }

    /**
     * Метод открывает диалоговое окно
     */
    public static <T> T showDialogWindow(String window) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(window));
        try {
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image("/images/Programlogo.png"));
            dialogStage.show();
            log.info("Открыли диалоговое окно {}", window);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return loader.getController();
    }

    /**
     * Метод обновляет содержимое TableView
     */
    public static <T> void updateList(List<T> list, TableView<T> tableView, String textConstants) {
        if (list.size() == 0) {
            tableView.setPlaceholder(new Label(textConstants));
        }
        tableView.getItems().clear();
        tableView.getItems().addAll(list);
    }

    /**
     *
     */
    public static Map<Stage, PersonalAircraftDialogController> showEditDialogDoubleClickWithStage(String window) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(window));
        try {
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image("/images/Programlogo.png"));
            dialogStage.show();
            return new HashMap<>() {{
                put(dialogStage, loader.getController());
            }};
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Метод перебирает список двигателей, отсеивает те, которые уже установлены на какой-либо самолет и создает List -
     * заполненый двигателями, которые не установлены на самолеты.
     */
    public static List<Engine> filterInstalledEngine() {
        List<Engine> list = new ArrayList<>(SaveData.enginesList);
        for (Aircraft aircraft : SaveData.aircraftList) {
            for (Engine engine : SaveData.enginesList) {
                if (aircraft.getLeftEngine() != null
                        && aircraft.getLeftEngine().getSerialNumber().equals(engine.getSerialNumber())
                        || aircraft.getRightEngine() != null
                        && aircraft.getRightEngine().getSerialNumber().equals(engine.getSerialNumber())) {
                    list.remove(engine);
                }
            }
        }
        return list;
    }

    /**
     * Метод перебирает список КСА, отсеивает те, которые уже установлены на какой-либо самолет и создает List -
     * заполненый КСА, которые не установлены на самолеты.
     */
    public static List<Ksa> filterInstalledKsa() {
        List<Ksa> list = new ArrayList<>(SaveData.ksaList);
        for (Aircraft aircraft : SaveData.aircraftList) {
            for (Ksa ksa : SaveData.ksaList) {
                if (aircraft.getKsa() != null && aircraft.getKsa().getSerialNumber().equals(ksa.getSerialNumber())) {
                    list.remove(ksa);
                }
            }
        }
        return list;
    }

    /**
     * Метод перебирает список планеров, отсеивает те, которые уже установлены на какой-либо самолет и создает List -
     * заполненый планерами, которые не установлены на самолеты.
     */
    public static List<Planer> filterInstalledPlaner() {
        List<Planer> list = new ArrayList<>(SaveData.planersList);
        for (Aircraft aircraft : SaveData.aircraftList) {
            for (Planer planer : SaveData.planersList) {
                if (aircraft.getPlaner().getSerialNumber().equals(planer.getSerialNumber())) {
                    list.remove(planer);
                }
            }
        }
        return list;
    }

    /**
     * Метод перебирает список основных тормозов, отсеивает те, которые уже установлены на какой-либо самолет и создает List -
     * заполненый основными тормозами, которые не установлены на самолеты.
     */
    public static List<MainBreak> filterInstalledMainBreak() {
        List<MainBreak> list = new ArrayList<>(SaveData.mainBreaksList);
        for (Aircraft aircraft : SaveData.aircraftList) {
            for (MainBreak mainBreak : SaveData.mainBreaksList) {
                if (aircraft.getLeftMainBrake().getSerialNumber().equals(mainBreak.getSerialNumber())
                        || aircraft.getRightMainBrake().getSerialNumber().equals(mainBreak.getSerialNumber())) {
                    list.remove(mainBreak);
                }
            }
        }
        return list;
    }

    /**
     * Метод перебирает список основных колес, отсеивает те, которые уже установлены на какой-либо самолет и создает List -
     * заполненый основными колесами, которые не установлены на самолеты.
     */
    public static List<MainWheel> filterInstalledMainWheel() {
        List<MainWheel> list = new ArrayList<>(SaveData.mainWheelsList);
        for (Aircraft aircraft : SaveData.aircraftList) {
            for (MainWheel mainWheel : SaveData.mainWheelsList) {
                if (aircraft.getLeftMainWheel().getSerialNumber().equals(mainWheel.getSerialNumber())
                        || aircraft.getRightMainWheel().getSerialNumber().equals(mainWheel.getSerialNumber())) {
                    list.remove(mainWheel);
                }
            }
        }
        return list;
    }

    /**
     * Метод перебирает список передних тормозов, отсеивает те, которые уже установлены на какой-либо самолет и создает List -
     * заполненый передними тормозами, которые не установлены на самолеты.
     */
    public static List<FrontBreak> filterInstalledFrontBreak() {
        List<FrontBreak> list = new ArrayList<>(SaveData.frontBreaksList);
        for (Aircraft aircraft : SaveData.aircraftList) {
            for (FrontBreak frontBreak : SaveData.frontBreaksList) {
                if (aircraft.getLeftFrontBrake().getSerialNumber().equals(frontBreak.getSerialNumber())
                        || aircraft.getRightFrontBrake().getSerialNumber().equals(frontBreak.getSerialNumber())) {
                    list.remove(frontBreak);
                }
            }
        }
        return list;
    }

    /**
     * Метод перебирает список передних колес, отсеивает те, которые уже установлены на какой-либо самолет и создает List -
     * заполненый передними колесами, которые не установлены на самолеты.
     */
    public static List<FrontWheel> filterInstalledFrontWheel() {
        List<FrontWheel> list = new ArrayList<>(SaveData.frontWheelsList);
        for (Aircraft aircraft : SaveData.aircraftList) {
            for (FrontWheel frontWheel : SaveData.frontWheelsList) {
                if (aircraft.getLeftFrontWheel().getSerialNumber().equals(frontWheel.getSerialNumber())
                        || aircraft.getRightFrontBrake().getSerialNumber().equals(frontWheel.getSerialNumber())) {
                    list.remove(frontWheel);
                }
            }
        }
        return list;
    }

    /**
     * Метод перебирает список цилиндров подкоса, отсеивает те, которые уже установлены на какой-либо самолет и создает List -
     * заполненый цилиндрами подкоса, которые не установлены на самолеты.
     */
    public static List<CylinderOfRetractionExtension> filterInstalledCylinder() {
        List<CylinderOfRetractionExtension> list = new ArrayList<>(SaveData.cylindersList);
        for (Aircraft aircraft : SaveData.aircraftList) {
            for (CylinderOfRetractionExtension cylinder : SaveData.cylindersList) {
                if (aircraft.getLeftMainCylinder().getSerialNumber().equals(cylinder.getSerialNumber())
                        || aircraft.getRightMainCylinder().getSerialNumber().equals(cylinder.getSerialNumber())
                        || aircraft.getFrontCylinder().getSerialNumber().equals(cylinder.getSerialNumber())) {
                    list.remove(cylinder);
                }
            }
        }
        return list;
    }

    /**
     * Метод проверяет на свопадение агрегаты левой и правой сторон, при создании самолета.
     * Например если для левого двигателя и для правого двигателя, мы выбираем один и тот же агрегат,
     * появится всплывающее окно с предупреждением
     */
    public static boolean checkingOnDuplications(ComboBox[] aircraftAggregates) {
        boolean isDuplicated = true;
        if (aircraftAggregates[1].getSelectionModel().getSelectedItem() != null
                && aircraftAggregates[2].getSelectionModel().getSelectedItem() != null) {
            if (aircraftAggregates[1].getSelectionModel().getSelectedItem().equals(
                    aircraftAggregates[2].getSelectionModel().getSelectedItem())) {
                Utils.openInformationWindow(TextConstants.ENGINE_DUPLICATE);
                isDuplicated = false;
            }
        }
        if (aircraftAggregates[5].getSelectionModel().getSelectedItem().equals(
                aircraftAggregates[6].getSelectionModel().getSelectedItem())) {
            Utils.openInformationWindow(TextConstants.MAIN_BREAK_DUPLICATE);
            isDuplicated = false;
        }
        if (aircraftAggregates[7].getSelectionModel().getSelectedItem().equals(
                aircraftAggregates[8].getSelectionModel().getSelectedItem())) {
            Utils.openInformationWindow(TextConstants.MAIN_WHEEL_DUPLICATE);
            isDuplicated = false;
        }
        if (aircraftAggregates[9].getSelectionModel().getSelectedItem().equals(
                aircraftAggregates[10].getSelectionModel().getSelectedItem())) {
            Utils.openInformationWindow(TextConstants.FRONT_BREAK_DUPLICATE);
            isDuplicated = false;
        }
        if (aircraftAggregates[11].getSelectionModel().getSelectedItem().equals(
                aircraftAggregates[12].getSelectionModel().getSelectedItem())) {
            Utils.openInformationWindow(TextConstants.FRONT_WHEEL_DUPLICATE);
            isDuplicated = false;
        }
        if (aircraftAggregates[13].getSelectionModel().getSelectedItem().equals(
                aircraftAggregates[14].getSelectionModel().getSelectedItem())
                || aircraftAggregates[13].getSelectionModel().getSelectedItem().equals(
                aircraftAggregates[15].getSelectionModel().getSelectedItem())
                || aircraftAggregates[15].getSelectionModel().getSelectedItem().equals(
                aircraftAggregates[14].getSelectionModel().getSelectedItem())) {
            Utils.openInformationWindow(TextConstants.CYLINDER_DUPLICATE);
            isDuplicated = false;
        }

        return isDuplicated;
    }

    /**
     * Метод проверяет число, вводимое в поля при создании или редактировании двигателя,
     * принимающие в себя данные в минутах.
     * Если число превышает или равно кол-ву минут в 1 часе, появляется всплывающее окно с уведомлением
     */
    public static boolean engineMinutesChecker(TextField[] fields) {
        boolean isChecker = true;
        if (parseInt(fields[1].getText()) > 59
                || parseInt(fields[4].getText()) > 59
                || parseInt(fields[6].getText()) > 59
                || parseInt(fields[8].getText()) > 59
                || parseInt(fields[10].getText()) > 59
                || parseInt(fields[12].getText()) > 59
                || parseInt(fields[14].getText()) > 59
                || parseInt(fields[16].getText()) > 59
                || StringUtils.isBlank(fields[17].getCharacters())) {
            isChecker = false;
            Utils.openInformationWindow(TextConstants.MINUTES_SIZE);
        }
        return isChecker;
    }

    /**
     * Метод проверяет число, вводимое в поля при создании или редактировании КСА и планера,
     * принимающие в себя данные в минутах.
     * Если число превышает или равно кол-ву минут в 1 часе, появляется всплывающее окно с уведомлением
     */
    public static boolean minutesChecker(TextField[] fields) {
        boolean isChecker = true;
        if (parseInt(fields[3].getText()) > 59
                || parseInt(fields[5].getText()) > 59
                || parseInt(fields[7].getText()) > 59) {
            isChecker = false;
            Utils.openInformationWindow(TextConstants.MINUTES_SIZE);
        }
        return isChecker;
    }
}
