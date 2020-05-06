package com.ehi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * ClassName: HelloService
 *
 * @Author: WangYiHai
 * @Date: 2020/5/6 14:31
 * @Description: TODO
 */
@Service
public class HelloService {
    @Autowired
    RedisTemplate redisTemplate;

    public void hello() {
        redisTemplate.opsForValue().set("k1","v1");

        Object k1 = redisTemplate.opsForValue().get("k1");
        System.out.println(k1);
    }
}