package by.baby.withdrawalmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"by.baby.component",
		"by.baby.withdrawalmicroservice"})
@EntityScan(
		basePackages = {"by.baby.persistence.entity",
				"by.baby.withdrawalmicroservice.persistence.entity"}
)
@EnableJpaRepositories({"by.baby.component.repository",
		"by.baby.withdrawalmicroservice.repository"})
public class WithdrawalMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithdrawalMicroserviceApplication.class, args);
	}

}
