package themayoras.trackmywaterchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import themayoras.trackmywaterchange.entity.Tank;
import themayoras.trackmywaterchange.entity.WaterChange;
import themayoras.trackmywaterchange.entity.WaterChangeComment;
import themayoras.trackmywaterchange.service.TankService;

@Controller
@RequestMapping("/tank")
public class TankController {

    @Autowired
    private TankService tankService;

    @PostMapping("/delete/{tankId}")
    public String deleteTank(@PathVariable("tankId") int id) {
        System.err.println("Tank ID: " + id);

        tankService.deleteTank(id);

        return "redirect:/home";
    }

    @GetMapping("/list")
    public String getTankList(@RequestParam("tankId") int id, Model model) {
        Tank tank = tankService.getTank(id);

        model.addAttribute("tank", tank);

        return "tanks/list-tanks.html";
    }

    @GetMapping("/add/waterChange/{tankId}")
    public String getAdddWaterChange(@PathVariable("tankId") int tankId, Model model) {
        Tank tank = tankService.getTank(tankId);

        model.addAttribute("tank", tank);
        model.addAttribute("newWaterChange", new WaterChange());

        return "tanks/add-water-change.html";
    }

    @PostMapping(path = "/add/waterChange/{{tankId}", params = { "addComment" })
    public String addCommentToWaterChange(@ModelAttribute("newWaterChange") WaterChange waterChange,
            @ModelAttribute("tank") Tank tank, Model model) {

        model.addAttribute("tank", tank);
        waterChange.addComment(new WaterChangeComment());
    
        return "tanks/add-water-change.html";
    }
}
