package com.ehi.redis.model;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * ClassName: MyKeyGenerator
 *
 * @Author: WangYiHai
 * @Date: 2020/4/16 10:08
 * @Description: TODO
 */
@Component
public class  MyKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return method.getName() + Arrays.toString(params);
    }
}