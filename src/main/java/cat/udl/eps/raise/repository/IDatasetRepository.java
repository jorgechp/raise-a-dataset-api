package cat.udl.eps.raise.repository;

import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IDatasetRepository<T> {
    Optional<T> findByName(@Param("name") String name);
}
