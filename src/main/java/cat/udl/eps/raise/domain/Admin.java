package cat.udl.eps.raise.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Admin extends User {

    @Override
    @JsonValue(value = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
    }

}