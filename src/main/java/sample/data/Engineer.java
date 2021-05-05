package sample.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sample.data.enums.Rank;

@Getter
@Setter
@Builder
public class Engineer {
    private String fullName;
    private Rank rank;

    @Override
    public String toString() {
        return rank.getDescription() + fullName;
    }
}
