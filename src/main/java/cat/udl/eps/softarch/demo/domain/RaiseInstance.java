package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;


@Entity
@Data
public class RaiseInstance {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Dataset dataset;
    @OneToOne
    private Repository repository;
    @ManyToOne
    private User user;
    private Time date;

}
