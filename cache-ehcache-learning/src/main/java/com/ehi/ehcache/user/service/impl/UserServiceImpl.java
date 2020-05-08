package com.ehi.ehcache.user.service.impl;

import com.ehi.ehcache.user.model.User;
import com.ehi.ehcache.user.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 *
 * @Author: WangYiHai
 * @Date: 2020/4/15 18:50
 * @Description: TODO
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    /**
     * 缓存key就是方法的参数，缓存的value就是方法的返回值
     * @param id
     * @param userName
     * @return
     */
    @Cacheable(key = "#id")
    @Override
    public User getUserById(Integer id, String userName) {
        return null;
    }

    @Cacheable(keyGenerator = "myKeyGenerator")
    public User getUserById(Integer id) {
        User user = new User(1,"Lisi");
        System.out.println(user);
        return user;
    }

    /**
     * @CachePut加在更新方法上，当数据库中的数据更新后，相关的缓存数据页要自动更新，
     * 使用该注解，可以将方法的返回值自动更新到已经存在的key上
     * @param user
     * @return
     */
    @CachePut(key = "#user.id")
    public User updateUserById(User user) {
        return user;
    }

    /**
     * @CacheEvict这个注解加在删除方法上，当数据库中的数据删除后，相关的缓存数据页要自动删除
     * @param id
     */
    @CacheEvict()
    public void deleteUserById(Integer id) {
        //这里执行删除操作，删除的去数据库中删除
    }

}