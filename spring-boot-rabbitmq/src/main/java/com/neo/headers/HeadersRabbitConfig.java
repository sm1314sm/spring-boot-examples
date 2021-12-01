package com.neo.headers;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 在Headers这种交换机下
 * 相较于direct和topic固定地使用路由键，headers则可以自定义匹配规则
 */
@Configuration
public class HeadersRabbitConfig {
    /**
     * 创建两个队列
     */
    @Bean
    public Queue headersQueueA() {
        return new Queue("headers.A");
    }

    @Bean
    public Queue headersQueueB() {
        return new Queue("headers.B");
    }

    /**
     * 创建一个交换机
     */
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headersExchange");
    }

    /**
     * 交换机和队列进行绑定
     */
    @Bean
    public Binding bindingExchange1(Queue headersQueueA, HeadersExchange headersExchange) {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        return BindingBuilder.bind(headersQueueA).to(headersExchange).whereAny(map).match();
    }

    @Bean
    public Binding bindingExchange2(Queue headersQueueB, HeadersExchange headersExchange) {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        return BindingBuilder.bind(headersQueueB).to(headersExchange).whereAll(map).match();
    }
}
