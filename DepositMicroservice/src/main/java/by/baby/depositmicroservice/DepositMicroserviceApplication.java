package by.baby.depositmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		scanBasePackages = {"by.baby.component",
				"by.baby.depositmicroservice"})
@EntityScan(
		basePackages = {"by.baby.persistence.entity",
				"by.baby.depositmicroservice.persistence.entity"}
)
@EnableJpaRepositories(
		{"by.baby.component.repository", "by.baby.depositmicroservice.repository"}
)
public class DepositMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepositMicroserviceApplication.class, args);
	}

}
