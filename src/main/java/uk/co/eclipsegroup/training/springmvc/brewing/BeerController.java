package uk.co.eclipsegroup.training.springmvc.brewing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("beers")
public class BeerController {
    @GetMapping("hello")
    public ModelAndView helloBeer() {
        return new ModelAndView("beer");
    }

    @GetMapping("hello/{customBeer}")
    public ModelAndView helloBeer(@PathVariable String customBeer, Model model) {
        populateBeer(customBeer, model);
        return new ModelAndView("beer");
    }

    @PostMapping("hello")
    public ModelAndView underTheCounter(@RequestParam Optional<String> customBeer, Model model) {
        customBeer.ifPresent(beer -> populateBeer(beer, model));
        return new ModelAndView("beer");
    }

    @ModelAttribute
    private void beer(Model model) {
        populateBeer("Chmielu Triple STRONGEST IPA", model);
    }

    private void populateBeer(String customBeer, Model model) {
        model.addAttribute("beer", customBeer);
    }
}
