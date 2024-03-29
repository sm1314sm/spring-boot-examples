package com.neo.configtest.object;

import com.neo.configtest.pojo.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ObjectSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(int i) {
        User user = new User();
        user.setUserName("joke" + i);
        user.setPassWord("password" + i);
        rabbitTemplate.convertAndSend("object", user, new CorrelationData(UUID.randomUUID().toString()));
    }
}