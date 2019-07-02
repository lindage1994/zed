package com.example.zed.util;

import com.example.zed.enums.Color;
import org.junit.jupiter.api.Test;

import java.util.*;

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

    @Test
    public void test2() {
        Map<Color,Object> map = new EnumMap<>(Color.class);
        map.put(Color.RED, new Object());
        map.put(Color.BLUE, new Object());
        System.out.print(map.toString());
        Map<Color,Object> map2 = new EnumMap<>(map);
        Map<Color, Integer> map3 = new HashMap<>();
        map3.put(Color.RED, 1);
        map3.put(Color.BLUE, 2);
        Map<Color, Integer> map4 = new EnumMap<>(map3);
    }

    @Test
    public void test3() {
        EnumSet<Color> colorEnumSet = EnumSet.noneOf(Color.class);
        colorEnumSet.add(Color.RED);
        colorEnumSet.add(Color.BLUE);

        EnumSet<Color> colorEnumSet1 = EnumSet.allOf(Color.class);

        EnumSet<Color> colorEnumSet2 = EnumSet.of(Color.BLUE);

        EnumSet<Color> colorEnumSet3 = EnumSet.copyOf(colorEnumSet1);

        List<Color> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        EnumSet<Color> colorEnumSet4 = EnumSet.copyOf(colors);
    }

     class MyThread implements Runnable {


        Runnable r = () -> System.out.print("xxxxxx");

        @Override
        public void run() {
            System.out.print("it is "+ Thread.currentThread().getId() + " running!");
            Color.valueOf("RED").draw();
            int index = Color.BLUE.ordinal();
            System.out.println(index);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
