package uk.co.eclipsegroup.training.springmvc.bar;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarService {
    private final BarRepository barRepository;

    public BarService(BarRepository barRepository) {
        this.barRepository = barRepository;
    }

    public List<Beer> getAll() {
        return barRepository.fetchAll();
    }

    public Beer save(Beer beer) {
        return barRepository.store(beer);
    }

    public Beer delete(String id) {
        return barRepository.remove(id);
    }
}
