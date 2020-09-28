package uk.co.eclipsegroup.training.springmvc.brewing;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
class BeerControllerTest {
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new BeerController()).build();

    @Test
    void beerView_isReturned(SoftAssertions softly) throws Exception {
        var mvcResult = mockMvc.perform(get("/beers/hello"))
                .andExpect(status().isOk())
                .andReturn();

        softly.assertThat(extractViewName(mvcResult)).isEqualTo("beer");
        softly.assertThat(extractAttribute(mvcResult)).contains("Chmielu");
    }

    private String extractAttribute(org.springframework.test.web.servlet.MvcResult mvcResult) {
        return (String) mvcResult.getModelAndView().getModelMap().get("beer");
    }

    private String extractViewName(org.springframework.test.web.servlet.MvcResult mvcResult) {
        return mvcResult.getModelAndView().getViewName();
    }
}