package com.example.zed.util;

import com.example.zed.annotation.MethodName;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;

public class TestAnnotationMain {
    public static void main(String[] args){
        TestAnnotation obj = new TestAnnotation();
        Class clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        StringBuffer log = new StringBuffer();
        clazz.isAnnotationPresent(Controller.class);
        int errornum = 0;

        for (Method method:methods){
            if (method.isAnnotationPresent(MethodName.class)){
                System.out.println(method.getName());
                try {
                    method.invoke(obj,null);
                } catch (Exception e) {
                    errornum++;
                    log.append(method.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    //记录测试过程中，发生的异常的名称
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //记录测试过程中，发生的异常的具体信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
                }
            }
        }
        System.out.println(log);
    }
}
