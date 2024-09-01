package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("S")
@Entity
@Data
public class Dataset extends AbstractDataset {

    @ManyToMany
    private Set<User> maintainedBy;

    @ManyToMany
    private Set<Repository> repositories;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User rescuedBy;

    @OneToMany(mappedBy = "dataset", cascade = CascadeType.REMOVE)
    private List<RaiseInstance> raiseInstances;
}
