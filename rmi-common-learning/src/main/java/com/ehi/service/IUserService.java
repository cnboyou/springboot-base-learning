package com.ehi.service;

import com.ehi.model.User;

/**
 * ClassName: IUserService
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 11:19
 * @Description: TODO
 */
public interface IUserService {

    User getUserByName(String username);

}