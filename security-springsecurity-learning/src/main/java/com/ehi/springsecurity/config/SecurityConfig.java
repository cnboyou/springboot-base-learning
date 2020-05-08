package com.ehi.springsecurity.config;

import com.ehi.springsecurity.model.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: SecurityConfig
 *
 * @Author: WangYiHai
 * @Date: 2020/4/15 16:25
 * @Description: TODO
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 加盐验证
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //下面的配置表示内存中配置两个用户
        auth.inMemoryAuthentication()
                .withUser("ehi").roles("admin")
                .password("$2a$10$2O4EwLrrFPEboTfDOtC0F.RpUMk.3q3KvBHRx7XXKUMLBGjOOBs8q")
                .and()
                .withUser("lisi").roles("user")
                .password("$2a$10$2O4EwLrrFPEboTfDOtC0F.RpUMk.3q3KvBHRx7XXKUMLBGjOOBs8q");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/db/**").hasRole("db")
                .antMatchers("/user/**").hasRole("user")
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
                                                        Authentication authentication) throws IOException, ServletException {
                        RespBean ok = RespBean.ok("登录成功", authentication.getPrincipal());
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(ok));
                        out.flush();
                        out.close();
                    }
                })

                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        RespBean error = RespBean.error("登录失败");
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(error));
                        out.flush();
                        out.close();
                    }
                })

                .loginPage("/login")
                .permitAll()
        .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        RespBean ok = RespBean.ok("注销成功", authentication.getPrincipal());
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(ok));
                        out.flush();
                        out.close();
                    }
                })

                .permitAll()
        .and()
            .csrf()
            .disable()
            .exceptionHandling()
            .accessDeniedHandler(new AccessDeniedHandler() {
                @Override
                public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
                    //权限不足
                    RespBean error = RespBean.error("权限不足");
                    resp.setStatus(403);
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(error));
                    out.flush();
                    out.close();
                }
            });
    }

    /**
     * /db/**格式的路径需要具备dba角色才能访问， /admin/**格式的路径则需要具备admin角色才能访问，
     * /user/**格式的路径，则需要具备user角色才能访问，此时提供相关接口，
     * 会发现，dba除了访问 /db/**，也能访问 /admin/**和 /user/**，admin角色除了访问 /admin/**，
     * 也能访问 /user/**，user角色则只能访问 /user/**。
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchy roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
        ((RoleHierarchyImpl) roleHierarchy).setHierarchy(hierarchy);
        return roleHierarchy;
    }
}