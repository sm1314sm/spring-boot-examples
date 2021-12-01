package com.neo.configtest.exchange;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@Component
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = "test_dlx_queue_name")
    public void receiveMessage(Message message, Channel channel) throws IOException {
        try {
            // 获取消息
            String context = new String(message.getBody());
            // 模拟拒绝一些消息到死信队列中
            if (new Random().nextInt(10) < 5) {
                // 拒绝消息，第一个参数代表消息下标，b代表是否批量取消，b1代表是否重新进入队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } else {
                // 正常确认消息
                System.out.println("MessageProperties:" + message.getMessageProperties() + "\nMessageBody:" + context);
                // 消息确认，第一个参数代表消息下标，b代表是否批量确认
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            // 判断消息是否已经拒绝过
            if (message.getMessageProperties().getRedelivered()) {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    @RabbitListener(queues = "dead_letters_queue_name")
    public void deadMessage(Message message, Channel channel) throws IOException {
        try {
            // 获取消息
            String context = new String(message.getBody());
            System.out.println("DeadMessageProperties:" + message.getMessageProperties() + "\nDeadMessageBody:" + context);
            // 消息确认，第一个参数代表消息下标，b代表是否批量确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 判断消息是否已经拒绝过
            if (message.getMessageProperties().getRedelivered()) {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    @RabbitListener(queues = "un_routing_queue_name")
    public void unRoutingMessage(Message message, Channel channel) throws IOException {
        try {
            // 获取消息
            String context = new String(message.getBody());
            System.out.println("unRoutingMessageProperties:" + message.getMessageProperties() + "\nunRountingMessageBody:" + context);
            // 消息确认，第一个参数代表消息下标，b代表是否批量确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 判断消息是否已经拒绝过
            if (message.getMessageProperties().getRedelivered()) {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}
