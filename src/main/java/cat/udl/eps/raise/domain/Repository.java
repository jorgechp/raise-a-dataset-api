package cat.udl.eps.raise.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Repository extends UriEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String url;

    @Override
    public Long getId() { return id; }
}
