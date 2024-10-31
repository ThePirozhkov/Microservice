package by.baby.util.constants;


import org.springframework.web.client.ResourceAccessException;

import java.net.ConnectException;
import java.util.HashSet;
import java.util.Set;

public final class KafkaConfigurationConstants {

    private KafkaConfigurationConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static Set<Class<? extends Exception>> getRetryableExceptions() {
        HashSet<Class<? extends Exception>> retryableExceptions = new HashSet<>();
        retryableExceptions.add(ResourceAccessException.class);
        retryableExceptions.add(ConnectException.class);
        return retryableExceptions;
    }
}
