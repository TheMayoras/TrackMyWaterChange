package themayoras.trackmywaterchange.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.rest.dto.UserDto;
import themayoras.trackmywaterchange.rest.dto.UserRegistrationDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserConversionService {
    @Mapping(source = "tanks", target = "tankIds", qualifiedByName = "tankToTankId")
    UserDto userToUserDto(User user);

    @Mapping(target = "roles",
             expression = "java(new java.util.HashSet<themayoras.trackmywaterchange.entity.Role>(java.util" +
                                  ".Collections.singleton(" +
                                  "new themayoras.trackmywaterchange.entity.Role(\"ROLE_USER\"))))")
    User userRegistrationDtoToUser(UserRegistrationDto registration);

    @Named("tankToTankId")
    default List<Integer> tankToTankId(List<Tank> tanks) {
        if (tanks == null) {
            return null;
        }
        return tanks.stream()
                    .map(Tank::getId)
                    .collect(Collectors.toList());
    }
}
