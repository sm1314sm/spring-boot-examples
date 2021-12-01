package com.neo.headers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersReceiverB {
    @RabbitListener(queues = "headers.B")
    public void process(String message) {
        System.out.println("HeadersReceiverB: " + message);
    }
}
