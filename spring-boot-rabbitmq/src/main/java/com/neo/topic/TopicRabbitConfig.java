package com.neo.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在Topic这种交换机下
 * 路由键是用.隔开的串字符，比如说agreements.um.#或者agreements.eu.*等，#表示0个或若干个关键字，*表示一个关键字
 */
@Configuration
public class TopicRabbitConfig {
    /**
     * 创建两个队列
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue("topic.A");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topic.B");
    }

    /**
     * 创建一个交换机
     */
    @Bean
    public TopicExchange topicMessageExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 交换机和队列进行绑定
     */
    @Bean
    public Binding bindingExchangeMessage1(Queue topicQueue1, TopicExchange topicMessageExchange) {
        return BindingBuilder.bind(topicQueue1).to(topicMessageExchange).with("topic.configtest.*");
    }

    @Bean
    public Binding bindingExchangeMessage2(Queue topicQueue2, TopicExchange topicMessageExchange) {
        return BindingBuilder.bind(topicQueue2).to(topicMessageExchange).with("topic.*");
    }

    @Bean
    public Binding bindingExchangeMessage3(Queue topicQueue2, TopicExchange topicMessageExchange) {
        return BindingBuilder.bind(topicQueue2).to(topicMessageExchange).with("topic.configtest.#");
    }
}
