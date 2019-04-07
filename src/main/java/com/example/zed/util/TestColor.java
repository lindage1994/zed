package com.example.zed.util;

import com.example.zed.enums.Color;
import org.junit.jupiter.api.Test;

public class TestColor{
    private static int startId = 1;
    private final static int uid = startId++;
    @Test
    public void test(){
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        MyThread myThread4 = new MyThread();
        MyThread myThread5 = new MyThread();
        myThread1.run();
        myThread2.run();
        myThread3.run();
        myThread4.run();
        myThread5.run();

    }

     class MyThread implements Runnable {


        Runnable r = () -> {
            System.out.print("xxxxxx");

        };

        @Override
        public void run() {
            System.out.print("it is "+ Thread.currentThread().getId() + " running!");
            Color.valueOf("RED").draw();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
