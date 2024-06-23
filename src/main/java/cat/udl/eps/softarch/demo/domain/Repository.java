package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Repository {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String url;
}
