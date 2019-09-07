package com.example.zed.bytecode;

import java.lang.management.ManagementFactory;

/**
 * @Author: zed
 * @Date: 2019/9/7 11:11
 * @Description:
 */
public class Base {

    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String s = name.split("@")[0];
        // pid
        System.out.println("pid:" + s);
        while (true) {
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
                break;
            }
            process();
        }
    }

    public static void process() {
        System.out.println("process");
    }
}
