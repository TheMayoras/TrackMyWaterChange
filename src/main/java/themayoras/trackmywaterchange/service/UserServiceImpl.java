package themayoras.trackmywaterchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import themayoras.trackmywaterchange.dao.UserDao;
import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findById(int id) {
		return userDao.getById(id);
	}

	@Override
	public boolean deleteById(int id) {
		 return userDao.removeById(id).size() == 0 ? false : true;
	}

	@Override
	public void saveOrUpdate(User user) {
		userDao.save(user);
	}

	@Override
	public List<User> findAllByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public List<Tank> getAllTanksByUserId(int id) {
		return userDao.getById(id).getTanks();
	}

	@Override
	public List<Tank> getAllTanks(User user) {
		return getAllTanksByUserId(user.getId());
	}

}
