package com.example.listener.integration;


import com.example.listener.integration.annotation.IT;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;

@IT
public abstract class RabbitMqTestBased {
    public static final RabbitMQContainer container = new RabbitMQContainer("rabbitmq:management")
            .withExposedPorts(5672);

    @BeforeAll
    public static void start() {
        container.start();
    }

    @DynamicPropertySource
    public static void registrationOfSettings(DynamicPropertyRegistry registry) {

        registry.add("spring.rabbitmq.addresses", container::getHost);
        registry.add("spring.rabbitmq.port", () -> container.getMappedPort(5672));
    }
}
