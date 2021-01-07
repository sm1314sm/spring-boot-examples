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
    public Queue fanoutQueue1() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue fanoutQueue3() {
        return new Queue("fanout.C");
    }

    /**
     * 创建一个交换机
     */
    @Bean
    public FanoutExchange fanoutMessageExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 交换机和队列进行绑定
     */
    @Bean
    public Binding bindingExchangeA(Queue fanoutQueue1, FanoutExchange fanoutMessageExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutMessageExchange);
    }

    @Bean
    public Binding bindingExchangeB(Queue fanoutQueue2, FanoutExchange fanoutMessageExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutMessageExchange);
    }

    @Bean
    public Binding bindingExchangeC(Queue fanoutQueue3, FanoutExchange fanoutMessageExchange) {
        return BindingBuilder.bind(fanoutQueue3).to(fanoutMessageExchange);
    }
}
