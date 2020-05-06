package com.ehi.model;

/**
 * ClassName: User
 *
 * @Author: WangYiHai
 * @Date: 2020/5/6 17:58
 * @Description: TODO
 */
public class User {
    private Long id;
    private String username;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}