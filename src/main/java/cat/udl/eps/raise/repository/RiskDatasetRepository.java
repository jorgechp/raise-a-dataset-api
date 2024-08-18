package cat.udl.eps.raise.repository;

import cat.udl.eps.raise.domain.RiskDataset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RiskDatasetRepository extends CrudRepository<RiskDataset, Long>, PagingAndSortingRepository<RiskDataset, Long> {

  /* Interface provides automatically, as defined in
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
   * and
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
   * the methods: count, delete, deleteAll, deleteById, existsById, findAll, findAllById, findById, save, saveAll,...
   *
   * Additional methods like findByUsernameContaining can be defined following:
   * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
   */

   Optional<RiskDataset> findByName(@Param("name") String name);



}
