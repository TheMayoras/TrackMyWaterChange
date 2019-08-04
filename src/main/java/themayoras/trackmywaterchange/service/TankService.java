package themayoras.trackmywaterchange.service;

import java.util.List;

import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.entity.WaterChange;

public interface TankService {
    void saveOrUpdate (Tank tank);

    List<Tank> getTanksForUserWithNameLike(User user, String namePattern);

    List<WaterChange> getAllWaterChanges(Tank tank);

    List<WaterChange> getWaterChangeByMinAmount(double minAmount);

    List<WaterChange> getWaterChangeByMaxAmount(double maxAmount);

    List<WaterChange> getWaterChangeByMinMaxAmounts(double minAmount, double maxAmount);

    boolean deleteWaterChange(int waterChangeId);

    boolean deleteWaterChange(WaterChange waterChange);

    boolean createWaterChange(Tank tank, WaterChange waterChange);

    boolean updateWaterChange(WaterChange waterChange);

    void deleteTank(int id);

    Tank getTank(int id);

    Tank getTank(Tank tank);
    
    WaterChange getWaterChange(int id);
    
}
