package com.ehi.anotion;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class Receiver {

    //===============以下是验证direct Exchange的队列==========
    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void processHello(String msg) {
        System.out.println("helloReceiver  : " + msg);
    }

    @RabbitListener(queues = "user")
    @RabbitHandler
    public void processUser(String msg) {
        System.out.println("userReceiver  : " + msg);
    }

    //===============以上是验证direct Exchange的队列==========




    //===============以下是验证topic Exchange的队列==========
    @RabbitListener(queues = "topic.message")
    @RabbitHandler
    public void processTopicMessage(String msg) {
        System.out.println("topicMessageReceiver  : " + msg);
    }

    @RabbitListener(queues = "topic.messages")
    @RabbitHandler
    public void processTopicMessages(String msg) {
        System.out.println("topicMessagesReceiver  : " + msg);
    }

    //===============以上是验证topic Exchange的队列==========






    //===============以下是验证fanout Exchange的队列==========
    @RabbitListener(queues = "fanout.A")
    @RabbitHandler
    public void processFanoutA(String msg) {
        System.out.println("fanoutAReceiver  : " + msg);
    }

    @RabbitListener(queues = "fanout.B")
    @RabbitHandler
    public void processFanoutB(String msg) {
        System.out.println("fanoutBReceiver  : " + msg);
    }

    @RabbitListener(queues = "fanout.C")
    @RabbitHandler
    public void processFanoutC(String msg) {
        System.out.println("fanoutCReceiver  : " + msg);
    }

    //===============以上是验证fanout Exchange的队列==========

}

