package data.components;

import data.AbstractAggregate;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Ksa extends AbstractAggregate {
    //Общаая наработка кса
    private int total_Operating_Time;
    //Общее кол-во запусков левого двигателя
    private int starting_Left_Count;
    //Общее кол-во последовательных запусков кса
    private int total_Starting_Ksa_Count;
    //Общее кол-во запусков правого
    private int starting_Right_Count;
    //Остаток ресурса до 25-ти часовых работ (25+5)
    private int resource_Reserve_Before_25hours;
    //Остаток до замены масла (200+30ч или 2 года)
    private int oilChange;

    @Override
    public String toString() { return "сер/№ " + super.getSerialNumber(); }
}
