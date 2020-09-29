package uk.co.eclipsegroup.training.springmvc.brewing.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.eclipsegroup.training.springmvc.brewing.ControllerTest;
import uk.co.eclipsegroup.training.springmvc.brewing.model.Beer;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class BarControllerTest extends ControllerTest {
    @Autowired
    private BarController barController;
    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(barController).build();
    }

    @Test
    void barIsNotEmpty() throws Exception {
        var mvcResult = mockMvc.perform(get("/bar")).andReturn();

        var beers = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Beer>>() {
        });

        assertThat(beers).isNotEmpty().extracting(Beer::getName).noneMatch(String::isEmpty);
    }
}