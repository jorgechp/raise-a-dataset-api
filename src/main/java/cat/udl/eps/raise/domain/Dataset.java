package cat.udl.eps.raise.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @ManyToOne
    private User rescuedBy;

}
