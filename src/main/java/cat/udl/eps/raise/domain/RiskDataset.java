package cat.udl.eps.raise.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("R")
@Entity
@Data
public class RiskDataset extends AbstractDataset {

    @NotBlank
    private String address;

    @Enumerated(EnumType.ORDINAL)
    private RISKCategories category;

}
