package uk.co.eclipsegroup.training.springmvc.bar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bar")
public class BarController {
    private final BarService barService;

    public BarController(BarService barService) {
        this.barService = barService;
    }

    @GetMapping
    public List<Beer> list() {
        return barService.getAll();
    }

    @GetMapping("{id}")
    public Beer getById(@PathVariable String id) {
        return barService.getById(id);
    }

    @GetMapping("remove-alcohol")
    public ResponseEntity<String> noCanDo() {
        return new ResponseEntity<>("You wish!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public Beer addBeer(@RequestBody Beer beer) {
        return barService.save(beer);
    }

    @DeleteMapping("{id}")
    public Beer removeBeer(@PathVariable String id) {
        return barService.delete(id);
    }
}
