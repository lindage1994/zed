package com.example.zed;

/**
 * @Author: zed
 * @Date: 2019/7/17 17:51
 * @Description:
 */
public class Dog {

    public Dog(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        name.replace("B","");
        this.name = name;
    }
}
