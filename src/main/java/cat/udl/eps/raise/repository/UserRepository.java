package cat.udl.eps.raise.repository;

import cat.udl.eps.raise.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {

  /* Interface provides automatically, as defined in
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
   * and
   * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
   * the methods: count, delete, deleteAll, deleteById, existsById, findAll, findAllById, findById, save, saveAll,...
   *
   * Additional methods like findByUsernameContaining can be defined following:
   * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
   */


  Optional<User> findByUsername(@Param("text") String text);

  boolean existsByUsername(@Param("text") String text);

  @Query("SELECT u FROM User u ORDER BY u.score DESC")
  List<User> findAllUserOrderedByScoreDesc();

  @Query("SELECT COUNT(u) FROM User u WHERE u.score > (SELECT u2.score FROM User u2 WHERE u2.id = :userId)")
  long countUsersWithHigherScore(@Param("userId") Long userId);

  @Query("SELECT score FROM User WHERE id = :userId")
  long getUserScore(@Param("userId") Long userId);

  @Query("SELECT COUNT(u) FROM User u")
  long countAll();

}
