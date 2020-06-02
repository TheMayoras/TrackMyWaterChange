package themayoras.trackmywaterchange.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.WaterChange;
import themayoras.trackmywaterchange.rest.dto.TankDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TankConversionService {
    @Mapping(source = "waterChanges", target = "waterChangeIds", qualifiedByName = "waterChangeToWaterChangeId")
    TankDto tankToTankDto(Tank tank);

    @Named("waterChangeToWaterChangeId")
    default List<Integer> waterChangeToWaterChangeId(List<WaterChange> waterChanges) {
        if (waterChanges == null) {
            return null;
        }

        return waterChanges.stream()
                           .map(WaterChange::getId)
                           .collect(Collectors.toList());

    }
}
