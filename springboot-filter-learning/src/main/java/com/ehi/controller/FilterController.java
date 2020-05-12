package com.ehi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/12 16:33
 * @Description: TODO
 */
@Controller
public class FilterController {

    @RequestMapping("/test")
    public String testMyFilter() {
        return "Hello Filter";
    }
}