package com.example.zed.json;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zed
 * @Date: 2019/10/26 19:22
 * @Description:
 */
@Data
public class User {

    int uid;

    String name;

    @JSONField(serializeUsing = JSONDateSerializer.class, deserializeUsing = JSONDateSerializer.class)
    Date birth;

}
