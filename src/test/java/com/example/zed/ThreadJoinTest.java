package com.example.zed;

/**
 * @Auther: zed
 * @Date: 2019/4/15 16:07
 * @Description:
 */
public class ThreadJoinTest {
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            for (int i = 0;i < 5; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A" + i);
                Thread.yield();
            }
        });
        Thread b = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B" + i);
                Thread.yield();
            }
        });
        Thread c = new Thread(() -> {
            System.out.println("守护线程执行.......");
            int i = 0;
            while (true) {
                System.out.println("守护线程进度....." + i++);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        a.setPriority(Thread.MIN_PRIORITY);
        b.setPriority(Thread.MAX_PRIORITY);
        c.setDaemon(true);
        a.start();
//        a.join();
        b.start();
        c.start();
    }
}
