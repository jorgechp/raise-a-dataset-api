package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class RaiseInstance extends UriEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String uniqueIdentifier;

    @ManyToOne
    private Dataset dataset;

    @ManyToOne
    private Repository repository;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<Compliance> compliances;

    private LocalDate date;

    @NotNull
    private boolean isInRisk;

}
