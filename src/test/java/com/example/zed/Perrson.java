package com.example.zed;

/**
 * @Auther: zed
 * @Date: 2019/4/2 09:17
 * @Description:
 */
public class Perrson {

    public static String getValue(String a){

        String  ss = a;
        synchronized (ss){
            System.out.println(ss);
            return "sss";
        }
    }
}
