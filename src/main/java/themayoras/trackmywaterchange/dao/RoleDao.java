package themayoras.trackmywaterchange.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import themayoras.trackmywaterchange.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
    
    public List<Role> findByRoleLike(String role);
    
    public List<Role> findByRole(String role);
}
