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

    @GetMapping("sum")
    public Integer sum() {
        return 5 / 0;
    }

    @GetMapping("byebye")
    public String byeBye() {
        throw new IllegalArgumentException("Baj baj maszkaro");
    }

    @GetMapping("hello/{customBeer}")
    public String helloBeer(@PathVariable String customBeer, Model model) {
        model.addAttribute("beer", customBeer);
        return "beer";
    }

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("beer", "Chmielu STRONG IPA");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Model model, IllegalArgumentException e) {
        model.addAttribute("message", e.getMessage());
        return "global-error";
    }
}
