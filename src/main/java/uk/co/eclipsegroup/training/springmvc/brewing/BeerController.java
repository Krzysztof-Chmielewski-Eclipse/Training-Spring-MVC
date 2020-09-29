package uk.co.eclipsegroup.training.springmvc.brewing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("beers")
public class BeerController {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String helloBeer(Model model) {
        model.addAttribute("beer", "Chmielu STRONG IPA");

        return "beer";
    }
}
