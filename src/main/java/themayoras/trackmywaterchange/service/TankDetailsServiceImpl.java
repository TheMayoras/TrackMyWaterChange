package themayoras.trackmywaterchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.TankDetails;
import themayoras.trackmywaterchange.entity.UnitConverter;
import themayoras.trackmywaterchange.entity.WaterChange;

import java.util.Date;
import java.util.List;

@Service
public class TankDetailsServiceImpl implements TankDetailsService {
    private static final long MS_TO_DAYS = 1000 * 3600 * 24;

    private TankService tankService;

    @Autowired
    public TankDetailsServiceImpl(TankService tankService) {
        this.tankService = tankService;
    }

    @Override
    public TankDetails getTankDetails(int tankId) {
        Tank tank = tankService.getTank(tankId);
        return getTankDetails(tank);
    }

    @Override
    public TankDetails getTankDetails(Tank tank) {
        List<WaterChange> waterChanges = tank.getWaterChanges();

        if (waterChanges == null) {
            return new TankDetails(
                    tank,
                    -1,
                    new Date(),
                    new Date(),
                    -1,
                    tank.getUnits()
            );

        }

        double difference = 0;
        if (waterChanges != null && waterChanges.size() > 1) {
            waterChanges.sort((c1, c2) -> c1.getDate().compareTo(c2.getDate()));
            for (int i = 1; i < waterChanges.size(); i++) {
                WaterChange waterChange = waterChanges.get(i);
                // get the difference in ms
                double diff = waterChange.getDate().getTime() - waterChanges.get(i - 1).getDate().getTime();
                difference += diff / MS_TO_DAYS;
            }
            difference /= ((double) waterChanges.size() - 1);
        }

        if (waterChanges.size() > 0) {
            return new TankDetails(
                    tank,
                    difference,
                    waterChanges.get(waterChanges.size() - 1).getDate(),
                    waterChanges.get(0).getDate(),
                    averageQuantity(waterChanges),
                    tank.getUnits()
            );
        }

        return new TankDetails(
                tank,
                difference,
                null,
                null,
                0,
                tank.getUnits()
        );

    }

    private double averageQuantity(List<WaterChange> waterChanges) {
        double output = 0;

        for (WaterChange waterChange : waterChanges) {
            UnitConverter converter = new UnitConverter(waterChange.getUnits(), waterChange.getAmount(),
                                                        waterChange.getTank().getUnits(),
                                                        waterChange.getTank().getSize()
            );
            output += converter.convertTo(waterChange.getTank().getUnits());
        }

        return output / (double) waterChanges.size();
    }

}
