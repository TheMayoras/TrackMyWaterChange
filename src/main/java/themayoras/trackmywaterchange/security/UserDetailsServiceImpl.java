package themayoras.trackmywaterchange.security;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.service.UserService;

@Resource
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.err.println("User Details: " + username);
        try {
            User user = userService.findByUsername(username);

            System.err.println("User Found: " + user);
            return CustomUserSpringUserConverter.getSpringUser(user);

        } catch (Exception e) {
            System.err.println("Error finding User: " + e.getMessage());
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
    }

}
