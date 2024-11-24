package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Validation extends UriEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne()
    private Compliance compliance;

    @CreationTimestamp
    private LocalDate validationDate;

    @NotNull
    @ManyToOne
    private User validator;

    @NotNull
    private boolean isPositive;

    @Lob
    @Column( length = 10000 )
    private String negativeComment;
}
