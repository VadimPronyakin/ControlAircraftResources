package sample.data;

import lombok.Data;
import lombok.NonNull;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.MainBreak;


@Data
public class Aircraft {
    @NonNull
    private int aircraftNumber;
    @NonNull
    private Engineer iak;
    @NonNull
    private Planer planer;
    private Engine leftEngine;
    private Engine rightEngine;
    private Ksa ksa;
    private MainBreak leftMainBrake;
    private MainBreak rightMainBrake;
    private MainBreak leftFrontBrake;
    private MainBreak rightFrontBrake;
}
