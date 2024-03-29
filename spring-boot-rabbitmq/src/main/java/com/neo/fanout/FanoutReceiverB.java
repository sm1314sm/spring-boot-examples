package com.neo.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiverB {
    @RabbitListener(queues = "fanout.B")
    public void process(String message) {
        System.out.println("fanoutReceiverB: " + message);
    }
}
