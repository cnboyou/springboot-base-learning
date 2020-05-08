package com.ehi.controller;

import com.ehi.model.User;
import com.ehi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserController
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 11:30
 * @Description: TODO
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/getUser")
    public User getUser(){
        return userService.getUserByName("admin");
    }
}