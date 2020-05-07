package com.ehi.service.impl;

import com.ehi.service.HelloWorldService;
import org.springframework.stereotype.Service;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/7 18:35
 * @Description: TODO
 */
@Service("HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String name) {
        return "Hello World! " + name;
    }
}