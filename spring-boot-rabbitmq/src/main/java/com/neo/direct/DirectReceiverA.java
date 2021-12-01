package com.neo.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiverA {
    @RabbitListener(queues = "direct.A")
    public void process(String message) {
        System.out.println("DirectReceiverA: " + message);
    }
}
