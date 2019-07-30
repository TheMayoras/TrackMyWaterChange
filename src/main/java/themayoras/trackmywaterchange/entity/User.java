package themayoras.trackmywaterchange.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import themayoras.trackmywaterchange.entity.validation.Email;
import themayoras.trackmywaterchange.entity.validation.Username;

@Entity
@Table(name = "users")
public class User {

    // userID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    // username
    @Username
    @Column(name = "username")
    private String username;

    // password (NOTE: encrypted with BCryptPasswordEncoder
    @NotNull(message = "required")
    @NotEmpty(message = "required")
    @Column(name = "password")
    private String password;

    // first name
    @NotNull(message = "is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name")
    @Column(name = "first_name")
    private String firstName;

    // last name
    @NotNull(message = "is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name")
    @Column(name = "last_name")
    private String lastName;

    // email (optional)
    @Email(message = "invalid email")
    @Column(name = "email")
    private String email;

    // list of the user's tanks
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "user")
    private List<Tank> tanks;

    // the user's roles
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "users_user_roles", joinColumns = { @JoinColumn(name = "id_user") }, inverseJoinColumns = {
	    @JoinColumn(name = "id_user_role") })
    private Set<Role> roles;

    public void addTank(Tank tank) {
	if (tanks == null) {
	    tanks = new ArrayList<>();
	}

	tanks.add(tank);
    }

    public void addRole(Role role) {
	if (roles == null) {
	    roles = new HashSet<>();
	}

	roles.add(role);
    }

    public void remove(Role role) {
	if (roles != null) {
	    roles.remove(role);
	}
    }

    public User() {

    }

    public User(String username) {
	this.username = username;
    }

    public User(String username, String email) {
	this.username = username;
	this.email = email;
    }

    public User(String username,
	    @NotNull(message = "is required") @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name") String firstName,
	    @NotNull(message = "is required") @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name") String lastName) {
	this.username = username;
	this.firstName = firstName;
	this.lastName = lastName;
    }

    public User(String username,
	    @NotNull(message = "is required") @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name") String firstName,
	    @NotNull(message = "is required") @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name") String lastName,
	    @Email(message = "invalid email") String email) {
	this.username = username;
	this.firstName = firstName;
	this.lastName = lastName;
	this.setEmail(email);
    }

    public User(String username,
	    @NotNull(message = "is required") @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name") String firstName,
	    @NotNull(message = "is required") @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name") String lastName,
	    @Email(message = "invalid email") String email, List<Tank> tanks) {
	this.username = username;
	this.firstName = firstName;
	this.lastName = lastName;
	this.setEmail(email);
	this.tanks = tanks;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	if (email.trim().length() == 0) {
	    email = null;
	}

	this.email = email;
    }

    public List<Tank> getTanks() {
	return tanks;
    }

    public void setTanks(List<Tank> tanks) {
	this.tanks = tanks;
    }

    public Set<Role> getRoles() {
	return this.roles;
    }

    public void setRoles(Set<Role> roles) {
	this.roles = roles;
    }

    @Override
    public String toString() {
	return "User [id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", firstName="
		+ this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + ", tanks=" + this.tanks
		+ ", roles=" + this.roles + "]";
    }

    public void remove(Tank tank) {
	if (tanks != null) {
	    tanks.remove(tank);
	    tank.setUser(null);
	}
    }

}
