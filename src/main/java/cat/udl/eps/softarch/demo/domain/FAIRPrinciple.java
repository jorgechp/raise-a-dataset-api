package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class FAIRPrinciple {
    @Id
    @GeneratedValue
    private Long id;

    private String namePrefix;
    private String name;
    private String description;
    private String url;

    @Enumerated(EnumType.ORDINAL)
    private FAIRCategories category;

}
