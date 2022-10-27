package com.example.emitter.service;


import com.example.emitter.config.RabbitMqConfig;
import com.example.emitter.dto.RequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmitterService {

    RabbitTemplate rabbitTemplate;

    static String ROUTING_KEY = "ROUTING_KEY";

    public void sendMessage(RequestDto message) {

        rabbitTemplate.convertAndSend(RabbitMqConfig.MAIN_EXCHANGE_NAME, ROUTING_KEY, message);
        log.info("Мы отправили: " + message);

    }


}
