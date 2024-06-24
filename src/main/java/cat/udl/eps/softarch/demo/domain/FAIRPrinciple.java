package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @Override
    public Long getId() { return id; }
}
