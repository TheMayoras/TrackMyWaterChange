package themayoras.trackmywaterchange.rest.dto;

import themayoras.trackmywaterchange.entity.validation.Email;
import themayoras.trackmywaterchange.entity.validation.Username;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegistrationDto {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
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
        if (email != null && email.trim().length() == 0) {
            email = null;
        }
        this.email = email;
    }
}
