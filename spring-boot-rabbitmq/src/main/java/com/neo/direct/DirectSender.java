package com.neo.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send1() {
        String context = "hi, direct msg ";
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend("directExchange", "test1", context);
    }

    public void send2() {
        String context = "hi, direct msg ";
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend("directExchange", "test2", context);
    }
}