package com.ehi.ehcache.user.service;

import com.ehi.ehcache.user.model.User;

/**
 * ClassName: UserService
 *
 * @Author: WangYiHai
 * @Date: 2020/4/15 18:49
 * @Description: TODO
 */
public interface UserService {

    User getUserById(Integer id, String userName);
}