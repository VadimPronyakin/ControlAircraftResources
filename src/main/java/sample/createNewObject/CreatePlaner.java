package sample.createNewObject;

import sample.data.components.Planer;

public class CreatePlaner {
    public static void createNewPlaner(int totalOperatingTime,
                                       int operatingTime,
                                       int totalLandingCount,
                                       int landingCount,
                                       int resourceReserveBefore_100hours,
                                       int resourceReserveBefore_200hours) {
        Planer planer = Planer.builder()
                .total_Operating_Time(totalOperatingTime)
                .total_Operating_Time(operatingTime)
                .total_Landing_Count(totalLandingCount)
                .landingCount(landingCount)
                .resource_Reserve_Before_100hours(resourceReserveBefore_100hours)
                .resource_Reserve_Before_200hours(resourceReserveBefore_200hours)
                .build();
    }
}
