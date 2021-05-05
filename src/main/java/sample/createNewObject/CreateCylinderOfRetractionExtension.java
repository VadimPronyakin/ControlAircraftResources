package sample.createNewObject;

import sample.data.components.limitedResource.CylinderOfRetractionExtension;

public class CreateCylinderOfRetractionExtension {
    public static void createNewCylinder(int totalLandings,
                                         int landingsForFlights,
                                         int firstRepair,
                                         int secondRepair,
                                         int replacement) {
        CylinderOfRetractionExtension cylinder = CylinderOfRetractionExtension.builder()
                .totalLandings(totalLandings)
                .landings_For_Flights(landingsForFlights)
                .resource_Reserve_Before_First_Repair(firstRepair)
                .resource_Reserve_Before_Second_Repair(secondRepair)
                .resource_Reserve_Before_Replacement(replacement)
                .build();
    }
}
