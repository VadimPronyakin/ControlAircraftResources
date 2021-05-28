package sample.data;

import lombok.*;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    private String fullNameEngineer;
}

