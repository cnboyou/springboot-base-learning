package com.ehi.rabbitmq.controller;

import com.ehi.rabbitmq.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private Sender sender;


    @GetMapping("/direct")
    public void direct() {
        sender.sendDirect();
    }

    @GetMapping("/topic")
    public void topic() {
        sender.sendTopic();
    }

    @GetMapping("/fanout")
    public void fanout() {
        sender.sendFanout();
    }

}
