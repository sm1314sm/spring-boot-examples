package com.neo.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiverB {
    @RabbitListener(queues = "direct.B")
    public void process(String message) {
        System.out.println("DirectReceiverB: " + message);
    }
}
