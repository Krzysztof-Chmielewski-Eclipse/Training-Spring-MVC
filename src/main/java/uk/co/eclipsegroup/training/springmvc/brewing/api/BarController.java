package uk.co.eclipsegroup.training.springmvc.brewing.api;

import org.springframework.web.bind.annotation.*;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;
import uk.co.eclipsegroup.training.springmvc.brewing.service.BarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bar")
public class BarController {
    private final BarService barService;

    public BarController(BarService barService) {
        this.barService = barService;
    }

    @GetMapping("{id}")
    public Optional<Beer> getBeer(@PathVariable String id) {
        return barService.get(id);
    }

    @GetMapping
    public List<Beer> listBeers() {
        return barService.getAll();
    }

    @PostMapping
    public Beer saveBeer(@RequestBody Beer beer) {
        return barService.save(beer);
    }

    @DeleteMapping
    public Optional<Beer> deleteBeer(@RequestBody String id) {
         return barService.delete(id);
    }
}
