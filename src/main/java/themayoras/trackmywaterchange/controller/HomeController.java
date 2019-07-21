package themayoras.trackmywaterchange.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private UserService userService;
	
	 @GetMapping("")
	 public String getHome(@CookieValue("userId") Cookie userIdCookie, Model model) {
		 int userId = Integer.valueOf(userIdCookie.getValue());

		 User user = userService.findById(userId);
		 
		 if (user == null) {
			 return "redirect:/user/login-required";
		 }
		 
		 model.addAttribute("user", user);
		 
		 System.err.println("User added to model: " + user);
		 
		 return "home-page.html";
	 }
	 
	 // add a tank
	 @RequestMapping(value="add-tank")
	 public String addTankToUser(@ModelAttribute("user") User user, @ModelAttribute("test") String test, BindingResult result) {	
		 System.err.println("Adding Tank for User: " + user);
		 
		 return "add-tank.html"; 
	 }
	 
	 // redirect to get the water changes for the tank
	 @RequestMapping(value="", params= {"waterChangeId"})
	 public String getTanksWaterChanges(@ModelAttribute("user") User user, BindingResult result) {
		 
		 return "/home";
	 }
}
