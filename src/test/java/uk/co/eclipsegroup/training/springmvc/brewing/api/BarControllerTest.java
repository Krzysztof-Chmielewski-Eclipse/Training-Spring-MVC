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

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class BarControllerTest extends ControllerTest {
    @Autowired
    private BarController barController;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${api.url}")
    private String apiUrl;

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

        var mvcResult = mockMvc.perform(get("/bar")).andReturn();

        var beers = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Beer>>() {
        });

        assertThat(beers).isNotEmpty().extracting(Beer::getName).noneMatch(String::isEmpty);
    }

    private List<Beer> beers() {
        return List.of(new Beer("Chmielu IPA"), new Beer("Chmielu Strong IPA"));
    }
}