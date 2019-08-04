package themayoras.trackmywaterchange.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import themayoras.trackmywaterchange.entity.WaterChange;

public interface WaterChangeDao extends JpaRepository<WaterChange, Integer> {

	List<WaterChange> findByAmountGreaterThanEqual(double minAmount);

	List<WaterChange> findByAmountLessThanEqual(double maxAmount);

	List<WaterChange> findByAmountBetween(double minAmount, double maxAmount);

	int removeById(int waterChangeId);

}
