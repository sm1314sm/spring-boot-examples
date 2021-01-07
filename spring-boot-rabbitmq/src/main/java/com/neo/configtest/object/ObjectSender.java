package com.neo.configtest.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ObjectSender {
    private static final Logger logger = LoggerFactory.getLogger(ObjectSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setUserName("joke" + i);
            user.setPassWord("password" + i);
            logger.info(user.toString());
            rabbitTemplate.convertAndSend("object", user, new CorrelationData(UUID.randomUUID().toString()));
        }
    }
}