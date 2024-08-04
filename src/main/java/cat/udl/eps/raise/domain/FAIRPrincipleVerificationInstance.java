package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "fair_principle_verification_instance", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"instance_id", "fair_principle_id"})
})
public class FAIRPrincipleVerificationInstance extends UriEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private Set<User> verifiers;

    @ManyToOne
    @JoinColumn(name="fair_principle_id")
    private FAIRPrinciple fairPrinciple;
    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;
    @ManyToOne
    @JoinColumn(name="instance_id")
    private RaiseInstance instance;
}
