package com.example.zed.bytecode;


import javassist.*;

import java.io.IOException;

/**
 * @Author: zed
 * @Date: 2019/9/7 11:39
 * @Description: Javassist
 */
public class JavassistTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass cc = classPool.get("com.example.zed.bytecode.Base");
        CtMethod  method = cc.getDeclaredMethod("process");
        method.insertBefore("{ System.out.println(\"start aop\"); }");
        method.insertAfter("{ System.out.println(\"end aop\"); }");
        Class c = cc.toClass();
        cc.writeFile("E://zed/target/classes");
        Base h = (Base)c.newInstance();
        h.process();
    }
}
