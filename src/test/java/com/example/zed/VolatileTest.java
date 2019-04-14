package com.example.zed;

/**
 * @Auther: zed
 * @Date: 2019/4/12 16:40
 * @Description: volatile 测试
 */
public class VolatileTest {
    private static volatile int a = 0;
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{for(int j = 0;j < 10; j++)a++;});
            thread.start();
            Thread.sleep(300);
        }
        System.out.println(a);
    }
}
