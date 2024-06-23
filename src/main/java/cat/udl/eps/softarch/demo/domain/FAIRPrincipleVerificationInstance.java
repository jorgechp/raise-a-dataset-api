package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity
@Data
public class FAIRPrincipleVerificationInstance {
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
