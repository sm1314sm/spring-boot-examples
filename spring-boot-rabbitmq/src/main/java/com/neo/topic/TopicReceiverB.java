package com.neo.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiverB {
    @RabbitListener(queues = "topic.B")
    public void process(String message) {
        System.out.println("TopicReceiverB: " + message);
    }
}
