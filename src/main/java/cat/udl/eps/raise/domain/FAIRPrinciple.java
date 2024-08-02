package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class FAIRPrinciple extends UriEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String namePrefix;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String url;

    @Enumerated(EnumType.ORDINAL)
    private FAIRCategories category;

    @NotNull
    private short difficulty;

    @Override
    public Long getId() { return id; }
}
