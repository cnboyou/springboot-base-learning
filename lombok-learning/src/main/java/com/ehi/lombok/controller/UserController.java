package com.ehi.lombok.controller;

import com.ehi.lombok.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: s
 *
 * @Author: WangYiHai
 * @Date: 2020/4/8 10:15
 * @Description: TODO
 */
@Slf4j
@RestController
@RequestMapping(("/users"))
public class UserController {

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id) {
        User user = new User();
        user.setUsername("风清扬");
        user.setAge(21);
        user.setId(id);

        if (log.isInfoEnabled()) {
            log.info("用户 {}", user);
        }

        return user;
    }

}
