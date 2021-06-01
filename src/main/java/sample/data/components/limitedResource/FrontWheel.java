package sample.data.components.limitedResource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrontWheel {
    //Общее кол-во посадок с начала эксплуатации
    private int totalLandings;
    //Остаток посадок  до назначенного ресурса (1280пос)
    private int resource_Reserve_Replacement_Wheel;
    //Серийный номер переднего колеса
    private String serialNumber;
    //Номер самолета,на котором установлено колесо
    private String aircraftNumberInstalled;
    private Boolean isNeedAttention;

    @Override
    public String toString() { return "сер/№ " +  serialNumber; }
}
