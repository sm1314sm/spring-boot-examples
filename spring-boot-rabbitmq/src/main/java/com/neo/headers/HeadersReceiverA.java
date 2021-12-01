package com.neo.headers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersReceiverA {
    @RabbitListener(queues = "headers.A")
    public void process(String message) {
        System.out.println("HeadersReceiverA: " + message);
    }
}
