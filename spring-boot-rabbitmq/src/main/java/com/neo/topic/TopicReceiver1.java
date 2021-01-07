package com.neo.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver1 {
    @RabbitListener(queues = "topic.A")
    public void process(String message) {
        System.out.println("Topic Receiver 1: " + message);
    }
}
