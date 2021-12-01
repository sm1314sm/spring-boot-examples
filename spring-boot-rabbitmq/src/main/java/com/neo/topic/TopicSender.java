package com.neo.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void topicSendA() {
        String context = "hi, i am message 1";
        System.out.println("topicSendA: " + context);
        rabbitTemplate.convertAndSend("topicExchange", "topic.message1", context);
    }

    public void topicSendB() {
        String context = "hi, i am message 2";
        System.out.println("topicSendB: " + context);
        rabbitTemplate.convertAndSend("topicExchange", "topic.message2", context);
    }
}