package com.ehi.rabbitmq.service;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/11 16:27
 * @Description: TODO
 */
@Component
public class Sender {

    //rabbitTemplate.convertAndSend(String exchange交换机名称(可省略), String routingKey路由键, Object object传递的消息）
    @Autowired
    private AmqpTemplate rabbitTemplate;

    //direct方式交换机名字随便填，但是不能填direct，会造成两次消费
    public void sendDirect() {
        String msg1 = "hello " + new Date();
        System.out.println("helloSender : " + msg1);
        this.rabbitTemplate.convertAndSend("hello", msg1);
//        this.rabbitTemplate.convertAndSend("direct","hello", msg1);
        String msg2 = "user " + new Date();
        System.out.println("userSender : " + msg2);
        this.rabbitTemplate.convertAndSend("user", msg2);
//        this.rabbitTemplate.convertAndSend("direct","user", msg2);
    }

    //topic方式
    public void sendTopic() {
        String msg1 = "I am topic.mesaage msg======";
        System.out.println("topic.mesaage sender : " + msg1);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);

        String msg2 = "I am topic.mesaages msg########";
        System.out.println("topic.mesaages sender : " + msg2);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);
    }

    //fanout方式routingKey随便填
    public void sendFanout() {
        String msg = "I am fanoutSender msg======";
        System.out.println("fanoutSender : " + msg);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "suibiantian",msg);
    }


}
