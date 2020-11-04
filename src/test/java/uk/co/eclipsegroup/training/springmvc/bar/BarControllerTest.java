package uk.co.eclipsegroup.training.springmvc.bar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class BarControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BarController barController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(barController).build();
    }

    @Test
    void barIsNotEmpty() throws Exception {
        var mvcResult = mockMvc.perform(get("/bar"))
                .andExpect(status().isOk())
                .andReturn();

        var beers = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Beer>>() {
        });

        assertThat(beers).isNotEmpty().extracting(Beer::getName).allMatch(b -> b.contains("Chmielu"));
    }

    @Test
    void cannotRemoveAlcoholFromBar() throws Exception {
        var mvcResult = mockMvc.perform(get("/bar/remove-alcohol"))
                .andExpect(status().is(HttpStatus.METHOD_NOT_ALLOWED.value()))
                .andReturn();

        var message = mvcResult.getResponse().getContentAsString();

        assertThat(message).isNotEmpty();
    }

    @Test
    void cannotFetchNonExistentBeer() throws Exception {
        mockMvc.perform(get("/bar/3"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andReturn();
    }

    @Test
    void canFetchBeer_thatIsInTheBar() throws Exception {
        var mvcResult = mockMvc.perform(get("/bar/1"))
                .andExpect(status().isOk())
                .andReturn();

        var beer = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Beer>() {
        });

        assertThat(beer).isNotNull().extracting(Beer::getId).isEqualTo("1");
    }
}