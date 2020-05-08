package com.ehi.model;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 18:37
 * @Description: TODO
 */
public class TestEntity {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}