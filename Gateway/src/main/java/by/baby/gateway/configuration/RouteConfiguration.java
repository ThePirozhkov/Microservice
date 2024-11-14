package by.baby.gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Value("${spring.cloud.gateway.routes[0].id}")
    private String paymentTransferMicroserviceRouteId;
    @Value("${spring.cloud.gateway.routes[1].id}")
    private String userMicroserviceRouteId;

    @Value("${custom.eureka.payment-transfer-microservice.path}")
    private String eurekaPaymentTransferMicroservicePath;
    @Value("${custom.eureka.user-microservice.path}")
    private String eurekaUserMicroservicePath;


    @Value("${spring.cloud.gateway.routes[0].uri}")
    private String eurekaPaymentTransferMicroserviceUri;
    @Value("${spring.cloud.gateway.routes[1].uri}")
    private String eurekaUserMicroserviceUri;

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(paymentTransferMicroserviceRouteId,
                        route -> route.path(eurekaPaymentTransferMicroservicePath)
                                .uri(eurekaPaymentTransferMicroserviceUri))
                .route(userMicroserviceRouteId,
                        route -> route.path(eurekaUserMicroservicePath)
                                .uri(eurekaUserMicroserviceUri))
                .build();
    }
}
