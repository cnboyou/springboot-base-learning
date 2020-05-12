package com.ehi.config.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/12 16:32
 * @Description: TODO
 */
@WebFilter(filterName = "myFilter",urlPatterns = {"/*"})//指定过滤器的名称和要过滤的地址
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("MyFilter init ");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("MyFilter doFilter"+req.getParameter("name"));
        chain.doFilter(request, response);
        return ;
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        System.out.println("MyFilter destroy");
    }

}