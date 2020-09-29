package uk.co.eclipsegroup.training.springmvc.brewing.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;
import uk.co.eclipsegroup.training.springmvc.brewing.service.BarService;

import java.util.List;

@RestController
@RequestMapping("bar")
public class BarController {
    private final BarService barService;

    public BarController(BarService barService) {
        this.barService = barService;
    }

    @GetMapping
    public List<Beer> listBeers() {
        return barService.getAll();
    }
}
