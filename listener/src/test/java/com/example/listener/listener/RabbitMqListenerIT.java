package com.example.listener.listener;

import com.example.listener.config.RabbitMqConfig;
import com.example.listener.dto.RequestDto;
import com.example.listener.integration.RabbitMqTestBased;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.mockito.ArgumentMatchers.any;

class RabbitMqListenerIT extends RabbitMqTestBased {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @MockBean
    SomeService someService;

    static String ROUTING_KEY = "ROUTING_KEY";

    @TestConfiguration
    static class RabbitMqTestConfig{

        @Bean
        public CachingConnectionFactory connectionFactory() {
            return new CachingConnectionFactory("localhost");
        }

    }

    @Test
    void processMessagesFromAnyQueue() {

        RequestDto message = new RequestDto("test", 1);

        rabbitTemplate.convertAndSend(RabbitMqConfig.MAIN_EXCHANGE_NAME, ROUTING_KEY, message);

        Mockito.verify(someService, Mockito.timeout(1000)).transfer(any());

    }
}