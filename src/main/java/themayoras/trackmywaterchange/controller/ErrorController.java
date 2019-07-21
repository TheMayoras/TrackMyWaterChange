package themayoras.trackmywaterchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping("/400")
	public String get400ErrorPage() {
		
		return "redirect:/user/login-expired";
	}
}
