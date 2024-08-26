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

    @Query("SELECT m FROM Mission m INNER JOIN User u ON (m MEMBER OF u.missionsAccepted )" +
            "AND u.username = :username")
    Optional<Mission[]> getMissionsForUser(@Param("username") String username);

    @Query("SELECT m FROM Mission m LEFT JOIN User u ON (m MEMBER OF u.missionsAccepted " +
            "OR m MEMBER OF u.missionsAcomplished) AND u.username = :username WHERE u.id IS NULL ORDER BY m.level ASC LIMIT 3")
    Optional<Mission[]> getSuggestedMissionsForUser(@Param("username") String username);

    @Query("SELECT m FROM Mission m LEFT JOIN User u ON (m MEMBER OF u.missionsAccepted " +
            "OR m MEMBER OF u.missionsAcomplished) AND u.username = :username WHERE u.id IS NULL")
    Optional<Mission[]> getOtherMissionsForUser(@Param("username") String username);

    @Query("SELECT m FROM Mission m JOIN User u ON m MEMBER OF u.missionsAcomplished WHERE u.username = :username")
    Optional<Mission[]> getMissionsAcomplishedByUser(@Param("username") String username);

}
