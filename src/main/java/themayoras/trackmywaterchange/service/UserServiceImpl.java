package themayoras.trackmywaterchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import themayoras.trackmywaterchange.dao.UserDao;
import themayoras.trackmywaterchange.entity.Role;
import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.entity.UserCommand;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TankService tankService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User findById(int id) {
        return userDao.getById(id);
    }

    @Override
    public User findByUsername(String username) {
        try {
            return userDao.findByUsername(username).get(0);
        } catch (NullPointerException npe) {
            return null;
        } catch (IndexOutOfBoundsException iobe) {
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        return userDao.removeById(id).size() == 0 ? false : true;
    }

    @Override
    public User saveOrUpdate(User user) {
        return userDao.save(user);
    }

    public User registerUser(UserCommand user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.getRoleByName("USER");

        User newUser = user.getUser();

        newUser.addRole(userRole);

        return userDao.save(newUser);
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

    @Override
    public void addTankToUser(int id, Tank tank) {
        User user = userDao.findById(id).get();

        user.addTank(tank);
        tank.setUser(user);
        saveOrUpdate(user);
    }

}
