package themayoras.trackmywaterchange.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.service.UserService;

@Component
public class SecurityFacadeImpl implements SecurityFacade {

    @Autowired
    private UserService userService;

    @Override
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        
        System.err.println("Principal: " + principal);
        return userService.findByUsername(principal.getUsername());
    }

}
