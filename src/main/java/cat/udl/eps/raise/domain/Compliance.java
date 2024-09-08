package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Compliance extends UriEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FAIRPrinciple principle;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "raise_instance_id", nullable = false)
    private RaiseInstance instance;

    @NotNull
    private LocalDate verificationDate;
}
