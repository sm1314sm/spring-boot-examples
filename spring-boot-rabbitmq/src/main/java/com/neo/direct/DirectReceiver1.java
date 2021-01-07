package com.neo.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver1 {
    @RabbitListener(queues = "direct.A")
    public void process(String message) {
        System.out.println("Direct Receiver: " + message);
    }
}
