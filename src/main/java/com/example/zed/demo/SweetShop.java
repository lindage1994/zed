package com.example.zed.demo;

/**
 * @Author: zed
 * @Date: 2019/7/2 16:42
 * @Description: Class对象加载时机
 */
class Candy {
    static {   System.out.println("Loading Candy"); }
}

class Gum {
    static {   System.out.println("Loading Gum"); }
}

class Cookie {
    static {   System.out.println("Loading Cookie"); }
}

public class SweetShop {
    public static void print(Object obj) {
        System.out.println(obj);
    }
    public static void main(String[] args) {
        print("inside main");
        new Candy();
        print("After creating Candy");
        try {
            Class.forName("com.example.zed.demo.Gum");
        } catch(ClassNotFoundException e) {
            print("Couldn't find Gum");
        }
        print("After Class.forName(\"com.example.zed.demo.Gum\")");
        new Cookie();
        print("After creating Cookie");
    }
}