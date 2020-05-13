package com.ehi.model;

/**
 * ClassName: User
 *
 * @Author: WangYiHai
 * @Date: 2020/5/13 11:04
 * @Description: TODO
 */
public class User {
    private String username;
    private Integer num;
    private Double salary;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}