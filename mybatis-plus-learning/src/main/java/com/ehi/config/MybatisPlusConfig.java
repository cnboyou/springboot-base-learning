package com.ehi.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/12 17:29
 * @Description:  MybatisPlus分页配置
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.jiangfeixiang.mpdemo.springbootmp.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}