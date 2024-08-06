package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Repository extends UriEntity<Long>{
    public Repository() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String address;

    @NotNull
    @ManyToOne
    private User addedBy;

    @ManyToMany
    private Set<Dataset> datasets;

    @Override
    public Long getId() { return id; }
}
