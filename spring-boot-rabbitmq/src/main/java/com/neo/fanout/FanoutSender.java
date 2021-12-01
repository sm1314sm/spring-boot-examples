package com.neo.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void fanoutSend() {
        String context = "hi, i am message 1";
        System.out.println("fanoutSend: " + context);
        rabbitTemplate.convertAndSend("fanoutExchange", "", context);
    }
}