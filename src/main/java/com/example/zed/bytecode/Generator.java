package com.example.zed.bytecode;

import aj.org.objectweb.asm.ClassReader;
import aj.org.objectweb.asm.ClassVisitor;
import aj.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author: zed
 * @Date: 2019/9/7 11:12
 * @Description: classReader and classWriter
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        // classReader
        ClassReader classReader = new ClassReader("com/example/zed/bytecode/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        // write
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        // output
        File f = new File("E://zed/target/classes/com/example/zed/bytecode/Base.class");
        FileOutputStream outputStream = new FileOutputStream(f);
        outputStream.write(data);
        outputStream.close();
        System.out.println("now generator cc success!!!");
    }
}
