package com.neo.direct;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在Direct这种交换机下
 * 会把消息发送到路由键完全匹配的队列中
 */
@Configuration
public class DirectRabbitConfig {
    /**
     * 创建两个队列
     */
    @Bean
    public Queue derictQueueA() {
        return new Queue("direct.A");
    }

    @Bean
    public Queue derictQueueB() {
        return new Queue("direct.B");
    }

    /**
     * 创建一个交换机
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    /**
     * 交换机和队列进行绑定
     */
    @Bean
    public Binding bindingDirectExchange1(Queue derictQueueA, DirectExchange directExchange) {
        return BindingBuilder.bind(derictQueueA).to(directExchange).with("derictA");
    }

    @Bean
    public Binding bindingDirectExchange2(Queue derictQueueB, DirectExchange directExchange) {
        return BindingBuilder.bind(derictQueueB).to(directExchange).with("derictB");
    }
}
