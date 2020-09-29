package uk.co.eclipsegroup.training.springmvc.bar;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BarService {
    public List<Beer> getAll() {
        return Arrays.asList(new Beer("Chmielu Strong IPA"), new Beer("Chmielu Stronger IPA"));
    }
}
