package com.ehi.model;

import org.springframework.data.annotation.Id;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/13 14:31
 * @Description: TODO
 */
public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}

