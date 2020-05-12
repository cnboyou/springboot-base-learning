package com.ehi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: FeignController
 *
 * @Author: WangYiHai
 * @Date: 2020/5/12 11:16
 * @Description: TODO
 */
@Slf4j
@RestController
@RequestMapping("/feign")
public class FeignController {

    @GetMapping("/demo1")
    public String getDemo1(@RequestParam String name) {
        log.info(name);
        return "Hello:" + name;
    }

}