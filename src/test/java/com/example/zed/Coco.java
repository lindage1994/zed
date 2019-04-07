package com.example.zed;


import java.text.SimpleDateFormat;

/**
 * @Auther: zed
 * @Date: 2019/3/23 14:36
 * @Description: coco
 */
public class Coco {
    public void drink(){
        Class coco = Coco.class;
        System.out.println(coco.getSimpleName() + "，我要同你饮酒");
    }
    public void seelp(long time){
        System.out.println("，仲有丢嗨" + new SimpleDateFormat("HH:mm:SS").format(time));
    }
    public void close() {
        System.out.println("，仲要出水....");
    }
}
