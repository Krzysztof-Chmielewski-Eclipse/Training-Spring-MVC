package uk.co.eclipsegroup.training.springmvc.bar;

import java.util.List;

interface BarRepository {
    List<Beer> fetchAll();

    Beer store(Beer beer);

    Beer remove(String id);
}
