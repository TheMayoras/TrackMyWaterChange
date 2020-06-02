package themayoras.trackmywaterchange.rest.dto;

import java.util.List;

public class UserDto {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<Integer> tankIds;

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
        this.email = email;
    }

    public List<Integer> getTankIds() {
        return tankIds;
    }

    public void setTankIds(List<Integer> tankIds) {
        this.tankIds = tankIds;
    }
}
