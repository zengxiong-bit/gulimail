package com.atguigu.gulimail.order.rabbit;

import com.atguigu.gulimail.order.entity.OrderEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = {"spring-mq-queue", "spring-mq-queue1"})
    public void receive(OrderEntity order) {
        System.out.println("收到消息：" + order);
    }
}

