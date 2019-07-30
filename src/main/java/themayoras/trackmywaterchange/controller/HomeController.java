package themayoras.trackmywaterchange.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import themayoras.trackmywaterchange.bean.SecurityFacade;
import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.User;
import themayoras.trackmywaterchange.service.CookieService;
import themayoras.trackmywaterchange.service.TankService;
import themayoras.trackmywaterchange.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
  @Autowired
  private UserService userService;

  @Autowired
  private TankService tankService;

  @Autowired
  CookieService cookieService;

  @Autowired
  private SecurityFacade securityFacade;

  @GetMapping("")
  public String getHome(Model model) {

    User user = securityFacade.getCurrentUser();
    
    if (user == null) {
      return "redirect:/user/login-required";
    }

    model.addAttribute("user", user);

    System.err.println("User added to model: " + user);

    return "home-page.html";
  }

  // add a tank
  @GetMapping(value = "add-tank")
  public String addTankToUser(Model model) {


    User user = securityFacade.getCurrentUser();
    Tank tank = new Tank();

    System.err.println("User Added to add-tank: " + user);
    System.err.println("Tank Added to add-tank: " + tank);

    model.addAttribute("user", user);
    model.addAttribute("newTank", tank);

    return "add-tank.html";
  }

  @PostMapping(value = "add-tank")
  public String addTank(@ModelAttribute("newTank") @Valid Tank tank, BindingResult result,
      @ModelAttribute("user") User user) {

    if (result.hasErrors()) {
      return "add-tank.html";
    }

    boolean validName = true;
    try {
      validName = tankService.getTanksForUserWithNameLike(user, tank.getName()).size() == 0;
    } catch (NullPointerException npe) {
      validName = false;
    }

    if (!validName) {
      result.addError(new ObjectError("newTank", "tank already in use"));
      return "add-tank.html";
    }

    System.err.println("Tank being Added: " + tank);

    tank.setId(0);
    userService.addTankToUser(user.getId(), tank);

    return "redirect:/home";
  }

  // redirect to get the water changes for the tank
  @RequestMapping(value = "", params = { "tankId" })
  public String getTanksWaterChanges(@ModelAttribute("user") User user, BindingResult result) {

    return "/home";
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    CustomNumberEditor editor = new CustomNumberEditor(Double.class, true) {
      @Override
      public void setAsText(String text) throws IllegalArgumentException {

        try {
          super.setAsText(text);

        } catch (Exception iae) {
          setValue(null);
        }
      }

    };
    binder.registerCustomEditor(Double.class, editor);

    StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
    binder.registerCustomEditor(String.class, trimmer);
  }
}
