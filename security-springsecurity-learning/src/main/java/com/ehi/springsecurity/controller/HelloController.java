package com.ehi.springsecurity.controller;

import com.ehi.springsecurity.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: HelloController
 *
 * @Author: WangYiHai
 * @Date: 2020/4/15 16:15
 * @Description: TODO
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/login")
    public RespBean login() {
        return RespBean.error("尚未登录，请登录");
    }


}