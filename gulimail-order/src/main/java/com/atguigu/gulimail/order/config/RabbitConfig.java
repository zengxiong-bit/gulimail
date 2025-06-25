package com.atguigu.gulimail.order.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.AbstractJackson2MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("spring-mq-fanout", true, false);
    }

    @Bean
    public Queue queue(){
        return new Queue("spring-mq-queue", true, false, false);
    }

    @Bean
    public Queue queue1(){
        return new Queue("spring-mq-queue1", true, false, false);
    }

    @Bean
    public Binding bind(){
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    @Bean
    public Binding bind1(){
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    @Bean
    public AbstractJackson2MessageConverter abstractJackson2MessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
