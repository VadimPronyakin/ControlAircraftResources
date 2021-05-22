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
    private Planer planer;
    //Левый двигатель
    private Engine leftEngine;
    //Правый двигатель
    private Engine rightEngine;
    //КСА
    private Ksa ksa;
    //Тормоз левого основного колеса
    private MainBreak leftMainBrake;
    //Тормоз правого основного колеса
    private MainBreak rightMainBrake;
    //Тормоз левого переднего колеса
    private FrontBreak leftFrontBrake;
    //Тормоз правого переднего колеса
    private FrontBreak rightFrontBrake;
    //Левое основное колесо
    private MainWheel leftMainWheel;
    //Правое основное колесо
    private MainWheel rightMainWheel;
    //Левое переднее колесо
    private FrontWheel leftFrontWheel;
    //Правое переднее колесо
    private FrontWheel rightFrontWheel;
    //Цилиндр подкоса левой основной стойки шасси
    private CylinderOfRetractionExtension leftMainCylinder;
    //Цилиндр подкоса правой основной стойки шасси
    private CylinderOfRetractionExtension rightMainCylinder;
    //Цилиндр подкоса передней стойки шасси
    private CylinderOfRetractionExtension frontCylinder;
    //ФИО и звание инженера Ак закрепленного за самолетом
    private String fullNameEngineer;
}

