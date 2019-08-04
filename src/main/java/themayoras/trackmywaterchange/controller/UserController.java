package themayoras.trackmywaterchange.controller;

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

import themayoras.trackmywaterchange.entity.UserCommand;
import themayoras.trackmywaterchange.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	// userDao
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String getLoginPage() {
	    return "user-forms/login.html";
	}

	@GetMapping("/register")
	public String getRegistrationPage(Model modelPage) {
		modelPage.addAttribute("newUser", new UserCommand());

		return "user-forms/registration.html";
	}

	@PostMapping("/register")
	public String processRegistration(@ModelAttribute("newUser") @Valid UserCommand user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "user-forms/registration.html";
		}

		// save the user
		if (userService.findAllByUsername(user.getUsername()).size() > 0) {
			model.addAttribute("registrationError", "username taken");
			return "user-forms/registration.html";
		}

		userService.registerUser(user);

		return "redirect:/user/login";
	}

	@GetMapping("/login-expired")
	public String getLoginExpired(Model model) {

		return "user-forms/login-expired.html";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor trimmer = new StringTrimmerEditor(false);

		binder.registerCustomEditor(String.class, trimmer);
	}
}
