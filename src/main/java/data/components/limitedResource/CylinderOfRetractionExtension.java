package data.components.limitedResource;

import data.AbstractAggregate;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CylinderOfRetractionExtension extends AbstractAggregate {
    //Общее кол-во посадок с начала эксплуатации
    private int totalLandings;
    //Остаток посадок до первого ремонта (1500пос)
    private int resource_Reserve_Before_First_Repair;
    //Остаток посадок до 2-го ремонта (800пос)
    private int resource_Reserve_Before_Second_Repair;
    //Остаток посадок  до назначенного ресурса (2500пос)
    private int resource_Reserve_Before_Replacement;

    @Override
    public String toString() { return "сер/№ " + super.getSerialNumber(); }
}
