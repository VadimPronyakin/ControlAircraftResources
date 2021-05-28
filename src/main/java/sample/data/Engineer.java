package sample.data;

import lombok.*;
import sample.data.enums.Rank;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Engineer {
    private String fullName;
    private Rank rank;
    private String link;
    private String ntzFullName;

    @Override
    public String toString() {
        return rank.getDescription() + " " + fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engineer engineer = (Engineer) o;
        return fullName.equals(engineer.fullName) &&
                rank == engineer.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, rank);
    }
}
