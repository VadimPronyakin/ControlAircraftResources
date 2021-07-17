package data.components;

import data.AbstractAggregate;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Engine extends AbstractAggregate {
    //Общаая наработка двигателя c начала эксплуатации
    private int totalOperatingTime;
    //Общее кол-во запусков двигателя с начала эксплуатации
    private int totalStartingEngineCount;
    //Остаток ресурса до 10-ти часовых работ (10+2ч)
    private int resourceReserveBefore_10hours;
    //Остаток ресурса до 25-ти часовых работ (25+5ч)
    private int resourceReserveBefore_25hours;
    //Остаток ресурса до 278-го бюллетеня (25+5ч)
    private int resourceReserveBefore_278bulletin;
    //Остаток ресурса до 50-ти часовых работ (50+10ч)
    private int resourceReserveBefore_50hours;
    //Остаток ресурса до 100-ти часовых работ (100+10ч)
    private int resourceReserveBefore_100hours;
    //Остаток ресурса до 150-ти часовых работ (150+10ч)
    private int resourceReserveBefore_150hours;
    //Остаток до замены масла (200+30ч или 2 года)
    private int oilChange;


    @Override
    public String toString() { return "сер/№ " + super.getSerialNumber(); }
}
