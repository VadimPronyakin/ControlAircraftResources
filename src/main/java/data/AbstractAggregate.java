package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractAggregate {
    //Серийный номер агрегата
    private String serialNumber;
    //Номер самолета,на котором установлен агрегат
    private String aircraftNumberInstalled;
    //Индикатор критического остатка
    private Boolean isNeedAttention;
}
