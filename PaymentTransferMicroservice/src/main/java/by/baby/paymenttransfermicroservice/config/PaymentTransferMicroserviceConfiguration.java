package by.baby.paymenttransfermicroservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EntityScan(
        basePackages = {"by.baby.paymenttransfermicroservice.persistence.entity",
                "by.baby.persistence.entity"}
)
@ComponentScan(
        basePackages = {"by.baby.component",
                "by.baby.paymenttransfermicroservice"}
)
public class PaymentTransferMicroserviceConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
