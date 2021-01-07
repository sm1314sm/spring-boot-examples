package com.neo.configtest.object;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ObjectReceiver {
    private static final Logger logger = LoggerFactory.getLogger(ObjectReceiver.class);

    @RabbitListener(queues = "object")
    public void process(User user, Message message, Channel channel) throws IOException {
        try {
            System.out.println(user);
            // 消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                logger.info("异常信息: {}", e.getMessage());
                // 拒绝消息，并且消息不再重新进入队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } else {
                logger.info("异常信息: {}", e.getMessage());
                // 拒绝消息，并且消息重新回到队列处理
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}
