package themayoras.trackmywaterchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import themayoras.trackmywaterchange.dao.TankDao;
import themayoras.trackmywaterchange.dao.WaterChangeDao;
import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.entity.WaterChange;

@Service
public class TankServiceImpl implements TankService {

    @Autowired
    private TankDao tankDao;

    @Autowired
    WaterChangeDao waterChangeDao;

    @Override
    public List<WaterChange> getAllWaterChanges(Tank tank) {
        // return null if not found
        return tankDao.findById(tank.getId()).map((Tank s) -> s.getWaterChanges()).orElseGet(null);
    }

    @Override
    public List<WaterChange> getWaterChangeByMinAmount(double minAmount) {
        return waterChangeDao.findByAmountGreaterThanEqual(minAmount);
    }

    @Override
    public List<WaterChange> getWaterChangeByMaxAmount(double maxAmount) {
        return waterChangeDao.findByAmountLessThanEqual(maxAmount);
    }

    @Override
    public List<WaterChange> getWaterChangeByMinMaxAmounts(double minAmount, double maxAmount) {
        return waterChangeDao.findByAmountBetween(minAmount, maxAmount);
    }

    @Override
    public boolean deleteWaterChange(int waterChangeId) {
        return waterChangeDao.removeById(waterChangeId).size() == 0 ? false : true;
    }

    @Override
    public boolean deleteWaterChange(WaterChange waterChange) {
        return deleteWaterChange(waterChange.getId());
    }

    @Override
    public boolean createWaterChange(Tank tank, WaterChange waterChange) {
        tank.addWaterChange(waterChange);
        return tankDao.save(tank).getId() > 0;
    }

    @Override
    public boolean updateWaterChange(WaterChange waterChange) {
        return waterChangeDao.save(waterChange).getId() > 0;
    }

    @Override
    public void saveOrUpdate(Tank tank) {
        tankDao.save(tank);
    }

    @Override
    public List<Tank> getTanksForUserWithNameLike(User user, String namePattern) {
        return tankDao.findByNameLikeForUser(user.getId(), namePattern);
    }

    @Override
    public void deleteTank(int id) {
        System.err.println("Deleting Tank with ID: " + id);
        tankDao.deleteById(id);
    }

    @Override
    public Tank getTank(int id) {
        return tankDao.findById(id).get();
    }

    @Override
    public Tank getTank(Tank tank) {
        return getTank(tank.getId());
    }

}
