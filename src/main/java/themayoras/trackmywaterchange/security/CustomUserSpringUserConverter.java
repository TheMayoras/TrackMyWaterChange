package themayoras.trackmywaterchange.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import themayoras.trackmywaterchange.entity.Role;
import themayoras.trackmywaterchange.entity.User;

public abstract class CustomUserSpringUserConverter {

    protected CustomUserSpringUserConverter() {

    }

    public static org.springframework.security.core.userdetails.User getSpringUser(User user) {
        String    username = user.getUsername();
        String    password = user.getPassword();
        Set<Role> roles    = user.getRoles();

        return new org.springframework.security.core.userdetails.User(username, password, true, true, true, true,
                getAuthorities(roles));
    }

    /**
     * This method is optional. You can just create a
     * Collection<@{GrantedAuthority}> inside the loadUserByUserName method
     * Return a list of Users granted authorities
     * 
     * @param roles Collection<@{Role}>
     * @return Collection<@{GrantedAuthority}>
     */
    private static Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {

        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();

        // Add the @{User} roles to the grantedAuthority Collection and return the
        // Collection
        for (Role role : roles) {
            grantedAuthority.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return grantedAuthority;

    }
}
