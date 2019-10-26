package com.example.zed.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * @Author: zed
 * @Date: 2019/10/26 19:35
 * @Description: JSON自定义序列化TEST
 */
public class JSONSerializerTest {

    public static void main(String[] args) {
        User user = new User();
        user.setUid(1);
        user.setName("么么哒");
        user.setBirth(new Date());

        System.out.println(JSONObject.parse(JSON.toJSONString(user)));

        String userStr = "{\"uid\":1,\"name\":\"么么哒\",\"birth\":\"{\\\"Year\\\":119,\\\"Zone\\\":-420,\\\"Time\\\":1572095317359}\"}";

        User user1 = JSONObject.parseObject(userStr,User.class);
        System.out.println(user1.getBirth());
    }
}
