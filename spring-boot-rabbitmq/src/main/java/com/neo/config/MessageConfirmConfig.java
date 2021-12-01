package com.neo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageConfirmConfig implements ConfirmCallback, ReturnCallback {
    private static final Logger logger = LoggerFactory.getLogger(MessageConfirmConfig.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 实现ConfirmCallback接口
     * 不管消息是否到达交换机，该回调都会执行
     * 如果消息没有到达交换机，则该方法中isSendSuccess = false
     * 如果消息正确到达交换机，则该方法中isSendSuccess = true
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String error) {
        if (correlationData != null) {
            logger.info("confirm回调方法>>>回调消息ID为: " + correlationData.getId());
        }
        if (isSendSuccess) {
            logger.info("confirm回调方法>>>消息发送到交换机成功！");
        } else {
            logger.info("confirm回调方法>>>消息发送到交换机失败！，原因 : [{}]", error);
        }
    }

    /**
     * 实现ReturnCallback接口
     * 消息从交换机成功到达队列，则returnedMessage方法不会执行
     * 消息从交换机未成功到达队列，则returnedMessage方法会执行
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("returnedMessage回调方法>>>" + message.toString() + ",replyCode:" + replyCode
                + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
    }
}
