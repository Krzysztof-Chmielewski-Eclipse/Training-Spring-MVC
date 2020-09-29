package uk.co.eclipsegroup.training.springmvc.brewing.repository;

import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;

import java.util.List;

public interface BeerRepository {
    List<Beer> fetchAll();
}
