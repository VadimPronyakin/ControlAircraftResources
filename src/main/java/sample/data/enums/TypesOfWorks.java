package sample.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypesOfWorks {
    WORKS_AFTER_10_HOURS("Через 10+2 ч.н.", 720),
    WORKS_AFTER_25_HOURS("Через 25+5 ч.н.", 1800),
    WORKS_AFTER_278_BULLETIN("По 278 бюллетеню", 1800),
    WORKS_AFTER_50_HOURS("Через 50+10 ч.н.", 3600),
    WORKS_AFTER_100_HOURS("Через 100+10 ч.н.", 6600),
    WORKS_AFTER_150_HOURS("Через 150+10 ч.н.", 9600),
    OIL_CHANGE_OPERATIONS("Замена масла", 13800),
    REPLACEMENT_ROTATING_DISKS("Замена ВРАЩАЮЩ. дисков", 175),
    REPLACEMENT_NON_ROTATING_DISKS("Замена НЕВРАЩАЮЩ. дисков", 175),
    REPLACEMENT_PRESSURE_DISKS("Замена нажимного диска", 0),
    REPLACEMENT_REFERENCE_DISKS("Замена опорного дисков", 0),
    FIRST_REPAIR_FRONT_BREAK("Первый ремонт", 0),
    FIRST_REPAIR_CYLINDER("Первый ремонт", 1500),
    SECOND_REPAIR_CYLINDER("Второй ремонт", 800);

    private final String description;
    private final int resource;

    @Override
    public String toString() {
        return this.getDescription();
    }
}
