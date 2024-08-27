package cat.udl.eps.raise.repository;

import cat.udl.eps.raise.domain.Mission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface MissionRepository extends CrudRepository<Mission, Long>, PagingAndSortingRepository<Mission, Long> {

    /* Interface provides automatically, as defined in
     * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
     * and
     * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
     * the methods: count, delete, deleteAll, deleteById, existsById, findAll, findAllById, findById, save, saveAll,...
     *
     * Additional methods like findByUsernameContaining can be defined following:
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
     */


    Optional<Mission> findMissionByName(@Param("name") String name);
    Optional<Mission> findMissionByRuleName(@Param("ruleName") String ruleName);
    Optional<Mission> findMissionById(@Param("ruleId") Long ruleId);
    Iterable<Mission> findAllByOrderByLevelAsc();
}
