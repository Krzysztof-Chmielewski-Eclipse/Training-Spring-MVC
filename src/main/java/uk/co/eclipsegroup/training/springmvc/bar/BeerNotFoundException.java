package uk.co.eclipsegroup.training.springmvc.bar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeerNotFoundException extends RuntimeException {
    public BeerNotFoundException(String id) {
        super("Could not find beer with " + id);
    }
}
