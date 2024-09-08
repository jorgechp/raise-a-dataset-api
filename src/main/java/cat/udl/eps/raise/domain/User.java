package cat.udl.eps.raise.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Entity
@Table(name = "AppUser") //Avoid collision with system table User
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends UriEntity<Long> implements UserDetails {

	public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User() {
		this.compliances = new HashSet<>();
		this.datasetsMaintained = new HashSet<>();
		this.missionsAccepted = new HashSet<>();
		this.missionsAcomplished = new HashSet<>();
	}

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank
	@Length(min = 8, max = 256)
	private String password;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private boolean passwordReset;

	@ManyToMany
	private Set<Dataset> datasetsMaintained;

	@OneToMany
	private Set<Dataset> datasetsRescued;

	@ManyToMany
	private Set<Compliance> compliances;

	@ManyToMany
	private Set<Mission> missionsAccepted;

	@ManyToMany
	private Set<Mission> missionsAcomplished;

	@OneToMany
	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JoinColumn(referencedColumnName = "id")
	private Set<Validation> validations;

	public void encodePassword() {
		this.password = passwordEncoder.encode(this.password);
	}

	@Override
	public String getUsername() { return username; }

	@Override
	public Long getId() { return id; }

	public void setUsername(String username) { this.username = username; }

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	@Override
	@JsonValue(value = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", passwordReset=" + passwordReset +
				'}';
	}
}
