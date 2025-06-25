package com.atguigu.gulimail.order.controller;


import com.atguigu.common.utils.R;
import com.atguigu.gulimail.order.entity.OrderEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RabbitTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/rabbitMQ")
    @ResponseBody
    public R rabbitTest(){

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setBillContent("hhh");
        String msg = "这是测试数据";
        rabbitTemplate.convertAndSend("spring-mq-fanout", "", orderEntity);
        return R.ok("消息已发送");
    }


}
