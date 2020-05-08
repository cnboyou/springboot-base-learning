package com.ehi.redis.model;

/**
 * ClassName: User
 *
 * @Author: WangYiHai
 * @Date: 2020/4/15 18:51
 * @Description: TODO
 */
public class User {

    private Integer id;

    private String userName;

    public User(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}