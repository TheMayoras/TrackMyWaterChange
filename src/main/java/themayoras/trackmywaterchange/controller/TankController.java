package themayoras.trackmywaterchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import themayoras.trackmywaterchange.service.TankService;

@Controller
@RequestMapping("/tank")
public class TankController {
	
	@Autowired
	private TankService tankService;

}
