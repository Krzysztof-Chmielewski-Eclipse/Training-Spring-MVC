package uk.co.eclipsegroup.training.springmvc.brewing.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.eclipsegroup.training.springmvc.brewing.ControllerTest;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BarControllerTest extends ControllerTest {
    @Autowired
    private BarController barController;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${api.url}")
    private String apiUrl;
    private final Beer bestIpa = new Beer("1", "Chmielu IPA");

    private MockMvc mockMvc;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        mockMvc = MockMvcBuilders.standaloneSetup(barController).build();
    }

    @Test
    void barIsNotEmpty() throws Exception {
        server.expect(once(), requestTo(apiUrl + "beers"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(beers())));

        var mvcResult = mockMvc.perform(get("/bar"))
                .andExpect(status().isOk())
                .andReturn();

        var beers = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Beer>>() {
        });

        assertThat(beers).isNotEmpty().extracting(Beer::getName).noneMatch(String::isEmpty);
    }

    @Test
    void canFetchOneBeer() throws Exception {
        server.expect(once(), requestTo(apiUrl + "beers/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(bestIpa)));

        var mvcResult = mockMvc.perform(get("/bar/1"))
                .andExpect(status().isOk())
                .andReturn();

        var beers = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Optional<Beer>>() {
        });

        assertThat(beers).isPresent().get().extracting(Beer::getName).asString().isNotEmpty();
    }

    @Test
    void ifBeerWithIdDoesNotExistMessageIsEmpty() throws Exception {
        server.expect(once(), requestTo(apiUrl + "beers/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON));

        var mvcResult = mockMvc.perform(get("/bar/1"))
                .andExpect(status().isOk())
                .andReturn();

        var beers = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Optional<Beer>>() {
        });

        assertThat(beers).isEmpty();
    }

    @Test
    void beerCanBeStored() throws Exception {
        var newBeer = new Beer("99", "Brand New IPA");
        server.expect(once(), requestTo(apiUrl + "beers"))
                .andExpect(content().string(objectMapper.writeValueAsString(newBeer)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(newBeer)));

        var mvcResult = mockMvc.perform(post("/bar")
                .content(objectMapper.writeValueAsString(newBeer)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        var beers = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Optional<Beer>>() {
        });

        assertThat(beers).isPresent().get().isEqualTo(newBeer);
    }


    @Test
    void beerCanBeDeleted() throws Exception {
        server.expect(once(), requestTo(apiUrl + "beers/1"))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(bestIpa)));

        var mvcResult = mockMvc.perform(delete("/bar").content("1"))
                .andExpect(status().isOk())
                .andReturn();

        var beer = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Optional<Beer>>() {
        });

        assertThat(beer).isPresent().get().isEqualTo(bestIpa);
    }


    private List<Beer> beers() {
        return List.of(bestIpa, new Beer("2", "Chmielu Strong IPA"));
    }
}