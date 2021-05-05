package sample.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rank {
    LIEUTENANT("лейтенант"),
    ST_LIEUTENANT("старший лейтенант"),
    CAPTAIN("капитан"),
    MAJOR("майор");

    private final String description;

    @Override
    public String toString() {
        return this.getDescription();
    }
}
