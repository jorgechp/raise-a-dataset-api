package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class RaiseInstance extends UriEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String doi;

    @ManyToOne
    private Dataset dataset;

    @OneToOne
    private Repository repository;

    @ManyToOne
    private User user;
    private LocalDate date;

}
