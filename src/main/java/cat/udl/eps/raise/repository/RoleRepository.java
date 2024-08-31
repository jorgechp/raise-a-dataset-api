package cat.udl.eps.raise.repository;

import cat.udl.eps.raise.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Long>{

}
