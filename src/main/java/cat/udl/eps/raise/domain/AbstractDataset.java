package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="DATASET_TYPE")
@Entity
@Data
public abstract class AbstractDataset extends UriEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String createdBy;

    @NotBlank
    private String registeredBy;

    private String description;

    private LocalDate creationDate;

    @NotNull
    private LocalDate registrationDate;

    @NotNull
    private boolean isRescued;

    @Override
    public Long getId() { return id; }
}
