package uk.co.eclipsegroup.training.springmvc.brewing.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;

import java.util.List;
import java.util.Optional;

@Profile("!test")
@Repository
class DefaultRepository implements BeerRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RestTemplate restTemplate;
    private final String apiUrl;

    DefaultRepository(RestTemplate restTemplate, @Value("${api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @Override
    public List<Beer> fetchAll() {
        return restTemplate.exchange(apiUrl + "beers", HttpMethod.GET, null, new ParameterizedTypeReference<List<Beer>>() {
        }).getBody();
    }

    @Override
    public Optional<Beer> fetch(String id) {
        try {
            return restTemplate.exchange(apiUrl + "beers/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<Optional<Beer>>() {
            }).getBody();
        } catch (Exception e) {
            log.error("Could not fetch Beer with id `{}`", id, e);
            return Optional.empty();
        }
    }

    @Override
    public Beer store(Beer beer) {
        return restTemplate.exchange(apiUrl + "beers", HttpMethod.POST, new HttpEntity<>(beer), new ParameterizedTypeReference<Beer>() {
        }).getBody();
    }

    @Override
    public Optional<Beer> remove(String id) {
        try {
            return Optional.ofNullable(restTemplate.exchange(apiUrl + "beers/" + id, HttpMethod.DELETE, null, new ParameterizedTypeReference<Beer>() {
            }).getBody());
        } catch (Exception e) {
            log.error("Could not fetch Beer with id `{}`", id, e);
            return Optional.empty();
        }
    }
}
