package sample.createNewObject;

import sample.data.components.Ksa;

public class CreateKsa {
    public static void createNewKsa(int totalOperatingTime,
                                   int operatingTime,
                                   int totalStartingKsaCount,
                                   int startingKsaCount,
                                   int resourceReserveBefore_25hours,
                                   int oilChange) {
        Ksa ksa = Ksa.builder()
                .total_Operating_Time(totalOperatingTime)
                .operatingTime(operatingTime)
                .total_Starting_Ksa_Count(totalStartingKsaCount)
                .starting_Ksa_Count(startingKsaCount)
                .resource_Reserve_Before_25hours(resourceReserveBefore_25hours)
                .oilChange(oilChange)
                .build();
    }
}
