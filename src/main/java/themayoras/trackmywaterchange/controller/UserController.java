package themayoras.trackmywaterchange.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	// userDao
	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String getRegistrationPage(Model modelPage) {
		modelPage.addAttribute("newUser", new User());

		return "registration.html";
	}

	@PostMapping("/register")
	public String processRegistration(@ModelAttribute("newUser") @Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "user-forms/registration.html";
		}

		// save the user
		if (userService.findAllByUsername(user.getUsername()).size() > 0) {
			model.addAttribute("registrationError", "username taken");
			return "user-forms/registration.html";
		}

		userService.saveOrUpdate(user);

		return "redirect:/user/login";
	}

	@GetMapping("/login")
	public String getLoginPage(Model modelPage) {
		// add blank user to the model
		modelPage.addAttribute("user", new User());

		return "user-forms/login.html";
	}

	@PostMapping("/login")
	public String loginFromLoginForm(@ModelAttribute("user") @Valid User user, BindingResult result, Model model,
			HttpServletResponse response) {
		User loggedInUser = null;
		try {
			loggedInUser = userService.findAllByUsername(user.getUsername()).get(0);
		} catch (NullPointerException npe) {
			model.addAttribute("loginError", "username not found");
			return "user-forms/login.html";
		}

		Cookie cookie = new Cookie("userId", Integer.toString(loggedInUser.getId())); // expires when browser is closed
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return "redirect:/home";
	}
	
	@GetMapping("/login-expired")
	public String getLoginExpired(Model model) {
		
		return "user-forms/login-expired.html";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
		
		binder.registerCustomEditor(String.class, trimmer);
	}
}
