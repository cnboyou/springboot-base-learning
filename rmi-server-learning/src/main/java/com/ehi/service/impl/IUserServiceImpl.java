package com.ehi.service.impl;

import com.ehi.model.User;
import com.ehi.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * ClassName: IUserServiceImpl
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 11:21
 * @Description: TODO
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Override
    public User getUserByName(String username) {
        User user = null;
        if (username != null && !username.equals("")) {
            user = new User();
            if (username.equals("admin")) {
                user.setUsername("admin");
                user.setPassword("123456");
            }else{
                user.setUsername("xxxx");
                user.setPassword("111111");
            }

        }
        return user;
    }
}