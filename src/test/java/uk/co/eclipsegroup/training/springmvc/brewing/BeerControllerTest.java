package uk.co.eclipsegroup.training.springmvc.brewing;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
class BeerControllerTest {
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new BeerController()).build();

    @Test
    void beerView_isReturned(SoftAssertions softly) throws Exception {
        var mvcResult = helloBeer("/beers/hello");

        softly.assertThat(extractViewName(mvcResult)).isEqualTo("beer");
        softly.assertThat(extractAttribute(mvcResult)).contains("Chmielu");
    }

    @Test
    void customerCanAskForCustomBeer(SoftAssertions softly) throws Exception {
        var beerName = "Chmielu Double IPA";
        var mvcResult = helloBeer("/beers/hello/" + beerName);

        softly.assertThat(extractViewName(mvcResult)).isEqualTo("beer");
        softly.assertThat(extractAttribute(mvcResult)).isEqualTo(beerName);
    }

    @Test
    void customerCanAskForCustomBeer_underTheCounter(SoftAssertions softly) throws Exception {
        var beerName = "Chmielu Quadruple IPA";
        var mvcResult =  mockMvc.perform(post("/beers/hello").param("customBeer", beerName))
                .andExpect(status().isOk())
                .andReturn();

        softly.assertThat(extractViewName(mvcResult)).isEqualTo("beer");
        softly.assertThat(extractAttribute(mvcResult)).isEqualTo(beerName);
    }

    private MvcResult helloBeer(String path) throws Exception {
        return mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andReturn();
    }

    private String extractAttribute(org.springframework.test.web.servlet.MvcResult mvcResult) {
        return (String) mvcResult.getModelAndView().getModelMap().get("beer");
    }

    private String extractViewName(org.springframework.test.web.servlet.MvcResult mvcResult) {
        return mvcResult.getModelAndView().getViewName();
    }
}