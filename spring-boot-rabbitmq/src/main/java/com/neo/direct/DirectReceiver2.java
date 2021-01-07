package com.neo.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver2 {
    @RabbitListener(queues = "direct.B")
    public void process(String message) {
        System.out.println("Direct Receiver: " + message);
    }
}
