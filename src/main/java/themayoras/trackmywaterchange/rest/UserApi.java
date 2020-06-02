package themayoras.trackmywaterchange.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import themayoras.trackmywaterchange.bean.SecurityFacade;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.rest.dto.UserDto;
import themayoras.trackmywaterchange.service.UserConversionService;
import themayoras.trackmywaterchange.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    private SecurityFacade securityFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private UserConversionService userConverter;

    @GetMapping("")
    public UserDto getCurrentUser() {
        User user = securityFacade.getCurrentUser();
        return userConverter.userToUserDto(user);
    }
}
