package uk.co.eclipsegroup.training.springmvc.brewing.service;

import org.springframework.stereotype.Service;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;

import java.util.Arrays;
import java.util.List;

@Service
public class BarService {
    public List<Beer> getAll() {
        return Arrays.asList(
                new Beer("Chmielu IPA"),
                new Beer("Chmielu Strong IPA"),
                new Beer("Chmielu Triple IPA"),
                new Beer("Chmielu West Coast IPA"),
                new Beer("Some random APA")
                );
    }
}
