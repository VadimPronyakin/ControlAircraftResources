package sample.data.components;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ksa {
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
    //Серийный номер КСА
    private String serialNumberKsa;
    //Номер самолета,на котором установлена КСА
    private String aircraftNumberInstalled;
    //Индикатор критического остатка
    private Boolean isNeedAttention;

    @Override
    public String toString() { return "сер/№ " + serialNumberKsa; }
}
