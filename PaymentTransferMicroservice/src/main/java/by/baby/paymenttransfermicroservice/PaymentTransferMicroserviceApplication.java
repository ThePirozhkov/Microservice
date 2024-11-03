package by.baby.paymenttransfermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {"by.baby.component", "by.baby.paymenttransfermicroservice"})
@EntityScan({"by.baby.paymenttransfermicroservice.persistence.entity", "by.baby.persistence.entity"})
@EnableJpaRepositories({"by.baby.component.repository", "by.baby.paymenttransfermicroservice.repository"})
public class PaymentTransferMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentTransferMicroserviceApplication.class, args);
    }

}
