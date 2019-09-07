package com.example.zed.bytecode;

import com.sun.tools.attach.*;

import java.io.IOException;

/**
 * @Author: zed
 * @Date: 2019/9/7 14:30
 * @Description:
 */
public class Attacher {
    public static void main(String[] args) throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {
        // 传入目标 JVM pid
        VirtualMachine vm = VirtualMachine.attach("10160");
        vm.loadAgent("E://zed/target/classes/com/example/zed/bytecode/bytecode.jar");
    }
}
