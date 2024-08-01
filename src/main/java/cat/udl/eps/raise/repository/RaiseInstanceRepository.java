package cat.udl.eps.raise.repository;

import cat.udl.eps.raise.domain.RaiseInstance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface RaiseInstanceRepository extends CrudRepository<RaiseInstance, Long>, PagingAndSortingRepository<RaiseInstance, Long> {

  /* Interface provides automatically, as defined in
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
   * and
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
   * the methods: count, delete, deleteAll, deleteById, existsById, findAll, findAllById, findById, save, saveAll,...
   *
   * Additional methods like findByUsernameContaining can be defined following:
   * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
   */



    Optional<RaiseInstance> findByDoi(@Param("doi") String doi);
    Optional<List<RaiseInstance>> findAllByDatasetId(@Param("id") Long id);

    Optional<List<RaiseInstance>> findAllByRepositoryId(@Param("id") Long id);

    Optional<List<RaiseInstance>> findAllByUserUsername(@Param("username") String username);
}
