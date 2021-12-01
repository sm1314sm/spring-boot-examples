package com.neo.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void directSendA() {
        String context = "hi, i am message 1";
        System.out.println("directSendA: " + context);
        rabbitTemplate.convertAndSend("directExchange", "derictA", context);
    }

    public void directSendB() {
        String context = "hi, i am message 2";
        System.out.println("directSendB: " + context);
        rabbitTemplate.convertAndSend("directExchange", "derictB", context);
    }
}