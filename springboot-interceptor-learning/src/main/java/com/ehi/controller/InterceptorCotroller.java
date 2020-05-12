package com.ehi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/12 16:28
 * @Description: TODO
 */
@Controller
public class InterceptorCotroller {
    @RequestMapping("/test")
    public ModelAndView testInterceptor() {
        System.out.println("进入controller");
        ModelAndView mv = new ModelAndView();

        mv.setViewName("aaa");
        System.out.println("即将返回modelandview");
        return mv;
    }
}