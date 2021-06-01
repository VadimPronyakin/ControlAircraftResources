package sample.data.components.limitedResource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrontBreak {
    //Общее кол-во посадок с начала эксплуатации
    private int totalLandings;
    //Остаток посадок  до 1-го ремонта (600пос)
    private int resource_Reserve_Before_First_Repair;
    //Остаток посадок  до назначенного ресурса (1280пос)
    private int resource_Reserve_Before_Replacement;
    //Серийный номер переднего тормоза
    private String serialNumber;
    //Номер самолета,на котором установлен передний тормоз
    private String aircraftNumberInstalled;
    private Boolean isNeedAttention;

    @Override
    public String toString() {
        return "сер/№ " + serialNumber;
    }
}
