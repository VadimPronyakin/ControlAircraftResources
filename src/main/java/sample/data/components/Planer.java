package sample.data.components;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Planer {
    //Общая наработка по планеру с начала эксплуатации
    private int total_Operating_Time;
    // Общее кол - во посадок с начала эксплуатации
    private int total_Landing_Count;
    //Остаток ресурса до 100-ти часовых работ
    private int resource_Reserve_Before_100hours;
    //Остаток ресурса до 200-та часовых работ
    private int resource_Reserve_Before_200hours;
    //Бортовой номер
    private String sideNumber;
    //Остаток дней до работ (30+6 дней стоянки с крайнего полета)
    private long days_Reserve_Before_30DaysParking;
    //Остаток дней до работ (6+1 месяцев эксплуатации)
    private long days_Reserve_Before_6months_Operating;
    //Дата выполнения работ через 6+1 месяцев эксплуатации
    private Date date_Work_After_6months_Operation;
    //Дата крайнего полета
    private Date last_Flight_Date;
    //Дата выполнения работ через 30+6 дней стоянки
    private Date date_Work_After_30days_Parking;
    //Индикатор критического остатка
    private Boolean isNeedAttention;

    @Override
    public String toString() {
        return "б/н " + sideNumber;
    }
}








