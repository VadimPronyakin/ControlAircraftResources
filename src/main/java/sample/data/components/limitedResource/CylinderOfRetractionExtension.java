package sample.data.components.limitedResource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CylinderOfRetractionExtension {
    //Общее кол-во посадок с начала эксплуатации
    private int totalLandings;
    //Кол-во посадок за летную смену
    //private int landings_For_Flights;
    //Остаток посадок до первого ремонта (1500пос)
    private int resource_Reserve_Before_First_Repair;
    //Остаток посадок до 2-го ремонта (800пос)
    private int resource_Reserve_Before_Second_Repair;
    //Остаток посадок  до назначенного ресурса (2500пос)
    private int resource_Reserve_Before_Replacement;
    //Серийный номер цилиндра подкоса
    private String serialNumber;
    //Номер самолета,на котором установлен цилиндр подкоса
    private int aircraftNumberInstalled;

    @Override
    public String toString() {
        return serialNumber;
    }
}
