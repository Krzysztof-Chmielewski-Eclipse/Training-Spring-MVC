package uk.co.eclipsegroup.training.springmvc.brewing;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("beers")
public class BeerController {
    @GetMapping("hello")
    public String helloBeer() {
        return "beer";
    }

    @PostMapping("hello")
    public String underTheCounterBeer(@RequestParam String customBeer, Model model) {
        model.addAttribute("customBeer", customBeer);
        return "beer";
    }

    @GetMapping("hello/{customBeer}")
    public String helloBeer(@PathVariable String customBeer, Model model) {
        model.addAttribute("beer", customBeer);
        return "beer";
    }

    @GetMapping("sum")
    public String iAmNotGoodAtMath() {
        return String.valueOf(5/0);
    }

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("beer", "Chmielu STRONG IPA");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String errorPage(Model model, ArithmeticException e) {
        model.addAttribute("type", "Custom handler");
        model.addAttribute("message", e.getMessage());
        return "global-error";
    }
}
