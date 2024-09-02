package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Dataset dataset;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Repository repository;

    @ManyToOne (cascade = CascadeType.DETACH)
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Compliance> compliances;

    @NotNull
    private LocalDate date;

    @NotNull
    private boolean isAgreeToRaise;

    @NotNull
    private short feedFrequencyInDays;

    @NotNull
    private boolean isInRisk;
    private LocalDate nextFeedAction;

}
