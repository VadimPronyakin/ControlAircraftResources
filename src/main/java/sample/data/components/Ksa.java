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
    //наработка кса за летную смену
    private int operatingTime;
    //Общее кол-во запусков кса
    private int total_Starting_Ksa_Count;
    //Кол-во запусков кса за летную смену
    private int starting_Ksa_Count;
    //Остаток ресурса до 25-ти часовых работ
    private int resource_Reserve_Before_25hours;
    //Остаток до замены масла (200+30ч или 2 года)
    private int oilChange;
}
