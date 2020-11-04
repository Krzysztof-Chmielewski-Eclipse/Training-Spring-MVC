package uk.co.eclipsegroup.training.springmvc.brewing;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
class BeerControllerTest {
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new BeerController()).build();

    @Test
    void beerView_isRendered() throws Exception {
        var mvcResult = mockMvc.perform(get("/beers/hello"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("beer");
    }

    @Test
    void underTheCounterBeer_canBeFetched(SoftAssertions softly) throws Exception {
        var beerName = "Chmielu Illegal IPA";
        var mvcResult = mockMvc.perform(post("/beers/hello").param("customBeer", beerName))
                .andExpect(status().isOk())
                .andReturn();

        softly.assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("beer");
        softly.assertThat(mvcResult.getModelAndView().getModel().get("customBeer")).isEqualTo(beerName);
    }

    @Test
    void itsNotGoodToSayGoodbye() throws Exception {
        var mvcResult = mockMvc.perform(get("/beers/byebye"))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andReturn();

    }
}