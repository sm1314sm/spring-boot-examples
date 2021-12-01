package com.neo.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueExchangeConfig {
    /**
     * 1.死信交换机(Dead-Letter-Exchange):申明队列的时候设置x-dead-letter-exchange参数
     * 当队列消息变成一个死信之后，如果该队列存在x-dead-letter-exchange参数，那么它会被发送到参数x-dead-letter-exchange对应值的交换机上
     *
     * 判断一个消息是否是死信消息的依据：
     * a. 消息被拒绝并不再返回原队列
     * b. 消息过期:
     *    设置队列的过期时间（在队列申明的时候使用 x-message-ttl 参数，单位为 毫秒）
     *    单独设置某个消息的过期时间（设置消息属性的 expiration 参数的值，单位为 毫秒）
     *    如果同时使用了两种方式设置过期时间，以两者之间较小的那个数值为准
     * c. 队列已满，无法再添加数据
     *
     * 2.备份交换机(alternate-exchange):申明交换机的时候设置alternate-exchange参数
     * 未被正确路由的消息将会经过此交换器
     */
    private static final String MESSAGE_BAK_QUEUE_NAME = "un_routing_queue_name";
    private static final String MESSAGE_BAK_EXCHANGE_NAME = "un_routing_exchange_name";

    private static final String DEAD_LETTERS_QUEUE_NAME = "dead_letters_queue_name";
    private static final String DEAD_LETTERS_EXCHANGE_NAME = "dead_letters_exchange_name";

    private static final String QUEUE_NAME = "test_dlx_queue_name";
    private static final String EXCHANGE_NAME = "test_dlx_exchange_name";

    private static final String ROUTING_KEY = "user.add";

    /**
     * 声明备份队列、备份交换机并绑定
     * 建议使用FanoutExchange广播式交换机
     */
    @Bean
    public Queue msgBakQueue() {
        return new Queue(MESSAGE_BAK_QUEUE_NAME);
    }

    @Bean
    public FanoutExchange msgBakExchange() {
        return new FanoutExchange(MESSAGE_BAK_EXCHANGE_NAME);
    }

    @Bean
    public Binding msgBakBinding(Queue msgBakQueue, FanoutExchange msgBakExchange) {
        return BindingBuilder.bind(msgBakQueue).to(msgBakExchange);
    }

    /**
     * 声明死信队列、死信交换机并绑定
     * 建议使用FanoutExchange广播式交换机
     */
    @Bean
    public Queue deadLettersQueue() {
        return new Queue(DEAD_LETTERS_QUEUE_NAME);
    }

    @Bean
    public FanoutExchange deadLettersExchange() {
        return new FanoutExchange(DEAD_LETTERS_EXCHANGE_NAME);
    }

    @Bean
    public Binding deadLettersBinding(Queue deadLettersQueue, FanoutExchange deadLettersExchange) {
        return BindingBuilder.bind(deadLettersQueue).to(deadLettersExchange);
    }

    /**
     * 声明普通队列，并指定相应的死信交换机
     */
    @Bean
    public Queue queue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", DEAD_LETTERS_EXCHANGE_NAME);
        return new Queue(QUEUE_NAME, true, false, false, arguments);
    }

    /**
     * 声明普通交换机，并指定相应的备份交换机
     */
    @Bean
    public Exchange exchange() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("alternate-exchange", MESSAGE_BAK_EXCHANGE_NAME);
        return new DirectExchange(EXCHANGE_NAME, true, false, arguments);
    }

    /**
     * 绑定普通交换机和普通队列
     */
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    /**
     * 声明普通交换机string
     */
    @Bean
    public Queue neoQueue() {
        return new Queue("neo");
    }

    /**
     * 声明普通交换机object
     */
    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }
}
