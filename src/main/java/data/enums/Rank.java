package data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rank {
    LIEUTENANT("Лейтенант"),
    ST_LIEUTENANT("Старший лейтенант"),
    CAPTAIN("Капитан"),
    MAJOR("Майор");

    private final String description;


    @Override
    public String toString() {
        return this.getDescription();
    }
}
