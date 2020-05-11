package com.ehi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/11 18:51
 * @Description: TODO
 */
@RestController
public class RpcController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}