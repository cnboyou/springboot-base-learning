package com.ehi.anotion;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueCofig {

    //===============以下是验证direct Exchange的队列==========
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public Queue userQueue() {
        return new Queue("user");
    }

    /**
     *注入name为'direct'的DirectExchange,默认名字就是空字符串，可以不注入
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("direct");
    }

    /**
     * 将队列hello与exchange绑定，binding_key为hello,就是完全匹配
     */
    @Bean
    Binding bindingHelloExchange(Queue helloQueue, DirectExchange exchange) {
        return BindingBuilder.bind(helloQueue).to(exchange).with("hello");
    }

    /**
     * 将队列user与exchange绑定，binding_key为hello,就是完全匹配
     */
    @Bean
    Binding bindingUserExchange(Queue userQueue, DirectExchange exchange) {
        return BindingBuilder.bind(userQueue).to(exchange).with("user");
    }

    //===============以上是验证direct Exchange的队列==========

    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message");
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }


    /**
     *注入name为exchange的TopicExchange
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }


    //===============以上是验证topic Exchange的队列==========


    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }


    /**
     * 注入name为fanoutExchange的FanoutExchange
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }



    /**
     * 将队列fanout.A与FanoutExchange绑定
     */
    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    /**
     * 将队列fanout.B与FanoutExchange绑定
     */
    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    /**
     * 将队列fanout.C与FanoutExchange绑定
     */
    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
    //===============以上是验证Fanout Exchange的队列==========



}
