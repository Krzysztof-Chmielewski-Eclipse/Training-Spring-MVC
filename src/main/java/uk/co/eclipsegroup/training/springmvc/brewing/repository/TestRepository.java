package uk.co.eclipsegroup.training.springmvc.brewing.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;

import java.util.Arrays;
import java.util.List;

@Profile("test")
@Repository
class TestRepository implements BeerRepository {
    @Override
    public List<Beer> fetchAll() {
        return Arrays.asList(
                new Beer("Chmielu IPA"),
                new Beer("Chmielu Strong IPA"),
                new Beer("Chmielu Triple IPA"),
                new Beer("Chmielu West Coast IPA"),
                new Beer("Some random APA")
        );
    }
}
