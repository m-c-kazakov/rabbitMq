package com.example.listener.listener;

import com.example.listener.dto.RequestDto;
import com.rabbitmq.client.Channel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RabbitMqListener {

    SomeService someService;

    @RabbitListener(queues = "listener-queue")
    public void processMessagesFromAnyQueue(RequestDto message,
                                            Channel channel,
                                            @Header(AmqpHeaders.DELIVERY_TAG) String tag) {
        log.info("Мы получили={}; tag={} ", message.toString(), tag);
        someService.transfer(message);
    }
}
