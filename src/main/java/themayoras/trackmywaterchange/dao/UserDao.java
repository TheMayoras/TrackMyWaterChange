package themayoras.trackmywaterchange.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import themayoras.trackmywaterchange.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	public User getById(int id);

	public List<User> removeById(int id);
	
	public List<User> findByUsername(String username);
	
	public List<User> findByUsernameContaining(String username);

}
