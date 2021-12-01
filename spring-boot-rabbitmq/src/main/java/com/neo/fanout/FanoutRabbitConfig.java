package com.neo.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在Fanout这种交换机下
 * 会把消息发到绑定路由器的全部队列中，如果配置了路由键会被忽略
 */
@Configuration
public class FanoutRabbitConfig {
    /**
     * 创建三个队列
     */
    @Bean
    public Queue fanoutQueueA() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue fanoutQueueB() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue fanoutQueueC() {
        return new Queue("fanout.C");
    }

    /**
     * 创建一个交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 交换机和队列进行绑定
     */
    @Bean
    public Binding bindingExchangeA(Queue fanoutQueueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeB(Queue fanoutQueueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueB).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeC(Queue fanoutQueueC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueC).to(fanoutExchange);
    }
}
