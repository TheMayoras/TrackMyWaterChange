package themayoras.trackmywaterchange.bean;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.service.UserService;

import javax.servlet.http.HttpSession;

@Component
public class SecurityFacadeImpl implements SecurityFacade {

    @Autowired
    private UserService userService;

    @Override
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        System.err.println("Principal: " + principal);
        return userService.findByUsername(principal.getUsername());
    }

    @Override
    public HttpSession getCurrentSession(boolean create) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getRequest().getSession(create);
    }

}
