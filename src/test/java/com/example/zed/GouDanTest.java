package com.example.zed;

import org.junit.Test;

import java.util.Date;

/**
 * @Auther: zed
 * @Date: 2019/3/23 14:42
 * @Description: doudantest
 */
public class GouDanTest {
    @Test
    public void gouDanTest() {
        Coco coco = new Coco();
        coco.drink();
        coco.seelp(new Date().getTime());
        coco.close();
    }
    @Test
    public void timeTest() {
        long nanotime = System.nanoTime();
        System.out.println(System.currentTimeMillis());
        System.out.println(nanotime);
        System.out.println((int)((Math.random()*9+1)*1000));
    }
}
