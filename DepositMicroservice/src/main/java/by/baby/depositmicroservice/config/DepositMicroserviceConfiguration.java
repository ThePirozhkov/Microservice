package by.baby.depositmicroservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("by.baby.persistence.entity")
public class DepositMicroserviceConfiguration {
}
