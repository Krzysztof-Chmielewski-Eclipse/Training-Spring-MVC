package uk.co.eclipsegroup.training.springmvc.brewing;

import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
public class ControllerTest {
    protected MockRestServiceServer server;
    private ClientHttpRequestFactory originalRequestFactory;
    @Autowired
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        originalRequestFactory = restTemplate.getRequestFactory();
        server = MockRestServiceServer.createServer(restTemplate);
    }

    @AfterEach
    public void tearDown() {
        restTemplate.setRequestFactory(originalRequestFactory);
    }
}
