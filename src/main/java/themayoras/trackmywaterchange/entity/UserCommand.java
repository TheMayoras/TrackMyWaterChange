package themayoras.trackmywaterchange.entity;

import themayoras.trackmywaterchange.entity.validation.Email;
import themayoras.trackmywaterchange.entity.validation.PasswordConfirmation;
import themayoras.trackmywaterchange.entity.validation.Username;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PasswordConfirmation(message = "passwords must match")
public class UserCommand {

    // userID
    private final int id = 0;

    // username
    @Username
    private String username;

    // password (NOTE: encrypted with BCryptPasswordEncoder
    @Pattern(regexp = ".{8}.*", message = "must be at least 8 characters") // must be at least 8 characters
    private String password;

    @NotBlank(message = "is required")
    private String confirmationPassword;

    // first name
    @NotNull(message = "is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name")
    private String firstName;

    // last name
    @NotNull(message = "is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name")
    private String lastName;

    // email (optional)
    @Email(message = "invalid email")
    private String email;

    public UserCommand(String username, @NotBlank(message = "required") String password,
                       @NotBlank(message = "is required") String confirmationPassword,
                       @NotNull(message = "is required") @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name") String firstName,
                       @NotNull(message = "is required") @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid name") String lastName,
                       String email) {
        this.username = username;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserCommand() {
    }

    public String getUsername() {
        return this.username;
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

    public String getConfirmationPassword() {
        return this.confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    @Override
    public String toString() {
        return "UserRegistrationUser [id=" + this.id + ", username=" + this.username + ", password=" + this.password
                + ", confirmationPassword=" + this.confirmationPassword + ", firstName=" + this.firstName
                + ", lastName=" + this.lastName + ", email=" + this.email + "]";
    }

}
