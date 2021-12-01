package com.neo.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiverA {
    @RabbitListener(queues = "topic.A")
    public void process(String message) {
        System.out.println("TopicReceiverA: " + message);
    }
}
