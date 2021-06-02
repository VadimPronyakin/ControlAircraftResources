package sample.data.components.limitedResource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainBreak {
    //Общее кол-во посадок с начала эксплуатации
    private int totalLandings;
    //Остаток посадок  до назначенного ресурса (750пос)
    private int resource_Reserve_Replacement_Break;
    //Остаток посадок до назначенного ресурса вращающихся дисков (175пос)
    private int resource_Reserve_Replacement_RotatingDisks;
    //Остаток посадок до назначенного ресурса невращающихся дисков (175пос)
    private int resource_Reserve_Replacement_NonRotatingDisks;
    //Остаток посадок до назначенного ресурса нажимного диска (500пос)
    private int resource_Reserve_Replacement_PressureDisk;
    //Остаток посадок до назначенного ресурса опорного диска (500пос)
    private int resource_Reserve_Replacement_ReferenceDisk;
    //Сериынй номер основного тормоза
    private String serialNumber;
    //Номер самолета, на который установлен тормоз
    private String aircraftNumberInstalled;
    //Индикатор критического остатка
    private Boolean isNeedAttention;

    @Override
    public String toString() { return "сер/№ " + serialNumber; }
}