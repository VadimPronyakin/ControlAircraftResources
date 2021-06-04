package service;

import data.SaveData;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.CylinderOfRetractionExtension;
import data.components.limitedResource.FrontBreak;
import data.components.limitedResource.MainBreak;
import data.enums.TypesOfWorks;
import io.writer.FilesWriter;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.util.Date;

import static service.BooleanValueSetter.*;

@Slf4j
public class AircraftWorks {

    /**
     * Метод пересчитывает остаток ресурсов двигателя после выполнения выбранных работ
     */
    public static void doWorksEngine(Engine engine, ComboBox<TypesOfWorks> listOfWorks) {

        switch (listOfWorks.getSelectionModel().getSelectedItem()) {
            case WORKS_AFTER_10_HOURS:
                engine.setResourceReserveBefore_10hours(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                log.info("Пользователь выполнил 10-ти часовые работы на двигателе");
                break;
            case WORKS_AFTER_25_HOURS:
                engine.setResourceReserveBefore_25hours(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                log.info("Пользователь выполнил 25-ти часовые работы на двигателе");
                break;
            case WORKS_AFTER_50_HOURS:
                engine.setResourceReserveBefore_50hours(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
                log.info("Пользователь выполнил 50-ти часовые работы на двигателе");
                break;
            case WORKS_AFTER_100_HOURS:
                engine.setResourceReserveBefore_100hours(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_50hours(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
                log.info("Пользователь выполнил 100-та часовые работы на двигателе");
                break;
            case WORKS_AFTER_150_HOURS:
                engine.setResourceReserveBefore_150hours(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_50hours(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                engine.setResourceReserveBefore_25hours(TypesOfWorks.WORKS_AFTER_25_HOURS.getResource());
                engine.setResourceReserveBefore_10hours(TypesOfWorks.WORKS_AFTER_10_HOURS.getResource());
                log.info("Пользователь выполнил 150-ти часовые работы на двигателе");
                break;
            case WORKS_AFTER_278_BULLETIN:
                engine.setResourceReserveBefore_278bulletin(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                log.info("Пользователь выполнил работы по 278-му бюллетеню на двигателе");
                break;
            case OIL_CHANGE_OPERATIONS:
                engine.setOilChange(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                log.info("Пользователь выполнил замену масла на двигателе");
                break;
        }
        engine.setIsNeedAttention(setBooleanValueEngine(engine));
    }

    /**
     * Метод пересчитывает остаток ресурсов КСА после выполнения выбранных работ
     */
    public static void doWorkKsa(Ksa ksa, ComboBox<TypesOfWorks> listOfWorks) {
        try {
            switch (listOfWorks.getSelectionModel().getSelectedItem()) {

                case WORKS_AFTER_25_HOURS:
                    ksa.setResource_Reserve_Before_25hours(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                    log.info("Пользователь выполнил 25-ти часовые работы на КСА");
                    break;
                case OIL_CHANGE_OPERATIONS:
                    ksa.setOilChange(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                    log.info("Пользователь выполнил замену масла на КСА");
                    break;
            }
            ksa.setIsNeedAttention(setBooleanValueKsa(ksa));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод пересчитывает остаток ресурсов основного тормоза после выполнения выбранных работ
     */
    public static void doWorksMainBreak(MainBreak mainBreak, ComboBox<TypesOfWorks> listOfWorks) {
        try {
            switch (listOfWorks.getSelectionModel().getSelectedItem()) {

                case REPLACEMENT_ROTATING_DISKS:
                    mainBreak.setResource_Reserve_Replacement_RotatingDisks(listOfWorks
                            .getSelectionModel()
                            .getSelectedItem()
                            .getResource());
                    log.info("Пользователь выполнил работы по замене вращающихся дисков");
                    break;
                case REPLACEMENT_NON_ROTATING_DISKS:
                    mainBreak.setResource_Reserve_Replacement_NonRotatingDisks(listOfWorks
                            .getSelectionModel()
                            .getSelectedItem()
                            .getResource());
                    log.info("Пользователь выполнил работы по замене невращающихся дисков");
                    break;
                case REPLACEMENT_PRESSURE_DISKS:
                    mainBreak.setResource_Reserve_Replacement_PressureDisk(listOfWorks
                            .getSelectionModel()
                            .getSelectedItem()
                            .getResource());
                    log.info("Пользователь выполнил работы по замене нажимного диска");
                    break;
                case REPLACEMENT_REFERENCE_DISKS:
                    mainBreak.setResource_Reserve_Replacement_ReferenceDisk(listOfWorks.getSelectionModel()
                            .getSelectedItem()
                            .getResource());
                    log.info("Пользователь выполнил работы по замене опорного диска");
                    break;
            }
            mainBreak.setIsNeedAttention(setBooleanValueMainBreak(mainBreak));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод пересчитывает остаток ресурсов планера после выполнения выбранных работ
     */
    public static void doWorksPlaner(Planer planer, ChoiceBox<TypesOfWorks> listOfWorks, DatePicker date) {

        switch (listOfWorks.getSelectionModel().getSelectedItem()) {
            case WORKS_AFTER_100_HOURS_PLANER:
                planer.setResource_Reserve_Before_100hours(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                log.info("Пользователь выполнил 100-та часовые работы на планере");
                break;
            case WORKS_AFTER_200_HOURS_PLANER:
                planer.setResource_Reserve_Before_200hours(listOfWorks.getSelectionModel().getSelectedItem().getResource());
                planer.setResource_Reserve_Before_100hours(TypesOfWorks.WORKS_AFTER_100_HOURS_PLANER.getResource());
                log.info("Пользователь выполнил 200-та часовые работы на планере");
                break;
            case WORKS_AFTER_30_DAYS_PARKING:
                planer.setDate_Work_After_30days_Parking(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                planer.setDays_Reserve_Before_30DaysParking(DateResourcesCalculator.calculateDateInDays(planer.getLast_Flight_Date(),
                        Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                log.info("Пользователь выполнил на планеру работы ч/з 30+6 дней стоянки");
                break;
            case WORKS_AFTER_6_MONTH_OPERATION:
                planer.setDate_Work_After_6months_Operation(
                        Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                planer.setDays_Reserve_Before_6months_Operating(DateResourcesCalculator.calculateDateInMonth(Date.from(
                        date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                log.info("Пользователь выполнил на планеру работы ч/з 6+1 месяцев эксплуатации");
                break;
        }
        planer.setIsNeedAttention(BooleanValueSetter.setBooleanValuePlaner(planer));
    }

    /**
     * Метод пересчитывает остаток ресурсов цилиндра подкоса после отправки на завод изготовитель и выполнения ремонта
     */
    public static void doWorksCylinder(CylinderOfRetractionExtension cylinder, ComboBox<TypesOfWorks> listOfWorks) {
        try {
            switch (listOfWorks.getSelectionModel().getSelectedItem()) {

                case FIRST_REPAIR_CYLINDER:
                    cylinder.setResource_Reserve_Before_First_Repair(0);
                    cylinder.setResource_Reserve_Before_Second_Repair(800);
                    FilesWriter.serialization(SaveData.cylindersList, CylinderOfRetractionExtension.class);
                    log.info("Пользователь выполнил первый ремонт цилиндра подкоса");
                    break;
                case SECOND_REPAIR_CYLINDER:
                    cylinder.setResource_Reserve_Before_Second_Repair(0);
                    FilesWriter.serialization(SaveData.ksaList, Ksa.class);
                    log.info("Пользователь выполнил второй ремонт цилиндра подкоса");
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод пересчитывает остаток ресурсов переднего тормоза после отправки на завод изготовитель и выполнения ремонта
     */
    public static void doWorksFrontBreak(FrontBreak frontBreak, ComboBox<TypesOfWorks> listOfWorks) {
        try {
            frontBreak.setResource_Reserve_Before_First_Repair(listOfWorks.getSelectionModel().getSelectedItem().getResource());
            FilesWriter.serialization(SaveData.frontBreaksList, FrontBreak.class);
            log.info("Пользователь выполнил  ремонт переднего колеса");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
