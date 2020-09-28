package uk.co.eclipsegroup.training.springmvc.brewing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("beers")
public class BeerController {
    @GetMapping("hello")
    public String helloBeer(Model model) {
        model.addAttribute("beer", "Chmielu Triple STRONGEST IPA");
        return "beer";
    }
}
