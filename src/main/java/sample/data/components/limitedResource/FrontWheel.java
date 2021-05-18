package sample.data.components.limitedResource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrontWheel {
    //Общее кол-во посадок с начала эксплуатации
    private int totalLandings;
    //Кол-во посадок за летную смену
    //private int landingsForFlights;
    //Остаток посадок  до назначенного ресурса (1280пос)
    private int resource_Reserve_Replacement_Wheel;
    //Серийный номер переднего колеса
    private String serialNumber;
    private String aircraftNumberInstalled;

    @Override
    public String toString() {
        return serialNumber;
    }
}
