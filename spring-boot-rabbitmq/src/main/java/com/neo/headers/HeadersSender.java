package com.neo.headers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeadersSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send1() {
        String context = "hi, headers msg ";
        System.out.println("Sender : " + context);
        Message message = MessageBuilder.withBody(context.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setHeader("a", "1")
                .build();
        rabbitTemplate.send("headersExchange", "", message);
    }

    public void send2() {
        String context = "hi, headers msg ";
        System.out.println("Sender : " + context);
        Message message = MessageBuilder.withBody(context.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setHeader("b", "2")
                .setHeader("c", "3")
                .build();
        rabbitTemplate.send("headersExchange", "", message);
    }
}
