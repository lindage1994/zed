package com.example.zed.demo;
import java.util.*;

/**
 * @Author: zed
 * @Date: 2019/7/2 16:50
 * @Description: 类初始化过程
 */

class Initable {
    //编译期静态常量
    static final int staticFinal = 47;
    //非编期静态常量
    static final int staticFinal2 =
            ClassInitialization.rand.nextInt(1000);
    static {
        Class<? extends Number> clazz = Number.class;
        Integer x = new Integer("10");
        Class<Integer> clazz1 = Integer.class;
        Number number = clazz1.cast(x);
        System.out.println("Initializing Initable");
    }

    public void cast2(Object object) {
        //instanceof关键字
        if (object instanceof Integer) {
            Integer integer = (Integer) object;
        }

        //isInstance方法
        if (Integer.class.isInstance(object)) {
            Integer integer = (Integer) object;
        }

    }
}

class Initable2 {
    //静态成员变量
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    //静态成员变量
    static int staticNonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}

public class ClassInitialization {
    public static Random rand = new Random(47);
    public static void main(String[] args) throws Exception {
        //字面常量获取方式获取Class对象
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");
        //不触发类初始化
        System.out.println(Initable.staticFinal);
        //会触发类初始化
        System.out.println(Initable.staticFinal2);
        //会触发类初始化
        System.out.println(Initable2.staticNonFinal);
        //forName方法获取Class对象
        Class initable3 = Class.forName("com.example.zed.demo.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
}
