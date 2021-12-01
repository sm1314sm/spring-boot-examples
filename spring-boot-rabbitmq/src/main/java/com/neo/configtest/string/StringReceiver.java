package com.neo.configtest.string;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Component
public class StringReceiver {
    @RabbitListener(queues = "neo")
    public void neoProcess(Message message, Channel channel) throws IOException {
        try {
            // 获取消息
            String context = new String(message.getBody());
            System.out.println("MessageProperties:" + message.getMessageProperties() + "\nMessageBody:" + context);
            // 消息确认，第一个参数代表消息下标，b代表是否批量确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 判断消息是否已经拒绝过
            if (message.getMessageProperties().getRedelivered()) {
                // 拒绝消息，第一个参数代表消息下标，b代表是否批量取消，b1代表是否重新进入队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}