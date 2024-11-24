package cat.udl.eps.raise.repository;

import cat.udl.eps.raise.domain.FAIRPrincipleIndicator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface FAIRPrincipleRepository extends CrudRepository<FAIRPrincipleIndicator, Long>, PagingAndSortingRepository<FAIRPrincipleIndicator, Long> {

  /* Interface provides automatically, as defined in
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
   * and
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
   * the methods: count, delete, deleteAll, deleteById, existsById, findAll, findAllById, findById, save, saveAll,...
   *
   * Additional methods like findByUsernameContaining can be defined following:
   * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
   */

   Optional<FAIRPrincipleIndicator> findByName(@Param("name") String name);

   Optional<FAIRPrincipleIndicator> findByNamePrefix(@Param("namePrefix") String namePrefix);

   @Query("SELECT fp FROM FAIRPrincipleIndicator fp WHERE fp.id IN " +
           "(SELECT fpvi.principle.id FROM Compliance fpvi WHERE fpvi.instance.id = :raiseInstanceId)")
   Optional<List<FAIRPrincipleIndicator>> findCompletedPrinciplesByRaiseInstanceId(@Param("raiseInstanceId") Long raiseInstanceId);

   @Query("SELECT fp FROM FAIRPrincipleIndicator fp WHERE fp.id NOT IN " +
           "(SELECT fpvi.principle.id FROM Compliance fpvi WHERE fpvi.instance.id = :raiseInstanceId)")
   Optional<List<FAIRPrincipleIndicator>> findPendingPrinciplesByRaiseInstanceId(@Param("raiseInstanceId") Long raiseInstanceId);

}
