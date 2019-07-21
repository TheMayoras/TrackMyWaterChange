package themayoras.trackmywaterchange.service;

import java.util.List;

import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.User;

public interface UserService {

	User findById(int id);

	boolean deleteById(int id);

	void saveOrUpdate(User user);

	List<User> findAllByUsername(String username);

	List<Tank> getAllTanks(User user);

	List<Tank> getAllTanksByUserId(int id);
	
}
