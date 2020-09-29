package uk.co.eclipsegroup.training.springmvc.brewing.service;

import org.springframework.stereotype.Service;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;
import uk.co.eclipsegroup.training.springmvc.brewing.repository.BeerRepository;

import java.util.List;

@Service
public class BarService {
    private final BeerRepository repository;

    public BarService(BeerRepository repository) {
        this.repository = repository;
    }

    public List<Beer> getAll() {
        return repository.fetchAll();
    }
}
