package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.sql.Time;
import java.util.Set;

@Entity
@Data
public class Dataset {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String author;
    private String description;
    private Time creationDate;
    private Time registrationDate;

    @ManyToMany
    private Set<User> authorInSystem;



}
