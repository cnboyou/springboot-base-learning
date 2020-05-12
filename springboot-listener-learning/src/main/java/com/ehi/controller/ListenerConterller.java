package com.ehi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/12 16:19
 * @Description: TODO
 */
@RestController
public class ListenerConterller {

    @RequestMapping("/test")
    public String testListenerLogin(HttpServletRequest req) {
        System.out.println("当前在线人数"+req.getSession().getId()+"："
                +req.getSession().getServletContext().getAttribute("count"));
        return "Hello testListenerLogin";
    }

}