package com.neo.headers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersReceiver1 {
    @RabbitListener(queues = "headers.A")
    public void process(String message) {
        System.out.println("Headers Receiver 1: " + message);
    }
}
