package com.neo.configtest.many;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NeoSender2 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(int i) {
        String context = "hi, sender2 neo msg " + i;
        rabbitTemplate.convertAndSend("neo", context);
    }
}