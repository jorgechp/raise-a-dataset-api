package cat.udl.eps.raise.repository;

import cat.udl.eps.raise.domain.Verification;
import cat.udl.eps.raise.projection.VerificationDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface VerificationRepository extends
        CrudRepository<Verification, Long>,
        PagingAndSortingRepository<Verification, Long> {

  /* Interface provides automatically, as defined in
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
   * and
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
   * the methods: count, delete, deleteAll, deleteById, existsById, findAll, findAllById, findById, save, saveAll,...
   *
   * Additional methods like findByUsernameContaining can be defined following:
   * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
   */

    Optional<Verification> findByInstanceId(@Param("id") Long id);

    boolean existsByInstanceIdAndPrincipleId
            (@Param("instance_id") Long instanceId, @Param("fair_principle_id") Long fairPrincipleId);


    @Query("SELECT new cat.udl.eps.raise.projection.VerificationDTO(" +
            "p.id, p.principle.id, p.author.id, p.author.username, p.instance.id, p.instance.repository.id," +
            " p.instance.repository.name, p.instance.dataset.id, p.instance.dataset.name, p.principle.namePrefix, p.principle.name, " +
            " p.principle.category, p.verificationDate) FROM Verification p")
    VerificationDTO[] retrieveAllVerificationInstanceDTO();
}
