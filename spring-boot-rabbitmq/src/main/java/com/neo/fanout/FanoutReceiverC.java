package com.neo.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiverC {
    @RabbitListener(queues = "fanout.C")
    public void process(String message) {
        System.out.println("fanoutReceiverC: " + message);
    }
}
