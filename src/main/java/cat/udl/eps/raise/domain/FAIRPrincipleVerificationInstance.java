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
    @OneToOne
    private FAIRPrinciple fairPrinciple;
    @OneToOne
    private User author;
    @ManyToOne
    private RaiseInstance instance;
}
