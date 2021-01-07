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
    public Queue derictQueue1() {
        return new Queue("direct.A");
    }

    @Bean
    public Queue derictQueue2() {
        return new Queue("direct.B");
    }

    /**
     * 创建一个交换机
     */
    @Bean
    public DirectExchange directMessageExchange() {
        return new DirectExchange("directExchange");
    }

    /**
     * 交换机和队列进行绑定
     */
    @Bean
    public Binding bindingDirectExchange1(Queue derictQueue1, DirectExchange directMessageExchange) {
        return BindingBuilder.bind(derictQueue1).to(directMessageExchange).with("test1");
    }

    @Bean
    public Binding bindingDirectExchange2(Queue derictQueue2, DirectExchange directMessageExchange) {
        return BindingBuilder.bind(derictQueue2).to(directMessageExchange).with("test2");
    }
}
