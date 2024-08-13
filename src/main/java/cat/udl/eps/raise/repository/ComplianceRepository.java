package cat.udl.eps.raise.repository;

import cat.udl.eps.raise.domain.Compliance;
import cat.udl.eps.raise.projection.ComplianceDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ComplianceRepository extends
        CrudRepository<Compliance, Long>,
        PagingAndSortingRepository<Compliance, Long> {

  /* Interface provides automatically, as defined in
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
   * and
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
   * the methods: count, delete, deleteAll, deleteById, existsById, findAll, findAllById, findById, save, saveAll,...
   *
   * Additional methods like findByUsernameContaining can be defined following:
   * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
   */

    Optional<Compliance> findByInstanceId(@Param("id") Long id);

    boolean existsByInstanceIdAndPrincipleId
            (@Param("instance_id") Long instanceId, @Param("fair_principle_id") Long fairPrincipleId);


    @Query("SELECT new cat.udl.eps.raise.projection.ComplianceDTO(" +
            "p.id, p.principle.id, p.author.id, p.author.username, p.instance.id, p.instance.repository.id," +
            " p.instance.repository.name, p.instance.dataset.id, p.instance.dataset.name, p.principle.namePrefix, p.principle.name, " +
            " p.principle.category, p.verificationDate) FROM Compliance p")
    ComplianceDTO[] retrieveAllComplianceDTO();

    @Query("SELECT new cat.udl.eps.raise.projection.ComplianceDTO(p.id, p.principle.id, p.author.id, p.author.username, p.instance.id, p.instance.repository.id," +
            "    p.instance.repository.name, p.instance.dataset.id, p.instance.dataset.name, p.principle.namePrefix, p.principle.name," +
            "    p.principle.category, p.verificationDate) FROM Compliance p LEFT JOIN Validation v ON p.id = v.compliance.id AND v.validator.id = :userId WHERE v.id is NULL")
    ComplianceDTO[] retrieveComplianceDTONotEvaluatedByUser(@Param("userId") Long userId);

    @Query("SELECT new cat.udl.eps.raise.projection.ComplianceDTO(p.id, p.principle.id, p.author.id, p.author.username, p.instance.id, p.instance.repository.id," +
            "    p.instance.repository.name, p.instance.dataset.id, p.instance.dataset.name, p.principle.namePrefix, p.principle.name," +
            "    p.principle.category, p.verificationDate) FROM Compliance p LEFT JOIN Validation v ON p.id = v.compliance.id WHERE v.id is NULL")
    ComplianceDTO[] retrieveComplianceDTONotEvaluated();
}
