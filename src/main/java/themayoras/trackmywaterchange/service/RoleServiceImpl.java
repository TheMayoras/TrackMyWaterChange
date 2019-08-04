package themayoras.trackmywaterchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import themayoras.trackmywaterchange.dao.RoleDao;
import themayoras.trackmywaterchange.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getRoleByName(String role) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        List<Role> roles = roleDao.findByRole(role);

        if (roles == null || roles.isEmpty()) {
            return null;
        }

        return roles.get(0);
    }

}
