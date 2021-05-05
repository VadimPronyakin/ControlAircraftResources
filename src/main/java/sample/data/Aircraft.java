package sample.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.MainBreak;


@Setter
@Getter
@Builder
public class Aircraft {
    private int aircraftNumber;
    private Engineer iak;
    private Planer planer;
    private Engine leftEngine;
    private Engine rightEngine;
    private Ksa ksa;
    private MainBreak leftMainBrake;
    private MainBreak rightMainBrake;
    private MainBreak leftFrontBrake;
    private MainBreak rightFrontBrake;
}
