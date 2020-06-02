package themayoras.trackmywaterchange;

import org.junit.Assert;
import org.junit.Test;
import themayoras.trackmywaterchange.entity.Role;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.rest.dto.UserDto;
import themayoras.trackmywaterchange.rest.dto.UserRegistrationDto;
import themayoras.trackmywaterchange.service.UserConversionServiceImpl;

import java.util.HashSet;
import java.util.Set;

public class UserConversionServiceTest {
    @Test
    public void testUserRegistrationDtoToUser() {
        UserRegistrationDto registrationUser = new UserRegistrationDto();
        registrationUser.setPassword("testpassword");
        registrationUser.setEmail(null);
        registrationUser.setFirstName("test");
        registrationUser.setLastName("case");
        registrationUser.setUsername("testcase");
        testUserEqual(registrationUser);

        registrationUser = new UserRegistrationDto();
        registrationUser.setPassword("testpassword");
        registrationUser.setEmail("");
        registrationUser.setFirstName("test");
        registrationUser.setLastName("case");
        registrationUser.setUsername("testcase");
        testUserEqual(registrationUser);

        registrationUser = new UserRegistrationDto();
        registrationUser.setPassword("testpassword");
        registrationUser.setEmail("testemail");
        registrationUser.setFirstName("test");
        registrationUser.setLastName("case");
        registrationUser.setUsername("testcase");
        testUserEqual(registrationUser);
    }

    @Test
    public void testUserToUserDto() {
        User user = new User();
        user.setId(10);
        user.setUsername("testcase");
        user.setFirstName("test");
        user.setLastName("case");
        user.setEmail(null);
        user.setPassword("password");
        user.setRoles(getRoles("ROLE_USER", "ROLE_ADMIN"));
        testUserEqual(user);

        user = new User();
        user.setId(10);
        user.setUsername("testcase");
        user.setFirstName("test");
        user.setLastName("case");
        user.setEmail("notnull");
        user.setPassword("password");
        user.setRoles(getRoles("ROLE_USER", "ROLE_ADMIN"));
        testUserEqual(user);
    }

    private void testUserEqual(UserRegistrationDto registrationUser) {
        User generatedUser = (new UserConversionServiceImpl()).userRegistrationDtoToUser(registrationUser);
        Assert.assertEquals(registrationUser.getPassword(), generatedUser.getPassword());
        Assert.assertEquals(registrationUser.getEmail(), generatedUser.getEmail());
        Assert.assertEquals(registrationUser.getFirstName(), generatedUser.getFirstName());
        Assert.assertEquals(registrationUser.getLastName(), generatedUser.getLastName());
        Assert.assertEquals(registrationUser.getUsername(), generatedUser.getUsername());
        Assert.assertEquals(1, generatedUser.getRoles().size());
        Assert.assertTrue(generatedUser.getRoles().stream().allMatch(role -> role.getRole().equals("ROLE_USER")));
    }

    private void testUserEqual(User user) {
        UserDto userDto = (new UserConversionServiceImpl()).userToUserDto(user);
        Assert.assertEquals(user.getUsername(), userDto.getUsername());
        Assert.assertEquals(user.getFirstName(), userDto.getFirstName());
        Assert.assertEquals(user.getLastName(), userDto.getLastName());
        Assert.assertEquals(user.getId(), userDto.getId());
        Assert.assertEquals(user.getEmail(), userDto.getEmail());
        if (user.getTanks() == null) {
            Assert.assertNull(userDto.getTankIds());
        } else {
            Assert.assertEquals(user.getTanks().size(), userDto.getTankIds().size());
            for (int i = 0; i < user.getTanks().size(); i++) {
                Assert.assertEquals(user.getTanks().get(i).getId(), (int) userDto.getTankIds().get(i));
            }
        }
    }

    private Set<Role> getRoles(String... roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(new Role(role));
        }

        return roleSet;
    }

}
