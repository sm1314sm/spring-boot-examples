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

    public void headersSendA() {
        String context = "hi, i am message 1";
        System.out.println("headersSendA: " + context);
        Message message = MessageBuilder.withBody(context.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setHeader("a", "1")
                .build();
        rabbitTemplate.send("headersExchange", "", message);
    }

    public void headersSendB() {
        String context = "hi, i am message 2";
        System.out.println("headersSendB: " + context);
        Message message = MessageBuilder.withBody(context.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setHeader("b", "2")
                .build();
        rabbitTemplate.send("headersExchange", "", message);
    }
}
