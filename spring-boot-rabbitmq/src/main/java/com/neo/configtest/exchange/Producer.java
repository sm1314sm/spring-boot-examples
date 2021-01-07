package com.neo.configtest.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private static final String EXCHANGE_NAME = "test_dlx_exchange_name";
    private static final String ROUTING_KEY = "user.add";
    private static final String UN_ROUTING_KEY = "user.delete";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        // 发送10条能够正确被路由的消息
        for (int i = 1; i <= 10; i++) {
            String message = "发送第" + i + "条能被正确路由的消息";
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message, new CorrelationData(UUID.randomUUID().toString()));
            logger.info("【发送了一条能够正确被路由的消息】,exchange:[{}],routingKey:[{}],message:[{}]", EXCHANGE_NAME, ROUTING_KEY, message);
        }

        // 发送2条不能正确被路由的消息
        for (int i = 1; i <= 2; i++) {
            String message = "发送第" + i + "条不能被正确路由的消息";
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, UN_ROUTING_KEY, message, new CorrelationData(UUID.randomUUID().toString()));
            logger.info("【发送了一条不能正确被路由的消息】,exchange:[{}],routingKey:[{}],message:[{}]", EXCHANGE_NAME, UN_ROUTING_KEY, message);
        }
    }
}
