package themayoras.trackmywaterchange.service;

import java.util.List;

import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.entity.UserCommand;

public interface UserService {
	
	User findById(int id);
	
	User findByUsername(String username);

	boolean deleteById(int id);

	User saveOrUpdate(User user);
	
	User registerUser(UserCommand user);

	List<User> findAllByUsername(String username);

	List<Tank> getAllTanks(User user);

	List<Tank> getAllTanksByUserId(int id);

	void addTankToUser(int id, Tank tank);
}
