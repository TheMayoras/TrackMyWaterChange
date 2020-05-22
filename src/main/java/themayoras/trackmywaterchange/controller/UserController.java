package themayoras.trackmywaterchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import themayoras.trackmywaterchange.bean.SecurityFacade;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.entity.UserCommand;
import themayoras.trackmywaterchange.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    // userDao
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityFacade securityFacade;

    @GetMapping("/login")
    public String getLoginPage(HttpServletRequest request) {
        return "user-forms/login.html";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model modelPage) {
        modelPage.addAttribute("newUser", new UserCommand());

        return "user-forms/registration.html";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("newUser") @Valid UserCommand user, BindingResult result,
                                      Model model, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "user-forms/registration.html";
        }

        // save the user
        if (userService.findAllByUsername(user.getUsername()).size() > 0) {
            model.addAttribute("registrationError", "username taken");
            return "user-forms/registration.html";
        }
        String password = user.getPassword();

        User registered = userService.registerUser(user);
        try {
            request.login(registered.getUsername(), password);
            return "redirect:/home";
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:/user/login";
    }

    @GetMapping("/login-expired")
    public String getLoginExpired(Model model) {

        return "user-forms/login-expired.html";
    }

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "user-forms/access-denied.html";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor trimmer = new StringTrimmerEditor(false);

        binder.registerCustomEditor(String.class, trimmer);
    }
}
