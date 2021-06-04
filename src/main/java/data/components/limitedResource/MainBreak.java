package data.components.limitedResource;

import data.AbstractAggregate;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MainBreak extends AbstractAggregate {
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

    @Override
    public String toString() { return "сер/№ " + super.getSerialNumber(); }
}