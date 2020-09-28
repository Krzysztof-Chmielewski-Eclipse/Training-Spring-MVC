package uk.co.eclipsegroup.training.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class TrainingSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingSpringMvcApplication.class, args);
	}

}
