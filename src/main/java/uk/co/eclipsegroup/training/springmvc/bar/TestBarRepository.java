package uk.co.eclipsegroup.training.springmvc.bar;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
@Profile("test")
public class TestBarRepository implements BarRepository {
    private final List<Beer> beers = Arrays.asList(new Beer("Chmielu Strong IPA"), new Beer("Chmielu Stronger IPA"));

    @Override
    public List<Beer> fetchAll() {
        return beers;
    }
}
