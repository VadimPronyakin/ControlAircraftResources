package data.components.limitedResource;

import data.AbstractAggregate;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MainWheel extends AbstractAggregate {
    //Общее кол-во посадок с начала эксплуатации
    private int totalLandings;
    //Остаток посадок  до назначенного ресурса (750пос)
    private int resource_Reserve_Replacement_Wheel;

    @Override
    public String toString() { return "сер/№ " + super.getSerialNumber(); }
}
