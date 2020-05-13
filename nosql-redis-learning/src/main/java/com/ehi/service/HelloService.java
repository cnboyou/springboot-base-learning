package com.ehi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void hello() {
        redisTemplate.opsForValue().set("k1","v1");

        Object k1 = redisTemplate.opsForValue().get("k1");
        System.out.println(k1);
    }

    public void hello1() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("k2","v2");
        Object k1 = ops.get("k2");
        System.out.println(k1);
    }

    public void hello3() {
        ValueOperations ops = stringRedisTemplate.opsForValue();
        ops.set("k3","v3");
        Object k1 = ops.get("k3");
        System.out.println(k1);
    }
}