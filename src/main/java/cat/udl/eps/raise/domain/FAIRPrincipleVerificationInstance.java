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
public class FAIRPrincipleVerificationInstance extends UriEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> verifiers;

    @ManyToOne(fetch = FetchType.EAGER)
    private FAIRPrinciple fairPrinciple;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "raise_instance_id", nullable = false)
    private RaiseInstance instance;

    @NotNull
    private LocalDate verificationDate;
}
