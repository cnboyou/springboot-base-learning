package com.ehi.controller;

import com.ehi.server.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/7 18:45
 * @Description: TODO
 */
@RestController
public class TestController {

    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping("/test")
    public String test() {
        return helloWorldService.sayHello("Spring boot with Hessian.");
    }

}