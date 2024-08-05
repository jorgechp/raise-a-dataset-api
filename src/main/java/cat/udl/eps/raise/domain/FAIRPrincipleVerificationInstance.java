package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class FAIRPrincipleVerificationInstance extends UriEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private Set<User> verifiers;

    @ManyToOne
    private FAIRPrinciple fairPrinciple;

    @ManyToOne
    private User author;

    @ManyToOne
    @JoinColumn(name = "raise_instance_id", nullable = false)
    private RaiseInstance instance;
}
