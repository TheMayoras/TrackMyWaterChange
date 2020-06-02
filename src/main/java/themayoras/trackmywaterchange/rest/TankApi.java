package themayoras.trackmywaterchange.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import themayoras.trackmywaterchange.bean.SecurityFacade;
import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.rest.dto.TankDto;
import themayoras.trackmywaterchange.service.TankConversionService;
import themayoras.trackmywaterchange.service.TankService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tank")
public class TankApi {
    @Autowired
    private TankService tankService;

    @Autowired
    private SecurityFacade securityFacade;

    @Autowired
    private TankConversionService conversionService;

    @GetMapping("/list")
    public List<TankDto> getTanks() {
        User user = securityFacade.getCurrentUser();
        List<Tank> tanks = user.getTanks();
        return tanks.stream()
                    .map(conversionService::tankToTankDto)
                    .collect(Collectors.toList());
    }
}
