package com.example.zed.generic;

/**
 * @Author: zed
 * @Date: 2019/7/17 16:12
 * @Description: 泛型栗子
 */
public class Generic<X extends CharSequence> {

    private X key;

    public Generic(X key) {
        this.key = key;
    }

    public X getKey() {
        return key;
    }

}
