package uk.co.eclipsegroup.training.springmvc.brewing.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Profile("test")
@Repository
class TestRepository implements BeerRepository {
    private final Map<String, Beer> beers = new ConcurrentHashMap<>();

    public TestRepository() {
        Stream.of(new Beer("1", "Chmielu IPA"),
                new Beer("2", "Chmielu Strong IPA"),
                new Beer("3", "Chmielu Triple IPA"),
                new Beer("4", "Chmielu West Coast IPA"),
                new Beer("5", "Some random APA")).forEach(beer -> beers.put(beer.getId(), beer));
    }

    @Override
    public List<Beer> fetchAll() {
        return List.copyOf(beers.values());
    }

    @Override
    public Optional<Beer> fetch(String id) {
        var beer = beers.get(id);
        if (beer == null) {
            return Optional.empty();
        }
        return Optional.of(beer);
    }

    @Override
    public Beer store(Beer beer) {
        return beers.put(beer.getId(), beer);
    }

    @Override
    public Optional<Beer> remove(String id) {
        return Optional.ofNullable(beers.remove(id));
    }
}
