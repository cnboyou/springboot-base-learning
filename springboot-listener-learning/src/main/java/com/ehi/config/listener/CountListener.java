package com.ehi.config.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * ClassName: CountListener
 *
 * @Author: WangYiHai
 * @Date: 2020/5/12 16:17
 * @Description: 下面以HttpSessionListener为例，用来监听 统计当前访问人数。
 */
@WebListener//注册监听器
public class CountListener implements HttpSessionListener  {
    private int count = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        count++;
        se.getSession().getServletContext().setAttribute("count", count);
        System.out.println("新增在线人数，当前在线人数："+count);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        count--;
        se.getSession().getServletContext().setAttribute("count", count);
        System.out.println("删减在线人数，当前在线人数："+count);
    }




}