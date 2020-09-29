package uk.co.eclipsegroup.training.springmvc.bar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
@Profile("!test")
public class DefaultBarRepository implements BarRepository {
    private final RestTemplate restTemplate;
    private final String urlApi;

    public DefaultBarRepository(RestTemplate restTemplate, @Value("${api.url}") String urlApi) {
        this.restTemplate = restTemplate;
        this.urlApi = urlApi;
    }

    @Override
    public List<Beer> fetchAll() {
        return fetchAllBeers().getBody();
    }

    @Override
    public Beer store(Beer beer) {
        return storeBeer(beer).getBody();
    }

    @Override
    public Beer remove(String id) {
        return deleteBeer(id).getBody();
    }

    private ResponseEntity<Beer> deleteBeer(String id) {
        return restTemplate.exchange(urlApi + "beers/" + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<>() {
                });
    }

    private ResponseEntity<Beer> storeBeer(Beer beer) {
        return restTemplate.exchange(urlApi + "beers", HttpMethod.POST, new HttpEntity<>(beer),
                new ParameterizedTypeReference<>() {
                });
    }

    private ResponseEntity<List<Beer>> fetchAllBeers() {
        return restTemplate.exchange(urlApi + "beers", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
    }
}