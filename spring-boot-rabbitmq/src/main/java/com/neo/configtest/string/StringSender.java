package com.neo.configtest.string;

import com.neo.configtest.pojo.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(int i) {
        Object context = "hi, sender string msg " + i;
        rabbitTemplate.convertAndSend("neo", context, new CorrelationData(UUID.randomUUID().toString()));
    }
}