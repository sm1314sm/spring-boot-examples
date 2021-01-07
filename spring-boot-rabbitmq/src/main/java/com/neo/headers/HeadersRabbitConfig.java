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
    public Queue headersQueue1() {
        return new Queue("headers.A");
    }

    @Bean
    public Queue headersQueue2() {
        return new Queue("headers.B");
    }

    /**
     * 创建一个交换机
     */
    @Bean
    public HeadersExchange headersMessageExchange() {
        return new HeadersExchange("headersExchange");
    }

    /**
     * 交换机和队列进行绑定
     */
    @Bean
    public Binding bindingExchange1(Queue headersQueue1, HeadersExchange headersMessageExchange) {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("d", "4");
        return BindingBuilder.bind(headersQueue1).to(headersMessageExchange).whereAny(map).match();
    }

    @Bean
    public Binding bindingExchange2(Queue headersQueue2, HeadersExchange headersMessageExchange) {
        Map<String, Object> map = new HashMap<>();
        map.put("b", "2");
        map.put("c", "3");
        return BindingBuilder.bind(headersQueue2).to(headersMessageExchange).whereAll(map).match();
    }
}
