package themayoras.trackmywaterchange.service;

import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.TankDetails;

public interface TankDetailsService {
    TankDetails getTankDetails(Tank tank);
    TankDetails getTankDetails(int tankId);
}
