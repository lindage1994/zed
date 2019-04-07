package com.example.zed;

import com.example.zed.algorithm.SortAlgorithm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    private

    @Test
    public void test() throws Exception {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("zed", "春江潮水连海平，海上明月共潮生");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testGet() throws Exception {
        String str = stringRedisTemplate.opsForValue().get("zed");
        System.out.println(str);
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(3);
        integerList.add(5);
        stringRedisTemplate.opsForList().set("intList",3,integerList.toString());
    }
    @Test
    public void testSort(){
         int[] n = {2,3,1,6,8,9,5,4};
         int[] s = SortAlgorithm.insertSort(n);
         for(int x:s){
             System.out.println(x);
         }

    }
    @Test
    public void testOptional() {
        Object o = null;
        System.out.println(Optional.ofNullable(o).map(Object::toString).orElse("kong"));
    }

    public static void main(String[] agrs) {
        Thread thread1 = new Thread(() -> Perrson.getValue("laobiao1"));
        thread1.start();

        Thread thread2 = new Thread(() -> Perrson.getValue("laobiao2"));
        thread2.start();

        Thread thread3 = new Thread(() -> Perrson.getValue("laobiao3"));
        thread3.start();
    }
}
