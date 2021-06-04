package service;

import data.Aircraft;
import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.*;


public class BooleanValueSetter {
    /**
     * Метод сетит значение в переменную boolean isNeedAttention в зависимости от наличия или отсутсвия элементов силовой установки
     * (двигатели и кса)
     */
    public static boolean setBooleanValueAircraft(Aircraft aircraft) {
        boolean attention = false;
        if (aircraft.getLeftEngine() != null && aircraft.getRightEngine() != null && aircraft.getKsa() != null) {
            if (aircraft.getRightEngine().getIsNeedAttention()
                    || aircraft.getLeftEngine().getIsNeedAttention()
                    || aircraft.getKsa().getIsNeedAttention()
                    || aircraft.getPlaner().getIsNeedAttention()
                    || aircraft.getLeftMainWheel().getIsNeedAttention()
                    || aircraft.getRightMainWheel().getIsNeedAttention()
                    || aircraft.getLeftFrontWheel().getIsNeedAttention()
                    || aircraft.getRightFrontWheel().getIsNeedAttention()
                    || aircraft.getLeftMainBrake().getIsNeedAttention()
                    || aircraft.getRightMainBrake().getIsNeedAttention()
                    || aircraft.getLeftFrontBrake().getIsNeedAttention()
                    || aircraft.getRightFrontBrake().getIsNeedAttention()
                    || aircraft.getLeftMainCylinder().getIsNeedAttention()
                    || aircraft.getRightMainCylinder().getIsNeedAttention()
                    || aircraft.getFrontCylinder().getIsNeedAttention()) {
                attention = true;
            }
        } else {
            if (aircraft.getPlaner().getIsNeedAttention()
                    || aircraft.getLeftMainWheel().getIsNeedAttention()
                    || aircraft.getRightMainWheel().getIsNeedAttention()
                    || aircraft.getLeftFrontWheel().getIsNeedAttention()
                    || aircraft.getRightFrontWheel().getIsNeedAttention()
                    || aircraft.getLeftMainBrake().getIsNeedAttention()
                    || aircraft.getRightMainBrake().getIsNeedAttention()
                    || aircraft.getLeftFrontBrake().getIsNeedAttention()
                    || aircraft.getRightFrontBrake().getIsNeedAttention()
                    || aircraft.getLeftMainCylinder().getIsNeedAttention()
                    || aircraft.getRightMainCylinder().getIsNeedAttention()
                    || aircraft.getFrontCylinder().getIsNeedAttention()) {
                attention = true;
            }
        }
        if (aircraft.getLeftEngine() == null && aircraft.getRightEngine() == null && aircraft.getKsa() != null) {
            if (aircraft.getKsa().getIsNeedAttention()
                    || aircraft.getPlaner().getIsNeedAttention()
                    || aircraft.getLeftMainWheel().getIsNeedAttention()
                    || aircraft.getRightMainWheel().getIsNeedAttention()
                    || aircraft.getLeftFrontWheel().getIsNeedAttention()
                    || aircraft.getRightFrontWheel().getIsNeedAttention()
                    || aircraft.getLeftMainBrake().getIsNeedAttention()
                    || aircraft.getRightMainBrake().getIsNeedAttention()
                    || aircraft.getLeftFrontBrake().getIsNeedAttention()
                    || aircraft.getRightFrontBrake().getIsNeedAttention()
                    || aircraft.getLeftMainCylinder().getIsNeedAttention()
                    || aircraft.getRightMainCylinder().getIsNeedAttention()
                    || aircraft.getFrontCylinder().getIsNeedAttention()) {
                attention = true;
            }
        }
        if (aircraft.getLeftEngine() == null && aircraft.getRightEngine() != null && aircraft.getKsa() == null) {
            if (aircraft.getRightEngine().getIsNeedAttention()
                    || aircraft.getPlaner().getIsNeedAttention()
                    || aircraft.getLeftMainWheel().getIsNeedAttention()
                    || aircraft.getRightMainWheel().getIsNeedAttention()
                    || aircraft.getLeftFrontWheel().getIsNeedAttention()
                    || aircraft.getRightFrontWheel().getIsNeedAttention()
                    || aircraft.getLeftMainBrake().getIsNeedAttention()
                    || aircraft.getRightMainBrake().getIsNeedAttention()
                    || aircraft.getLeftFrontBrake().getIsNeedAttention()
                    || aircraft.getRightFrontBrake().getIsNeedAttention()
                    || aircraft.getLeftMainCylinder().getIsNeedAttention()
                    || aircraft.getRightMainCylinder().getIsNeedAttention()
                    || aircraft.getFrontCylinder().getIsNeedAttention()) {
                attention = true;
            }
        }
        if (aircraft.getLeftEngine() != null && aircraft.getRightEngine() == null && aircraft.getKsa() == null) {
            if (aircraft.getLeftEngine().getIsNeedAttention()
                    || aircraft.getPlaner().getIsNeedAttention()
                    || aircraft.getLeftMainWheel().getIsNeedAttention()
                    || aircraft.getRightMainWheel().getIsNeedAttention()
                    || aircraft.getLeftFrontWheel().getIsNeedAttention()
                    || aircraft.getRightFrontWheel().getIsNeedAttention()
                    || aircraft.getLeftMainBrake().getIsNeedAttention()
                    || aircraft.getRightMainBrake().getIsNeedAttention()
                    || aircraft.getLeftFrontBrake().getIsNeedAttention()
                    || aircraft.getRightFrontBrake().getIsNeedAttention()
                    || aircraft.getLeftMainCylinder().getIsNeedAttention()
                    || aircraft.getRightMainCylinder().getIsNeedAttention()
                    || aircraft.getFrontCylinder().getIsNeedAttention()) {
                attention = true;
            }
        }
        if (aircraft.getLeftEngine() == null && aircraft.getRightEngine() != null && aircraft.getKsa() != null) {

            if (aircraft.getRightEngine().getIsNeedAttention()
                    || aircraft.getKsa().getIsNeedAttention()
                    || aircraft.getPlaner().getIsNeedAttention()
                    || aircraft.getLeftMainWheel().getIsNeedAttention()
                    || aircraft.getRightMainWheel().getIsNeedAttention()
                    || aircraft.getLeftFrontWheel().getIsNeedAttention()
                    || aircraft.getRightFrontWheel().getIsNeedAttention()
                    || aircraft.getLeftMainBrake().getIsNeedAttention()
                    || aircraft.getRightMainBrake().getIsNeedAttention()
                    || aircraft.getLeftFrontBrake().getIsNeedAttention()
                    || aircraft.getRightFrontBrake().getIsNeedAttention()
                    || aircraft.getLeftMainCylinder().getIsNeedAttention()
                    || aircraft.getRightMainCylinder().getIsNeedAttention()
                    || aircraft.getFrontCylinder().getIsNeedAttention()) {
                attention = true;
            }
        }
        if (aircraft.getRightEngine() == null && aircraft.getLeftEngine() != null && aircraft.getKsa() != null) {
            if (aircraft.getLeftEngine().getIsNeedAttention()
                    || aircraft.getKsa().getIsNeedAttention()
                    || aircraft.getPlaner().getIsNeedAttention()
                    || aircraft.getLeftMainWheel().getIsNeedAttention()
                    || aircraft.getRightMainWheel().getIsNeedAttention()
                    || aircraft.getLeftFrontWheel().getIsNeedAttention()
                    || aircraft.getRightFrontWheel().getIsNeedAttention()
                    || aircraft.getLeftMainBrake().getIsNeedAttention()
                    || aircraft.getRightMainBrake().getIsNeedAttention()
                    || aircraft.getLeftFrontBrake().getIsNeedAttention()
                    || aircraft.getRightFrontBrake().getIsNeedAttention()
                    || aircraft.getLeftMainCylinder().getIsNeedAttention()
                    || aircraft.getRightMainCylinder().getIsNeedAttention()
                    || aircraft.getFrontCylinder().getIsNeedAttention()) {
                attention = true;
            }
        }
        if (aircraft.getKsa() == null && aircraft.getLeftEngine() != null && aircraft.getRightEngine() != null) {
            if (aircraft.getRightEngine().getIsNeedAttention()
                    || aircraft.getLeftEngine().getIsNeedAttention()
                    || aircraft.getPlaner().getIsNeedAttention()
                    || aircraft.getLeftMainWheel().getIsNeedAttention()
                    || aircraft.getRightMainWheel().getIsNeedAttention()
                    || aircraft.getLeftFrontWheel().getIsNeedAttention()
                    || aircraft.getRightFrontWheel().getIsNeedAttention()
                    || aircraft.getLeftMainBrake().getIsNeedAttention()
                    || aircraft.getRightMainBrake().getIsNeedAttention()
                    || aircraft.getLeftFrontBrake().getIsNeedAttention()
                    || aircraft.getRightFrontBrake().getIsNeedAttention()
                    || aircraft.getLeftMainCylinder().getIsNeedAttention()
                    || aircraft.getRightMainCylinder().getIsNeedAttention()
                    || aircraft.getFrontCylinder().getIsNeedAttention()) {
                attention = true;
            }
        }
        return attention;
    }

    /**
     * Метод сэтит значение в переменную boolean isNeedAttention в зависимости от остатка ресурсов двигателя
     */
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
        }
        return attention;
    }

    /**
     * Метод сэтит значение в переменную boolean isNeedAttention в зависимости от остатка ресурсов КСА
     */
    public static boolean setBooleanValueKsa(Ksa ksa) {
        boolean attention = false;
        if (ksa.getResource_Reserve_Before_25hours() <= 300
                || ksa.getOilChange() <= 600) {
            attention = true;
        }
        return attention;
    }

    /**
     * Метод сэтит значение в переменную boolean isNeedAttention в зависимости от остатка ресурсов цилиндра подкоса
     */
    public static boolean setBooleanValueCylinder(CylinderOfRetractionExtension cylinder) {
        boolean attention = false;
        if ((cylinder.getResource_Reserve_Before_First_Repair() <= 15
            && cylinder.getResource_Reserve_Before_First_Repair() != 0)
                || (cylinder.getResource_Reserve_Before_Second_Repair() <= 15
                    && cylinder.getResource_Reserve_Before_First_Repair() != 0)
                || cylinder.getResource_Reserve_Before_Replacement() <= 15) {
            attention = true;
        }
        return attention;
    }

    /**
     * Метод сэтит значение в переменную boolean isNeedAttention в зависимости от остатка ресурсов переднего тормоза
     */
    public static boolean setBooleanValueFrontBreak(FrontBreak frontBreak) {
        boolean attention = false;
        if ((frontBreak.getResource_Reserve_Before_First_Repair() <= 15
            && frontBreak.getResource_Reserve_Before_First_Repair() != 0)
                || frontBreak.getResource_Reserve_Before_Replacement() <= 15) {
            attention = true;
        }
        return attention;
    }

    /**
     * Метод сэтит значение в переменную boolean isNeedAttention в зависимости от остатка ресурсов переднего колеса
     */
    public static boolean setBooleanValueFrontWheel(FrontWheel frontWheel) {
        boolean attention = false;
        if (frontWheel.getResource_Reserve_Replacement_Wheel() <= 15) {
            attention = true;
        }
        return attention;
    }

    /**
     * Метод сэтит значение в переменную boolean isNeedAttention в зависимости от остатка ресурсов основного тормоза
     */
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
        }
        return attention;
    }

    /**
     * Метод сэтит значение в переменную boolean isNeedAttention в зависимости от остатка ресурсов основного колеса
     */
    public static boolean setBooleanValueMainWheel(MainWheel mainWheel) {
        boolean attention = false;
        if (mainWheel.getResource_Reserve_Replacement_Wheel() <= 15) {
            attention = true;
        }
        return attention;
    }

    /**
     * Метод сэтит значение в переменную boolean isNeedAttention в зависимости от остатка ресурсов планера
     */
    public static boolean setBooleanValuePlaner(Planer planer) {
        boolean attention = false;
        if (planer.getResource_Reserve_Before_100hours() <= 600
                || planer.getResource_Reserve_Before_200hours() <= 600
                || planer.getDays_Reserve_Before_30DaysParking() <= 432_000_000
                || planer.getDays_Reserve_Before_6months_Operating() <= 432_000_000) {
            attention = true;
        }
        return attention;
    }
}

