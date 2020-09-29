package uk.co.eclipsegroup.training.springmvc.brewing.service;

import org.springframework.stereotype.Service;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;
import uk.co.eclipsegroup.training.springmvc.brewing.repository.BeerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BarService {
    private final BeerRepository repository;

    public BarService(BeerRepository repository) {
        this.repository = repository;
    }

    public List<Beer> getAll() {
        return repository.fetchAll();
    }

    public Optional<Beer> get(String id) {
        return repository.fetch(id);
    }

    public Beer save(Beer beer) {
        return repository.store(beer);
    }

    public Optional<Beer> delete(String id) {
        return repository.remove(id);
    }
}
