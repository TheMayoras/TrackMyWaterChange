package themayoras.trackmywaterchange.controller;

import java.text.DateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import themayoras.trackmywaterchange.dateformat.MultiDateFormatBuilder;
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

    @GetMapping("/list/{tankId}")
    public String getTankList(@PathVariable("tankId") int id, Model model) {
        Tank tank = tankService.getTank(id);

        model.addAttribute("tank", tank);

        return "tanks/list-tank-water-changes.html";
    }

    @GetMapping("/add/waterChange/{tankId}")
    public String getAdddWaterChange(@PathVariable("tankId") int tankId, Model model) {
        Tank tank = tankService.getTank(tankId);

        WaterChange newWaterChange = new WaterChange();
        newWaterChange.setTank(tank);

        model.addAttribute("newWaterChange", newWaterChange);

        return "tanks/add-water-change.html";
    }

    @PostMapping(path = "/add/waterChange/{tankId}", params = { "addComment" })
    public String addCommentToWaterChange(@ModelAttribute("newWaterChange") WaterChange waterChange,
            @PathVariable("tankId") int tankId, Model model) {


        WaterChangeComment comment = new WaterChangeComment();
        comment.setWaterChange(waterChange);

        waterChange.addComment(comment);

        return "tanks/add-water-change.html";
    }

    @PostMapping(path = "/add/waterChange/{tankId}", params = { "add" })
    public String addWaterChange(@ModelAttribute("newWaterChange") @Valid WaterChange waterChange, BindingResult result,
            @PathVariable("tankId") int tankId) {

        if (result.hasErrors()) {
            return "/tanks/add-water-change.html";
        }

        tankService.createWaterChange(waterChange.getTank(), waterChange);

        return "redirect:/home";
    }

    @PostMapping("/delete/waterChange/{waterChangeId}")
    public String deleteWaterChange(@PathVariable("waterChangeId") int id) {

        Tank tank = tankService.getWaterChange(id).getTank();

        tankService.deleteWaterChange(id);

        return "redirect:/tank/list/" + tank.getId();
    }

    @InitBinder
    public void bindDate(WebDataBinder binder) {
        DateFormat format = MultiDateFormatBuilder.create().setOutputFormat("mm-dd-yyyy")
                .addFormat("\\d{2}-\\d{2}-\\d{2}", "MM-dd-yy").addFormat("\\d{2}-\\d{2}-\\d{4}", "MM-dd-yyyy")
                .addFormat("\\d{2}/\\d{2}/\\d{2}", "MM/dd/yy").addFormat("\\d{2}\\/\\d{2}\\/\\d{4}", "MM/dd/yyyy")
                .addFormat("\\d/\\d{2}/\\d{4}", "M/dd/yyyy").addFormat("\\d/\\d{2}/\\d{2}", "M/dd/yy")
                .addFormat("\\d/\\d{2}/\\d{4}", "M-dd-yyyy").addFormat("\\d/\\d{2}/\\d{2}", "M-dd-yy").build();

        CustomDateEditor editor = new CustomDateEditor(format, true) {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    System.err.println(text);
                    super.setAsText(text);
                    System.err.println(super.getAsText());
                } catch (IllegalArgumentException iae) {
                    super.setAsText(null);
                }
            }

        };

        binder.registerCustomEditor(Date.class, editor);

        CustomNumberEditor numberEditor = new CustomNumberEditor(Double.class, true) {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    System.err.println("Number Editor: " + text);
                    super.setAsText(text);
                } catch (IllegalArgumentException iae) {
                    super.setAsText(null);
                }
            }

        };
        binder.registerCustomEditor(Double.class, numberEditor);
    }
}
