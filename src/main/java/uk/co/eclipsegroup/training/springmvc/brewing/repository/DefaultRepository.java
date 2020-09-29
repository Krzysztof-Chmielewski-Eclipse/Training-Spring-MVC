package uk.co.eclipsegroup.training.springmvc.brewing.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;

import java.util.List;

@Profile("!test")
@Repository
class DefaultRepository implements BeerRepository {
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
}
