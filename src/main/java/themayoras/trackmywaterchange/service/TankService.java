package themayoras.trackmywaterchange.service;

import java.util.List;

import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.WaterChange;

public interface TankService {
	List<WaterChange> getAllWaterChanges(Tank tank);
	
	List<WaterChange> getWaterChangeByMinAmount(double minAmount);
	
	List<WaterChange> getWaterChangeByMaxAmount(double maxAmount);
	
	List<WaterChange> getWaterChangeByMinMaxAmounts(double minAmount, double maxAmount);

	boolean deleteWaterChange(int waterChangeId);
	
	boolean deleteWaterChange(WaterChange waterChange);

	boolean createWaterChange(Tank tank, WaterChange waterChange);
	
	boolean updateWaterChange(WaterChange waterChange);
}
