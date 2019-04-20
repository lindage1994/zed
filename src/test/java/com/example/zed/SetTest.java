package com.example.zed;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Auther: zed
 * @Date: 2019/4/17 19:29
 * @Description:
 */
@Slf4j
public class SetTest {
    @Test
    public void setTest() {
        Map<String,String> map = new TreeMap<>((o1,o2) -> {
            if (null == o1 && null == o2) {
                return 0;
            } else if (null == o1) {
                return -1;
            } else if (null == o2) {
                return 1;
            } else {
                return o1.compareTo(o2);
            }
        });
        map.put("a", "123");
        map.put("b", "234");
        map.put("c", "456");
        map.put(null, "000");
        map.put(null, "220");
        map.put(null, "333");
        System.out.println(map);
        System.out.println(map.get(null));
        Map<String,String> map1 =  new HashMap<>();
        map1.put(null,"123");
        map1.put(null, "234");
        System.out.println(map1);

    }
    @Test
    public void testSet() {
        log.error("heiheihei");
        Set<String> set = new LinkedHashSet<>();
        set.add(null);
        set.add("abc");
        set.add("ba c");
        System.out.println(set);
        Set<String> set1 = new HashSet<>();
        set1.add(null);
        set1.add("123");
        set1.add("bbb");
        set1.add("ccc");
        System.out.println(set1);
        Set<String> set2 = new TreeSet<>((o1,o2) -> {
            if (null == o1 && null == o2) {
                return 0;
            } else if (null == o1) {
                return -1;
            } else if (null == o2) {
                return 1;
            } else {
                return o1.compareTo(o2);
            }
        });
        set2.add(null);
        set2.add("bbb");
        set2.add("bbb");
        set2.add("abc");
        System.out.println(set2);
    }
}
