package uk.co.eclipsegroup.training.springmvc.brewing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BeerControllerTest {
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new BeerController()).build();

    @Test
    void beerView_isRendered() throws Exception {
        var mvcResult = mockMvc.perform(get("/beers/hello"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("beer");
    }
}