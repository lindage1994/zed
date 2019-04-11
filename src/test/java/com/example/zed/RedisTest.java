package com.example.zed;

import com.example.zed.algorithm.SortAlgorithm;
import com.example.zed.lock.RedisLock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ZedApplication.class)
@EnableAutoConfiguration
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JedisPool jedisPool;
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
         SortAlgorithm.insertionSort(n);
         for(int x:n){
             System.out.println(x);
         }

    }
    @Test
    public void testOptional() {
        Object o = null;
        System.out.println(Optional.ofNullable(o).map(Object::toString).orElse("kong"));
    }
    @Test
    public void testRedisLock() {
        Jedis jedis = jedisPool.getResource();
        String userLock = "user:id:12345";
        String threadId = "1";
        if (RedisLock.tryGetDistributedLock(jedis, userLock, threadId, 30)) {
            System.out.println("get lock success, thread-id:" + threadId);
        }
        RedisLock.releaseDistributedLock(jedis, userLock, threadId);
        jedisPool.close();
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
