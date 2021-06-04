package data;

import lombok.*;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Aircraft {
    //Бортовой номер самолета
    @NonNull
    private String aircraftNumber;
    //ИАК закрепленный за самолетом
    @NonNull
    private Engineer iak;
    //Планер от самолета не открепляется
    @NonNull
    private Planer planer;
    //Левый двигатель
    private Engine leftEngine;
    //Правый двигатель
    private Engine rightEngine;
    //КСА
    private Ksa ksa;
    //Тормоз левого основного колеса
   @NonNull
    private MainBreak leftMainBrake;
    //Тормоз правого основного колеса
   @NonNull
    private MainBreak rightMainBrake;
    //Тормоз левого переднего колеса
    @NonNull
    private FrontBreak leftFrontBrake;
    //Тормоз правого переднего колеса
    @NonNull
    private FrontBreak rightFrontBrake;
    //Левое основное колесо
    @NonNull
    private MainWheel leftMainWheel;
    //Правое основное колесо
    @NonNull
    private MainWheel rightMainWheel;
    //Левое переднее колесо
    @NonNull
    private FrontWheel leftFrontWheel;
    //Правое переднее колесо
    @NonNull
    private FrontWheel rightFrontWheel;
    //Цилиндр подкоса левой основной стойки шасси
    @NonNull
    private CylinderOfRetractionExtension leftMainCylinder;
    //Цилиндр подкоса правой основной стойки шасси
    @NonNull
   private CylinderOfRetractionExtension rightMainCylinder;
    //Цилиндр подкоса передней стойки шасси
    @NonNull
    private CylinderOfRetractionExtension frontCylinder;
    //ФИО и звание инженера Ак закрепленного за самолетом
    @NonNull
    private String fullNameEngineer;
    //Индикатор критического остатка
    private Boolean isNeedAttention;
    //Номер приказа, которым самолет закрепляют за инженером(ак)
    @NonNull
    private int orderNumber;
    //Дата, когда вышел приказ о закреплении самолета за инженером(ак)
    @NonNull
    private Date orderDate;
    //Список хранит в себе 5 последних записей по наработке в самолете
    private List<OperatingHistory> operationHistory = new ArrayList<>();
}

