package sample.data.components.limitedResource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrontBreak {
    //Общее кол-во посадок с начала эксплуатации
    private int totalLandings;
    //Кол-во посадок за летную смену
    private int landingsForFlights;
    //Остаток посадок  до 1-го ремонта (600пос)
    private int resource_Reserve_Before_Repair;
    //Остаток посадок  до назначенного ресурса (1280пос)
    private int resource_Reserve_Before_Replacement;
}
