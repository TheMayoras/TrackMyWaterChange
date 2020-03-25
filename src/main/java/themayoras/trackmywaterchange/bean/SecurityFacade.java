package themayoras.trackmywaterchange.bean;

import themayoras.trackmywaterchange.entity.User;

import javax.servlet.http.HttpSession;

public interface SecurityFacade {
    User getCurrentUser();

    HttpSession getCurrentSession(boolean create);
}
