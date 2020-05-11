package com.ehi.xml;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendQueue() {
        String msg1 = "hello " + new Date();
        System.out.println("helloSender : " + msg1);
        this.rabbitTemplate.convertAndSend("hello", msg1);
//        this.rabbitTemplate.convertAndSend("direct","hello", msg1);
        String msg2 = "user " + new Date();
        System.out.println("userSender : " + msg2);
        this.rabbitTemplate.convertAndSend("user", msg2);
//        this.rabbitTemplate.convertAndSend("direct","user", msg1);
    }

    public void sendTopic() {
        String msg1 = "I am topic.mesaage msg======";
        System.out.println("topic.mesaage sender : " + msg1);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);

        String msg2 = "I am topic.mesaages msg########";
        System.out.println("topic.mesaages sender : " + msg2);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);
    }

    public void sendFanout() {
        String msg = "I am fanoutSender msg======";
        System.out.println("fanoutSender : " + msg);
        this.rabbitTemplate.convertAndSend("fanoutExchange","keysuibiantian", msg);
    }


}
