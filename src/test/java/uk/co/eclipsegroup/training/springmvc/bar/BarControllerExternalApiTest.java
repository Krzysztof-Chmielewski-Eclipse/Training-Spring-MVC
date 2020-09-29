package uk.co.eclipsegroup.training.springmvc.bar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BarControllerExternalApiTest {
    private MockRestServiceServer server;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BarController barController;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${api.url}")
    private String apiUrl;
    private ClientHttpRequestFactory originalRestFactory;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(barController).build();
    }

    @AfterEach
    void tearDown() {
        restTemplate.setRequestFactory(originalRestFactory);
    }

    @Test
    void barIsNotEmpty() throws Exception {
        setupMockServer();

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

        assertThat(beers).isNotEmpty().extracting(Beer::getName).allMatch(b -> b.contains("Chmielu"));
    }

    private void setupMockServer() {
        originalRestFactory = restTemplate.getRequestFactory();
        server = MockRestServiceServer.createServer(restTemplate);
    }

    private List<Beer> beers() {
        return Arrays.asList(new Beer("Chmielu Strong IPA"), new Beer("Chmielu Stronger IPA"));
    }
}