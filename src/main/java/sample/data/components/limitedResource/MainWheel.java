package sample.data.components.limitedResource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainWheel {
    //Общее кол-во посадок с начала эксплуатации
    private int totalLandings;
    //Остаток посадок  до назначенного ресурса (750пос)
    private int resource_Reserve_Replacement_Wheel;
    //Серийный номер основного колеса
    private String serialNumber;
    //Номер самолета,на котором установлено колесо
    private String aircraftNumberInstalled;
    //Индикатор критического остатка
    private Boolean isNeedAttention;

    @Override
    public String toString() { return "сер/№ " + serialNumber; }
}
