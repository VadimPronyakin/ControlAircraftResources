package sample.setBoolean;

import sample.data.Aircraft;
import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;

/**
 * Клоасс содержит в себе методы, которые сэтят значение в переменную boolean isNeedAttention в зависимости от остатка ресурсов
 */

public class SetBooleanValue {
    /**
     * Метод сетит значение в переменную boolean isNeedAttention в зависимости от наличия или отсутсвия элементов силовой установки
     * (двигатели и кса)
     */
    public static boolean setBooleanValueAircraft(Aircraft aircraft) {
        boolean attention = false;
        if (aircraft.getLeftEngine() != null && aircraft.getRightEngine() != null && aircraft.getKsa() != null) {
            if (aircraft.getRightEngine().getIsNeedAttention() == false
                    && aircraft.getLeftEngine().getIsNeedAttention() == false
                    && aircraft.getKsa().getIsNeedAttention() == false
                    && aircraft.getPlaner().getIsNeedAttention() == false
                    && aircraft.getRightMainWheel().getIsNeedAttention() == false
                    && aircraft.getLeftMainWheel().getIsNeedAttention() == false
                    && aircraft.getRightFrontWheel().getIsNeedAttention() == false
                    && aircraft.getLeftFrontWheel().getIsNeedAttention() == false
                    && aircraft.getRightMainBrake().getIsNeedAttention() == false
                    && aircraft.getLeftMainBrake().getIsNeedAttention() == false
                    && aircraft.getRightFrontBrake().getIsNeedAttention() == false
                    && aircraft.getLeftFrontBrake().getIsNeedAttention() == false
                    && aircraft.getRightMainCylinder().getIsNeedAttention() == false
                    && aircraft.getLeftMainCylinder().getIsNeedAttention() == false
                    && aircraft.getFrontCylinder().getIsNeedAttention() == false) {
                attention = false;
            } else if (aircraft.getRightEngine().getIsNeedAttention() == true
                    || aircraft.getLeftEngine().getIsNeedAttention() == true
                    || aircraft.getKsa().getIsNeedAttention() == true
                    || aircraft.getPlaner().getIsNeedAttention() == true
                    || aircraft.getLeftMainWheel().getIsNeedAttention() == true
                    || aircraft.getRightMainWheel().getIsNeedAttention() == true
                    || aircraft.getLeftFrontWheel().getIsNeedAttention() == true
                    || aircraft.getRightFrontWheel().getIsNeedAttention() == true
                    || aircraft.getLeftMainBrake().getIsNeedAttention() == true
                    || aircraft.getRightMainBrake().getIsNeedAttention() == true
                    || aircraft.getLeftFrontBrake().getIsNeedAttention() == true
                    || aircraft.getRightFrontBrake().getIsNeedAttention() == true
                    || aircraft.getLeftMainCylinder().getIsNeedAttention() == true
                    || aircraft.getRightMainCylinder().getIsNeedAttention() == true
                    || aircraft.getFrontCylinder().getIsNeedAttention() == true) {
                attention = true;
            }
        } else {
            if (aircraft.getPlaner().getIsNeedAttention() == false
                    && aircraft.getRightMainWheel().getIsNeedAttention() == false
                    && aircraft.getLeftMainWheel().getIsNeedAttention() == false
                    && aircraft.getRightFrontWheel().getIsNeedAttention() == false
                    && aircraft.getLeftFrontWheel().getIsNeedAttention() == false
                    && aircraft.getRightMainBrake().getIsNeedAttention() == false
                    && aircraft.getLeftMainBrake().getIsNeedAttention() == false
                    && aircraft.getRightFrontBrake().getIsNeedAttention() == false
                    && aircraft.getLeftFrontBrake().getIsNeedAttention() == false
                    && aircraft.getRightMainCylinder().getIsNeedAttention() == false
                    && aircraft.getLeftMainCylinder().getIsNeedAttention() == false
                    && aircraft.getFrontCylinder().getIsNeedAttention() == false) {
                attention = false;
            } else if (aircraft.getPlaner().getIsNeedAttention() == true
                    || aircraft.getLeftMainWheel().getIsNeedAttention() == true
                    || aircraft.getRightMainWheel().getIsNeedAttention() == true
                    || aircraft.getLeftFrontWheel().getIsNeedAttention() == true
                    || aircraft.getRightFrontWheel().getIsNeedAttention() == true
                    || aircraft.getLeftMainBrake().getIsNeedAttention() == true
                    || aircraft.getRightMainBrake().getIsNeedAttention() == true
                    || aircraft.getLeftFrontBrake().getIsNeedAttention() == true
                    || aircraft.getRightFrontBrake().getIsNeedAttention() == true
                    || aircraft.getLeftMainCylinder().getIsNeedAttention() == true
                    || aircraft.getRightMainCylinder().getIsNeedAttention() == true
                    || aircraft.getFrontCylinder().getIsNeedAttention() == true) {
                attention = true;
            }
        }
        if (aircraft.getLeftEngine() == null && aircraft.getRightEngine() == null && aircraft.getKsa() != null) {
            if (aircraft.getKsa().getIsNeedAttention() == false
                    && aircraft.getPlaner().getIsNeedAttention() == false
                    && aircraft.getRightMainWheel().getIsNeedAttention() == false
                    && aircraft.getLeftMainWheel().getIsNeedAttention() == false
                    && aircraft.getRightFrontWheel().getIsNeedAttention() == false
                    && aircraft.getLeftFrontWheel().getIsNeedAttention() == false
                    && aircraft.getRightMainBrake().getIsNeedAttention() == false
                    && aircraft.getLeftMainBrake().getIsNeedAttention() == false
                    && aircraft.getRightFrontBrake().getIsNeedAttention() == false
                    && aircraft.getLeftFrontBrake().getIsNeedAttention() == false
                    && aircraft.getRightMainCylinder().getIsNeedAttention() == false
                    && aircraft.getLeftMainCylinder().getIsNeedAttention() == false
                    && aircraft.getFrontCylinder().getIsNeedAttention() == false) {
                attention = false;
            } else if (aircraft.getKsa().getIsNeedAttention() == true
                    || aircraft.getPlaner().getIsNeedAttention() == true
                    || aircraft.getLeftMainWheel().getIsNeedAttention() == true
                    || aircraft.getRightMainWheel().getIsNeedAttention() == true
                    || aircraft.getLeftFrontWheel().getIsNeedAttention() == true
                    || aircraft.getRightFrontWheel().getIsNeedAttention() == true
                    || aircraft.getLeftMainBrake().getIsNeedAttention() == true
                    || aircraft.getRightMainBrake().getIsNeedAttention() == true
                    || aircraft.getLeftFrontBrake().getIsNeedAttention() == true
                    || aircraft.getRightFrontBrake().getIsNeedAttention() == true
                    || aircraft.getLeftMainCylinder().getIsNeedAttention() == true
                    || aircraft.getRightMainCylinder().getIsNeedAttention() == true
                    || aircraft.getFrontCylinder().getIsNeedAttention() == true) {
                attention = true;
            }
        }
        if (aircraft.getLeftEngine() == null && aircraft.getRightEngine() != null && aircraft.getKsa() == null) {
            if (aircraft.getRightEngine().getIsNeedAttention() == false
                    && aircraft.getPlaner().getIsNeedAttention() == false
                    && aircraft.getRightMainWheel().getIsNeedAttention() == false
                    && aircraft.getLeftMainWheel().getIsNeedAttention() == false
                    && aircraft.getRightFrontWheel().getIsNeedAttention() == false
                    && aircraft.getLeftFrontWheel().getIsNeedAttention() == false
                    && aircraft.getRightMainBrake().getIsNeedAttention() == false
                    && aircraft.getLeftMainBrake().getIsNeedAttention() == false
                    && aircraft.getRightFrontBrake().getIsNeedAttention() == false
                    && aircraft.getLeftFrontBrake().getIsNeedAttention() == false
                    && aircraft.getRightMainCylinder().getIsNeedAttention() == false
                    && aircraft.getLeftMainCylinder().getIsNeedAttention() == false
                    && aircraft.getFrontCylinder().getIsNeedAttention() == false) {
                attention = false;
            } else if (aircraft.getRightEngine().getIsNeedAttention() == true
                    || aircraft.getPlaner().getIsNeedAttention() == true
                    || aircraft.getLeftMainWheel().getIsNeedAttention() == true
                    || aircraft.getRightMainWheel().getIsNeedAttention() == true
                    || aircraft.getLeftFrontWheel().getIsNeedAttention() == true
                    || aircraft.getRightFrontWheel().getIsNeedAttention() == true
                    || aircraft.getLeftMainBrake().getIsNeedAttention() == true
                    || aircraft.getRightMainBrake().getIsNeedAttention() == true
                    || aircraft.getLeftFrontBrake().getIsNeedAttention() == true
                    || aircraft.getRightFrontBrake().getIsNeedAttention() == true
                    || aircraft.getLeftMainCylinder().getIsNeedAttention() == true
                    || aircraft.getRightMainCylinder().getIsNeedAttention() == true
                    || aircraft.getFrontCylinder().getIsNeedAttention() == true) {
                attention = true;
            }
        }
        if (aircraft.getLeftEngine() != null && aircraft.getRightEngine() == null && aircraft.getKsa() == null) {
            if (aircraft.getLeftEngine().getIsNeedAttention() == false
                    && aircraft.getPlaner().getIsNeedAttention() == false
                    && aircraft.getRightMainWheel().getIsNeedAttention() == false
                    && aircraft.getLeftMainWheel().getIsNeedAttention() == false
                    && aircraft.getRightFrontWheel().getIsNeedAttention() == false
                    && aircraft.getLeftFrontWheel().getIsNeedAttention() == false
                    && aircraft.getRightMainBrake().getIsNeedAttention() == false
                    && aircraft.getLeftMainBrake().getIsNeedAttention() == false
                    && aircraft.getRightFrontBrake().getIsNeedAttention() == false
                    && aircraft.getLeftFrontBrake().getIsNeedAttention() == false
                    && aircraft.getRightMainCylinder().getIsNeedAttention() == false
                    && aircraft.getLeftMainCylinder().getIsNeedAttention() == false
                    && aircraft.getFrontCylinder().getIsNeedAttention() == false) {
                attention = false;
            } else if (aircraft.getLeftEngine().getIsNeedAttention() == true
                    || aircraft.getPlaner().getIsNeedAttention() == true
                    || aircraft.getLeftMainWheel().getIsNeedAttention() == true
                    || aircraft.getRightMainWheel().getIsNeedAttention() == true
                    || aircraft.getLeftFrontWheel().getIsNeedAttention() == true
                    || aircraft.getRightFrontWheel().getIsNeedAttention() == true
                    || aircraft.getLeftMainBrake().getIsNeedAttention() == true
                    || aircraft.getRightMainBrake().getIsNeedAttention() == true
                    || aircraft.getLeftFrontBrake().getIsNeedAttention() == true
                    || aircraft.getRightFrontBrake().getIsNeedAttention() == true
                    || aircraft.getLeftMainCylinder().getIsNeedAttention() == true
                    || aircraft.getRightMainCylinder().getIsNeedAttention() == true
                    || aircraft.getFrontCylinder().getIsNeedAttention() == true) {
                attention = true;
            }
        }
        if (aircraft.getLeftEngine() == null && aircraft.getRightEngine() != null && aircraft.getKsa() != null) {

            if (aircraft.getRightEngine().getIsNeedAttention() == false
                    && aircraft.getKsa().getIsNeedAttention() == false
                    && aircraft.getPlaner().getIsNeedAttention() == false
                    && aircraft.getRightMainWheel().getIsNeedAttention() == false
                    && aircraft.getLeftMainWheel().getIsNeedAttention() == false
                    && aircraft.getRightFrontWheel().getIsNeedAttention() == false
                    && aircraft.getLeftFrontWheel().getIsNeedAttention() == false
                    && aircraft.getRightMainBrake().getIsNeedAttention() == false
                    && aircraft.getLeftMainBrake().getIsNeedAttention() == false
                    && aircraft.getRightFrontBrake().getIsNeedAttention() == false
                    && aircraft.getLeftFrontBrake().getIsNeedAttention() == false
                    && aircraft.getRightMainCylinder().getIsNeedAttention() == false
                    && aircraft.getLeftMainCylinder().getIsNeedAttention() == false
                    && aircraft.getFrontCylinder().getIsNeedAttention() == false) {
                attention = false;
            } else if (aircraft.getRightEngine().getIsNeedAttention() == true
                    || aircraft.getKsa().getIsNeedAttention() == true
                    || aircraft.getPlaner().getIsNeedAttention() == true
                    || aircraft.getLeftMainWheel().getIsNeedAttention() == true
                    || aircraft.getRightMainWheel().getIsNeedAttention() == true
                    || aircraft.getLeftFrontWheel().getIsNeedAttention() == true
                    || aircraft.getRightFrontWheel().getIsNeedAttention() == true
                    || aircraft.getLeftMainBrake().getIsNeedAttention() == true
                    || aircraft.getRightMainBrake().getIsNeedAttention() == true
                    || aircraft.getLeftFrontBrake().getIsNeedAttention() == true
                    || aircraft.getRightFrontBrake().getIsNeedAttention() == true
                    || aircraft.getLeftMainCylinder().getIsNeedAttention() == true
                    || aircraft.getRightMainCylinder().getIsNeedAttention() == true
                    || aircraft.getFrontCylinder().getIsNeedAttention() == true) {
                attention = true;
            }
        }
        if (aircraft.getRightEngine() == null && aircraft.getLeftEngine() != null && aircraft.getKsa() != null) {
            if (aircraft.getLeftEngine().getIsNeedAttention() == false
                    && aircraft.getKsa().getIsNeedAttention() == false
                    && aircraft.getPlaner().getIsNeedAttention() == false
                    && aircraft.getRightMainWheel().getIsNeedAttention() == false
                    && aircraft.getLeftMainWheel().getIsNeedAttention() == false
                    && aircraft.getRightFrontWheel().getIsNeedAttention() == false
                    && aircraft.getLeftFrontWheel().getIsNeedAttention() == false
                    && aircraft.getRightMainBrake().getIsNeedAttention() == false
                    && aircraft.getLeftMainBrake().getIsNeedAttention() == false
                    && aircraft.getRightFrontBrake().getIsNeedAttention() == false
                    && aircraft.getLeftFrontBrake().getIsNeedAttention() == false
                    && aircraft.getRightMainCylinder().getIsNeedAttention() == false
                    && aircraft.getLeftMainCylinder().getIsNeedAttention() == false
                    && aircraft.getFrontCylinder().getIsNeedAttention() == false) {
                attention = false;
            } else if (aircraft.getLeftEngine().getIsNeedAttention() == true
                    || aircraft.getKsa().getIsNeedAttention() == true
                    || aircraft.getPlaner().getIsNeedAttention() == true
                    || aircraft.getLeftMainWheel().getIsNeedAttention() == true
                    || aircraft.getRightMainWheel().getIsNeedAttention() == true
                    || aircraft.getLeftFrontWheel().getIsNeedAttention() == true
                    || aircraft.getRightFrontWheel().getIsNeedAttention() == true
                    || aircraft.getLeftMainBrake().getIsNeedAttention() == true
                    || aircraft.getRightMainBrake().getIsNeedAttention() == true
                    || aircraft.getLeftFrontBrake().getIsNeedAttention() == true
                    || aircraft.getRightFrontBrake().getIsNeedAttention() == true
                    || aircraft.getLeftMainCylinder().getIsNeedAttention() == true
                    || aircraft.getRightMainCylinder().getIsNeedAttention() == true
                    || aircraft.getFrontCylinder().getIsNeedAttention() == true) {
                attention = true;
            }
        }
        if (aircraft.getKsa() == null && aircraft.getLeftEngine() != null && aircraft.getRightEngine() != null) {
            if (aircraft.getRightEngine().getIsNeedAttention() == false
                    && aircraft.getLeftEngine().getIsNeedAttention() == false
                    && aircraft.getPlaner().getIsNeedAttention() == false
                    && aircraft.getRightMainWheel().getIsNeedAttention() == false
                    && aircraft.getLeftMainWheel().getIsNeedAttention() == false
                    && aircraft.getRightFrontWheel().getIsNeedAttention() == false
                    && aircraft.getLeftFrontWheel().getIsNeedAttention() == false
                    && aircraft.getRightMainBrake().getIsNeedAttention() == false
                    && aircraft.getLeftMainBrake().getIsNeedAttention() == false
                    && aircraft.getRightFrontBrake().getIsNeedAttention() == false
                    && aircraft.getLeftFrontBrake().getIsNeedAttention() == false
                    && aircraft.getRightMainCylinder().getIsNeedAttention() == false
                    && aircraft.getLeftMainCylinder().getIsNeedAttention() == false
                    && aircraft.getFrontCylinder().getIsNeedAttention() == false) {
                attention = false;
            } else if (aircraft.getRightEngine().getIsNeedAttention() == true
                    && aircraft.getLeftEngine().getIsNeedAttention() == true
                    || aircraft.getPlaner().getIsNeedAttention() == true
                    || aircraft.getLeftMainWheel().getIsNeedAttention() == true
                    || aircraft.getRightMainWheel().getIsNeedAttention() == true
                    || aircraft.getLeftFrontWheel().getIsNeedAttention() == true
                    || aircraft.getRightFrontWheel().getIsNeedAttention() == true
                    || aircraft.getLeftMainBrake().getIsNeedAttention() == true
                    || aircraft.getRightMainBrake().getIsNeedAttention() == true
                    || aircraft.getLeftFrontBrake().getIsNeedAttention() == true
                    || aircraft.getRightFrontBrake().getIsNeedAttention() == true
                    || aircraft.getLeftMainCylinder().getIsNeedAttention() == true
                    || aircraft.getRightMainCylinder().getIsNeedAttention() == true
                    || aircraft.getFrontCylinder().getIsNeedAttention() == true) {
                attention = true;
            }
        }
        return attention;
    }

    public static boolean setBooleanValueEngine(Engine engine) {
        boolean attention = false;
        if (engine.getResourceReserveBefore_10hours() <= 270
                || engine.getResourceReserveBefore_25hours() <= 300
                || engine.getResourceReserveBefore_50hours() <= 300
                || engine.getResourceReserveBefore_100hours() <= 600
                || engine.getResourceReserveBefore_150hours() <= 600
                || engine.getResourceReserveBefore_278bulletin() <= 300
                || engine.getOilChange() <= 600) {
            attention = true;
        } else if (engine.getResourceReserveBefore_10hours() > 270
                && engine.getResourceReserveBefore_25hours() > 300
                && engine.getResourceReserveBefore_50hours() > 300
                && engine.getResourceReserveBefore_100hours() > 600
                && engine.getResourceReserveBefore_150hours() > 600
                && engine.getResourceReserveBefore_278bulletin() > 300
                && engine.getOilChange() > 600) {
            attention = false;
        }
        return attention;
    }

    public static boolean setBooleanValueKsa(Ksa ksa) {
        boolean attention = false;
        if (ksa.getResource_Reserve_Before_25hours() <= 300
                || ksa.getOilChange() <= 600) {
            attention = true;
        } else if (ksa.getResource_Reserve_Before_25hours() > 300
                && ksa.getOilChange() > 600) {
            attention = false;
        }
        return attention;
    }

    public static boolean setBooleanValueCylinder(CylinderOfRetractionExtension cylinder) {
        boolean attention = false;
        if (cylinder.getResource_Reserve_Before_First_Repair() <= 15
                || cylinder.getResource_Reserve_Before_Second_Repair() <= 15
                || cylinder.getResource_Reserve_Before_Replacement() <= 15) {
            attention = true;
        } else if (cylinder.getResource_Reserve_Before_First_Repair() > 15
                && cylinder.getResource_Reserve_Before_Second_Repair() > 15
                && cylinder.getResource_Reserve_Before_Replacement() > 15) {
            attention = false;
        }
        return attention;
    }

    public static boolean setBooleanValueFrontBreak(FrontBreak frontBreak) {
        boolean attention = false;
        if (frontBreak.getResource_Reserve_Before_First_Repair() <= 15
                || frontBreak.getResource_Reserve_Before_Replacement() <= 15) {
            attention = true;
        } else if (frontBreak.getResource_Reserve_Before_First_Repair() > 15
                && frontBreak.getResource_Reserve_Before_Replacement() > 15) {
            attention = false;
        }
        return attention;
    }

    public static boolean setBooleanValueFrontWheel(FrontWheel frontWheel) {
        boolean attention = false;
        if (frontWheel.getResource_Reserve_Replacement_Wheel() <= 15) {
            attention = true;
        } else if (frontWheel.getResource_Reserve_Replacement_Wheel() > 15) {
            attention = false;
        }
        return attention;
    }

    public static boolean setBooleanValueMainBreak(MainBreak mainBreak) {
        boolean attention = false;
        if (mainBreak.getResource_Reserve_Replacement_Break() <= 15
                || mainBreak.getResource_Reserve_Replacement_RotatingDisks() <= 15
                || mainBreak.getResource_Reserve_Replacement_NonRotatingDisks() <= 15
                || (mainBreak.getResource_Reserve_Replacement_PressureDisk() <= 15
                && mainBreak.getResource_Reserve_Replacement_PressureDisk() != 0)
                || (mainBreak.getResource_Reserve_Replacement_ReferenceDisk() <= 15
                && mainBreak.getResource_Reserve_Replacement_ReferenceDisk() != 0)) {
            attention = true;
        } else if (mainBreak.getResource_Reserve_Replacement_Break() > 15
                && mainBreak.getResource_Reserve_Replacement_RotatingDisks() > 15
                && mainBreak.getResource_Reserve_Replacement_NonRotatingDisks() > 15
                && (mainBreak.getResource_Reserve_Replacement_PressureDisk() > 15
                || mainBreak.getResource_Reserve_Replacement_PressureDisk() == 0)
                && (mainBreak.getResource_Reserve_Replacement_ReferenceDisk() > 15
                || mainBreak.getResource_Reserve_Replacement_ReferenceDisk() == 0)) {
            attention = false;
        }
        return attention;
    }

    public static boolean setBooleanValueMainWheel(MainWheel mainWheel) {
        boolean attention = false;
        if (mainWheel.getResource_Reserve_Replacement_Wheel() <= 15) {
            attention = true;
        } else if (mainWheel.getResource_Reserve_Replacement_Wheel() > 15) {
            attention = false;
        }
        return attention;
    }

    public static boolean setBooleanValuePlaner(Planer planer) {
        boolean attention = false;
        if (planer.getResource_Reserve_Before_100hours() <= 600
                || planer.getResource_Reserve_Before_200hours() <= 600
                || planer.getDays_Reserve_Before_30DaysParking() <= 432_000_000
                || planer.getDays_Reserve_Before_6months_Operating() <= 432_000_000) {
            attention = true;
        } else if (planer.getResource_Reserve_Before_100hours() > 600
                && planer.getResource_Reserve_Before_200hours() > 600
                && planer.getDays_Reserve_Before_30DaysParking() > 432_000_000
                && planer.getDays_Reserve_Before_6months_Operating() > 432_000_000) {
            attention = false;
        }
        return attention;
    }

}

