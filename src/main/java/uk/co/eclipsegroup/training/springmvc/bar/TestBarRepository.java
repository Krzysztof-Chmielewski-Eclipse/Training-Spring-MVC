package uk.co.eclipsegroup.training.springmvc.bar;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("test")
public class TestBarRepository implements BarRepository {
    private final Map<String, Beer> beers = new ConcurrentHashMap<>();

    public TestBarRepository() {
        beers.putAll(Map.of(
                "1", new Beer("1", "Chmielu Strong IPA", true),
                "2", new Beer("2", "Chmielu Stronger IPA", true)));
    }

    @Override
    public List<Beer> fetchAll() {
        return new ArrayList<>(beers.values());
    }

    @Override
    public Beer store(Beer beer) {
        return beers.put(beer.getId(), beer);
    }

    @Override
    public Beer remove(String id) {
        return beers.remove(id);
    }

    @Override
    public Beer findById(String id) {
        var beer = beers.get(id);
        if (beer == null) {
            throw new BeerNotFoundException(id);
        }
        return beer;
    }
}
