package cat.udl.eps.raise.repository;

import cat.udl.eps.raise.domain.RaiseInstance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.Collection;
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



    Optional<RaiseInstance> findByUniqueIdentifier(@Param("uniqueIdentifier") String uniqueIdentifier);
    Optional<List<RaiseInstance>> findAllByDatasetId(@Param("id") Long id);

    Optional<List<RaiseInstance>> findAllByRepositoryId(@Param("id") Long id);

    Optional<List<RaiseInstance>> findAllByUserUsername(@Param("username") String username);

    int countAllByUserUsername(@Param("username") String username);

    Optional<List<RaiseInstance>> findAllByIsAgreeToRaiseIsTrue();

    Optional<List<RaiseInstance>> findAllByIsAgreeToRaiseIsTrueAndNextFeedActionBefore(@Param("date") LocalDate date);

    @Query("SELECT r FROM RaiseInstance r WHERE r.isAgreeToRaise = true AND r.nextFeedAction < CURRENT_DATE")
    Collection<RaiseInstance> findAllByIsAgreeToRaiseIsTrueAndNextFeedActionBeforeCurrentDate();
}
