package cat.udl.eps.raise.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Mission extends UriEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private int points;

    @NotNull
    private int level;

    @NotNull
    @Column(unique=true)
    private String ruleName;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "missionsAccepted")
    private Set<User> usersAccepted;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "missionsAcomplished")
    private Set<User> usersAccomplished;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mission mission)) return false;
        return Objects.equals(id, mission.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", level=" + level +
                ", ruleName='" + ruleName + '\'' +
                '}';
    }
}
