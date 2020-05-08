package com.ehi.model;

import java.io.Serializable;

/**
 * ClassName: User
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 11:19
 * @Description: TODO
 */
public class User implements Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}