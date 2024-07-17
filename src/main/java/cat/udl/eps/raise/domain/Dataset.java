package cat.udl.eps.raise.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Dataset extends UriEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique=true)
    private String name;
    @NotBlank
    private String author;

    private String description;
    @NotNull
    private LocalDate creationDate;
    @NotNull
    private LocalDate registrationDate;

    @ManyToMany
    private Set<User> authorInSystem;

    @ManyToMany
    private Set<Repository> repositories;

    @Override
    public Long getId() { return id; }
}
