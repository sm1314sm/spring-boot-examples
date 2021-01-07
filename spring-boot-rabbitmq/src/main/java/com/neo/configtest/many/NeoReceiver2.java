package com.neo.configtest.many;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NeoReceiver2 {
    @RabbitListener(queues = "neo")
    public void process(String neo, Message message, Channel channel) throws IOException {
        try {
            System.out.println(neo);
            // 消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                // 拒绝消息，并且消息不再重新进入队列 只能拒绝一个
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                // 拒绝消息，并且消息重新回到队列处理
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}