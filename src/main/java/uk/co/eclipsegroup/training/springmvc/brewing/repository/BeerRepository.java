package uk.co.eclipsegroup.training.springmvc.brewing.repository;

import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;

import java.util.List;
import java.util.Optional;

public interface BeerRepository {
    List<Beer> fetchAll();

    Optional<Beer> fetch(String id);

    Beer store(Beer beer);

    Optional<Beer> remove(String id);
}
