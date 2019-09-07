package com.example.zed;

import com.example.zed.generic.Generic;
import org.junit.Test;

/**
 * @Author: zed
 * @Date: 2019/7/17 16:15
 * @Description: 泛型测试
 */
public class GenericTest {

    @Test
    public void test() {
        Generic<String> generic = new Generic<>("123456");
        Generic<CharSequence> generic1 = new Generic<>("123456");
        System.out.println(generic.getKey());
        System.out.println(generic1.getKey());
    }

    @Test
    public void testPassByValue() {
        Dog dog = new Dog("A");
        func(dog);
        System.out.println(dog.getName());

    }

    private static void func(Dog dog) {
        dog.setName("B");
    }
}
