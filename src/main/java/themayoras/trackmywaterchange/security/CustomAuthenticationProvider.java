package themayoras.trackmywaterchange.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.service.UserService;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(UserDetailsService userDetails) {
        System.err.println("Setting User Details Service");
        super.setUserDetailsService(userDetails);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.findByUsername(authentication.getName());

        String enterredPassword = authentication.getCredentials().toString();

        try {
            System.err.println("Authenticating User: " + user);
            System.err.println("\tUser Password: " + user.getPassword());
            System.err.println("\t\tEntered Password: " + enterredPassword);
            System.err.println("\t\tEncoded Password: " + passwordEncoder.encode(enterredPassword));
        } catch (Exception e) {

        }
        if (user == null || !passwordMatch(user, enterredPassword)) {
            throw new BadCredentialsException("Invalid Username or Password");
        }

        Authentication auth = super.authenticate(authentication);

        return new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), auth.getAuthorities());
    }

    private boolean passwordMatch(User user, String enterredPassword) {
        try {
            return passwordEncoder.encode(enterredPassword).equals(user.getPassword());
        } catch (NullPointerException npe) {
            return false;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
