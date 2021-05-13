package sample.data.components.limitedResource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainWheel {
    //Общее кол-во посадок с начала эксплуатации
    private int totalLandings;
    //Кол-во посадок за летную смену
    //private int landingsForFlights;
    //Остаток посадок  до назначенного ресурса (750пос)
    private int resource_Reserve_Replacement_Wheel;
    //Серийный номер основного колеса
    private String serialNumber;
}
