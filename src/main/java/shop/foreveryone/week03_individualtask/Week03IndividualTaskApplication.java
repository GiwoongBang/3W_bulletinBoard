package shop.foreveryone.week03_individualtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week03IndividualTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(Week03IndividualTaskApplication.class, args);
	}

}
