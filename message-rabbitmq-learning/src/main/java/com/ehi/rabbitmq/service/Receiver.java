package com.ehi.rabbitmq.service;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 *     @RabbitListener()rabbit监听
 *     @QueueBinding()队列绑定 value绑定@Queue，exchange绑定@Exchange，key为路由键
 *     @Queue队列 value：名称；autoDelete：是否自动删除，当最后一个消费者断开连接之后队列是否自动被删除；durable： 是否持久化, 队列的声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果想重启之后还存在就要使队列持久化，保存到Erlang自带的Mnesia数据库中，当rabbitmq重启之后会读取该数据库
 *     @Exchange交换器，type有五种，其余参数同@Queue
 */
@Component
public class Receiver {

    //===============以下是验证direct Exchange的队列==========
//    @RabbitListener(queues = "hello")
    //direct模式，exchange名字随便填
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "hello",autoDelete = "false",declare = "true"),exchange = @Exchange(value = "suibianxie",type = ExchangeTypes.DIRECT),key = "user"
    ))
    @RabbitHandler
    public void processHello(String msg) {
        System.out.println("helloReceiver  : " + msg);
    }

    //    @RabbitListener(queues = "user")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "user",autoDelete = "false"),exchange = @Exchange(value = "suibianxie",type = ExchangeTypes.DIRECT),key = "hello"
    ))
    @RabbitHandler
    public void processUser(String msg) {
        System.out.println("userReceiver  : " + msg);
    }

    //===============以上是验证direct Exchange的队列==========




    //===============以下是验证topic Exchange的队列==========
//    @RabbitListener(queues = "topic.message")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "topic.message",autoDelete = "false"),exchange = @Exchange(value = "exchange",type = ExchangeTypes.TOPIC),key = "topic.message"
    ))
    @RabbitHandler
    public void processTopicMessage(String msg) {
        System.out.println("topicMessageReceiver  : " + msg);
    }

    //    @RabbitListener(queues = "topic.messages")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "topic.messages",autoDelete = "false"),exchange = @Exchange(value = "exchange",type = ExchangeTypes.TOPIC),key = "topic.#"
    ))
    @RabbitHandler
    public void processTopicMessages(String msg) {
        System.out.println("topicMessagesReceiver  : " + msg);
    }

    //===============以上是验证topic Exchange的队列==========






    //===============以下是验证fanout Exchange的队列==========
//    @RabbitListener(queues = "fanout.A")
    //fanout方式key不用填
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanout.A",autoDelete = "false"),exchange = @Exchange(value = "fanoutExchange",type = ExchangeTypes.FANOUT)
    ))
    @RabbitHandler
    public void processFanoutA(String msg) {
        System.out.println("fanoutAReceiver  : " + msg);
    }

    //    @RabbitListener(queues = "fanout.B")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanout.B",autoDelete = "false"),exchange = @Exchange(value = "fanoutExchange",type = ExchangeTypes.FANOUT)
    ))
    @RabbitHandler
    public void processFanoutB(String msg) {
        System.out.println("fanoutBReceiver  : " + msg);
    }

    //    @RabbitListener(queues = "fanout.C")
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanout.C",autoDelete = "false"),exchange = @Exchange(value = "fanoutExchange",type = ExchangeTypes.FANOUT)
    ))
    public void processFanoutC(String msg) {
        System.out.println("fanoutCReceiver  : " + msg);
    }

    //===============以上是验证fanout Exchange的队列==========

}

