package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Validation extends UriEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    private Compliance compliance;

    @NotNull
    private LocalDate validationDate;

    @NotNull
    @ManyToOne
    private User validator;

    @NotNull
    private boolean isPositive;

    private String negativeComment;
}
