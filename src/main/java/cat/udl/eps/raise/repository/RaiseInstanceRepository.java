package cat.udl.eps.raise.repository;

import cat.udl.eps.raise.domain.RaiseInstance;
import cat.udl.eps.raise.projection.RaiseInstanceDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
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

    Optional<List<RaiseInstance>> findAllByIsAgreeToRaise(@Param("isAgreeToRaise") boolean isAgreeToRaise);

    @Query("SELECT new cat.udl.eps.raise.projection.RaiseInstanceDTO(ri.id, d.id, r.id, d.name, r.name, ri.isAgreeToRaise, ri.nextFeedAction, ri.feedFrequencyInDays) " +
            "FROM RaiseInstance ri INNER JOIN Dataset d ON ri.dataset.id = d.id INNER JOIN Repository r ON ri.repository.id = r.id " +
            "WHERE ri.isAgreeToRaise = :isAgreeToRaise AND ri.user.id = :userId")
    RaiseInstanceDTO[] findAllByIsAgreeToRaiseAndUserId(@Param("isAgreeToRaise") boolean isAgreeToRaise, @Param("userId") Long userId);

    Optional<List<RaiseInstance>> findAllByIsAgreeToRaiseIsTrueAndNextFeedActionBefore(@Param("date") LocalDate date);

    Optional<List<RaiseInstance>> findAllByIsAgreeToRaiseIsTrueAndNextFeedActionBeforeAndUserId(@Param("date") LocalDate date, @Param("userId") Long userId);

    @Query("SELECT new cat.udl.eps.raise.projection.RaiseInstanceDTO(ri.id, d.id, r.id, d.name, r.name, ri.isAgreeToRaise, ri.nextFeedAction, ri.feedFrequencyInDays) " +
            "FROM RaiseInstance ri INNER JOIN Dataset d ON ri.dataset.id = d.id INNER JOIN Repository r ON ri.repository.id = r.id " +
            "WHERE ri.isAgreeToRaise = true AND ri.nextFeedAction < CURRENT_DATE")
    RaiseInstanceDTO[] findAllByIsAgreeToRaiseIsTrueAndNextFeedActionBeforeCurrentDate();
    @Query("SELECT new cat.udl.eps.raise.projection.RaiseInstanceDTO(ri.id, d.id, r.id, d.name, r.name, ri.isAgreeToRaise, ri.nextFeedAction, ri.feedFrequencyInDays) " +
            "FROM RaiseInstance ri INNER JOIN Dataset d ON ri.dataset.id = d.id INNER JOIN Repository r ON ri.repository.id = r.id " +
            "WHERE ri.isAgreeToRaise = true AND ri.nextFeedAction < CURRENT_DATE AND ri.user.id = :userId")
    RaiseInstanceDTO[] findAllByIsAgreeToRaiseIsTrueAndNextFeedActionBeforeCurrentDateAndUserId(@Param("userId")  Long userId);
    @Query("SELECT new cat.udl.eps.raise.projection.RaiseInstanceDTO(ri.id, d.id, r.id, d.name, r.name, ri.isAgreeToRaise, ri.nextFeedAction, ri.feedFrequencyInDays) " +
            "FROM RaiseInstance ri INNER JOIN Dataset d ON ri.dataset.id = d.id INNER JOIN Repository r ON ri.repository.id = r.id " +
            "WHERE ri.isAgreeToRaise = true AND ri.nextFeedAction >= CURRENT_DATE")
    RaiseInstanceDTO[] findAllByIsAgreeToRaiseIsTrueAndNextFeedActionAfterCurrentDate();
    @Query("SELECT new cat.udl.eps.raise.projection.RaiseInstanceDTO(ri.id, d.id, r.id, d.name, r.name, ri.isAgreeToRaise, ri.nextFeedAction, ri.feedFrequencyInDays) " +
            "FROM RaiseInstance ri INNER JOIN Dataset d ON ri.dataset.id = d.id INNER JOIN Repository r ON ri.repository.id = r.id " +
            "WHERE ri.isAgreeToRaise = true AND ri.nextFeedAction >= CURRENT_DATE AND ri.user.id = :userId")
    RaiseInstanceDTO[] findAllByIsAgreeToRaiseIsTrueAndNextFeedActionAfterCurrentDateAndUserId(@Param("userId") Long userId);

    @Query("SELECT new cat.udl.eps.raise.projection.RaiseInstanceDTO(ri.id, d.id, r.id, d.name, r.name, ri.isAgreeToRaise, ri.nextFeedAction, ri.feedFrequencyInDays) " +
            "FROM RaiseInstance ri INNER JOIN Dataset d ON ri.dataset.id = d.id INNER JOIN Repository r ON ri.repository.id = r.id ")
    RaiseInstanceDTO[] findAllRaiseInstancesDTO();
}
