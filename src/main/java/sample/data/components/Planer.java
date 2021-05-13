package sample.data.components;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Planer {
    //Общая наработка по планеру с начала эксплуатации
    private int total_Operating_Time;
    //Наработка по планеру за летную смену
   //private int operatingTime;
    // Общее кол - во посадок с начала эксплуатации
    private int total_Landing_Count;
    //Кол-во посадок за смену
    //private int landingCount;
    //Остаток ресурса до 100-ти часовых работ
    private int resource_Reserve_Before_100hours;
    //Остаток ресурса до 200-та часовых работ
    private int resource_Reserve_Before_200hours;
    // !(ДОБАВИТЬ ПОЛЕ) - Остаток дней до работ (30+6 дней стоянки с крайнего полета)
    // !(ДОБАВИТЬ ПОЛЕ) - Остаток дней до работ (6+1 месяцев эксплуатации)

}
